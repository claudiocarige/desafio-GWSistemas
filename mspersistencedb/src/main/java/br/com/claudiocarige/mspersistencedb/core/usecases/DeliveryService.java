package br.com.claudiocarige.mspersistencedb.core.usecases;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import br.com.claudiocarige.mspersistencedb.core.dtos.DeliveryDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.RequestDelivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.ResponseOfSolicitation;
import jakarta.mail.MessagingException;

import java.util.List;


public interface DeliveryService {

    ResponseOfSolicitation requestDelivery( RequestDelivery requestDelivery ) throws MessagingException;

    DeliveryDTO carryOutDelivery( String passwordDelivery, Long deliveryId ) throws MessagingException;

    Boolean CheckIfDeliveryIsCompleted( Long deliveryId );

    DeliveryDTO findDeliveryById( Long id );

    List<DeliveryDTO> findAllDeliveries();

}
