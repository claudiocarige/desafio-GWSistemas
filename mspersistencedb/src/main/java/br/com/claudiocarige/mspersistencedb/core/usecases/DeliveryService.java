package br.com.claudiocarige.mspersistencedb.core.usecases;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;


public interface DeliveryService {

    Delivery requestDelivery( Long senderId, Long recipientId, Product product ); //Solicitar Entrega

    DeliveryStatus carryOutDelivery( Delivery delivery ); //Realizar entrega

    boolean CheckIfDeliveryIsCompleted( Long deliveryId );

}
