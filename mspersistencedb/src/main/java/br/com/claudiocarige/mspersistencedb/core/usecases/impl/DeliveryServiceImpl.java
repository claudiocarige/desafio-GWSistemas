package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Item;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.ShippingRates;
import br.com.claudiocarige.mspersistencedb.core.dtos.DeliveryDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.RequestDelivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.ResponseOfSolicitation;
import br.com.claudiocarige.mspersistencedb.core.exceptions.IllegalArgumentException;
import br.com.claudiocarige.mspersistencedb.core.exceptions.InsufficientStockException;
import br.com.claudiocarige.mspersistencedb.core.exceptions.NoSuchElementException;
import br.com.claudiocarige.mspersistencedb.core.usecases.DeliveryService;
import br.com.claudiocarige.mspersistencedb.core.usecases.ProductService;
import br.com.claudiocarige.mspersistencedb.infra.adapters.google_maps_service.GoogleAPIDistanceMatrixService;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.CustomerRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.DeliveryRepository;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final ProductService productService;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final GoogleAPIDistanceMatrixService googleAPIDistanceMatrixService;

    private final ConvertClassToDTOService convertClassToDTOService;

    @Autowired
    public DeliveryServiceImpl( DeliveryRepository deliveryRepository,
                                ProductService productService,
                                CustomerRepository customerRepository,
                                ItemRepository itemRepository,
                                GoogleAPIDistanceMatrixService googleAPIDistanceMatrixService,
                                ConvertClassToDTOService convertClassToDTOService ) {

        this.deliveryRepository = deliveryRepository;
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.googleAPIDistanceMatrixService = googleAPIDistanceMatrixService;
        this.convertClassToDTOService = convertClassToDTOService;
    }

    @Override
    public ResponseOfSolicitation requestDelivery( RequestDelivery request ) {

        if( request.quantity() <= 0 ) {
            throw new IllegalArgumentException( "Quantity must be greater than 0" );
        }
        if( request.senderId().equals( request.recipientId() ) ) {
            throw new IllegalArgumentException( "Sender and recipient must be different" );
        }

        Delivery delivery = new Delivery();
        generateDeliveryData( request, delivery );
        //TODO disparar email para sender de confirmação de solicitação de entrega
        //TODO disparar email para recipient com data de recebimento e password
        String message = "Solicitação de entrega confirmada.";
        return new ResponseOfSolicitation( message, delivery.getId(), delivery.getDateSolicitation().toString(),
                delivery.getDateSolicitation().plusDays( 15 ).toString() );
    }

    private void generateDeliveryData( RequestDelivery request, Delivery delivery ) {

        delivery.setSender( customerRepository.findById( request.senderId() )
                .orElseThrow( () -> new NoSuchElementException( "Customer not Found" ) ) );

        delivery.setRecipient( customerRepository.findById( request.recipientId() )
                .orElseThrow( () -> new NoSuchElementException( "Customer not Found" ) ) );
        Product product = intanceProduct( request.productId() );
        calculateTotalShipping( delivery, product );//TODO calcular frete FINALIZAR CALCULO
        delivery.setPasswordDelivery( randomPasswordGenerator() );
        delivery.setStatusDelivery( DeliveryStatus.PENDING );
        Item item = getItem( request, product );
        delivery.addItemInList( item );
        delivery = deliveryRepository.save( delivery );
        item.setDelivery( delivery );
        itemRepository.save( item );
    }


    @Override
    public DeliveryStatus carryOutDelivery( String passwordDelivery, Long deliveryId ) {
        //TODO Buscar delivery
        //TODO enviar mensagem para sender se transação completa, senão verificar retorno
        //TODO alterar status

        return null;
    }

    @Override
    public Boolean CheckIfDeliveryIsCompleted( Long deliveryId ) {

        return false;
    }

    @Override
    public DeliveryDTO findDeliveryById( Long id ) {

        return convertClassToDTOService.convertDeliveryToDTO(
              deliveryRepository.findById( id ).orElseThrow( () -> new NoSuchElementException( "Delivery not Found" ) )
              );
    }

    @Override
    public List< DeliveryDTO > findAllDeliveries() {
        List< Delivery > deliveries = deliveryRepository.findAll();
        return deliveries.stream().map( convertClassToDTOService::convertDeliveryToDTO ).toList();
    }

    private Item getItem( RequestDelivery request, Product product ) {

        if( product.getQuantityStock() < request.quantity() ) {
            throw new InsufficientStockException( "Insufficient stock for the product: " + product.getName() );
        }

        updateStock( product, request.quantity() );
        Item item = new Item();
        item.setProductName( product.getName() );
        item.setItemShippingValue( calcItemShippingValue(product) );
        item.setQuantity( request.quantity() );
        return item;
    }

    private void updateStock( Product product, Integer quantity ) {

        product.setQuantityStock( product.getQuantityStock() - quantity );
        productService.updateProduct( product );

    }

    private BigDecimal calcItemShippingValue( Product product ) {

        return BigDecimal.valueOf( product.getValue().doubleValue() + (
                product.getWeight() * ShippingRates.TX_WEIGHT.getRate() ) + (
                product.getVolume() * ShippingRates.TX_VOLUME.getRate() ) + (
                product.getValue().doubleValue() * ShippingRates.TX_SECURITY.getRate() ) + (
                product.getValue().doubleValue() * ShippingRates.TX_ADM.getRate()));
    }

    private void calculateTotalShipping( Delivery delivery, Product product ) {

        Double distance = searchFromDistance( delivery );
        delivery.setFreightValue( BigDecimal.ZERO );
        delivery.getItemsList().forEach( item ->
                        delivery.setFreightValue( delivery.getFreightValue().add( item.getItemShippingValue() ) ) );
        delivery.setFreightValue(

                delivery.getFreightValue().add( BigDecimal.valueOf( distance * ShippingRates.TX_FUEL.getRate() ) ) );
    }

    private Double searchFromDistance( Delivery delivery ) {

        var senderCity = delivery.getSender().getAddress().getCity();
        var senderState = delivery.getSender().getAddress().getState();
        var recipientCity = delivery.getRecipient().getAddress().getCity();
        var recipientState = delivery.getRecipient().getAddress().getState();
        var origin = senderCity + ", " + senderState;
        var destination = recipientCity + ", " + recipientState;
        return googleAPIDistanceMatrixService.searchFromDistance( origin, destination );
    }

    private Product intanceProduct( Long productId ) {

        return productService.findProductById( productId );
    }

    public String randomPasswordGenerator() {

        Random random = new Random();
        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        var regexPattern = "^(?=(?:\\D*\\d)\\D*$)(?=.*[A-Z]).{6,}$";
        while( true ) {
            StringBuilder randomPassword = new StringBuilder();
            for( int i = 0; i < 6; i++ ) {
                var index = random.nextInt( characters.length() );
                randomPassword.append( characters.charAt( index ) );
            }
            Pattern pattern = Pattern.compile( regexPattern );
            Matcher matcher = pattern.matcher( randomPassword );
            if( matcher.matches() ) {
                return randomPassword.toString();
            }
        }
    }

}
