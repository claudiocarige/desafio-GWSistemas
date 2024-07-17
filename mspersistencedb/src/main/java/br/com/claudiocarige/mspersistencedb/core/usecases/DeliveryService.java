package br.com.claudiocarige.mspersistencedb.core.usecases;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import br.com.claudiocarige.mspersistencedb.core.dtos.RequestDelivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.ResponseOfSolicitation;


public interface DeliveryService {

    ResponseOfSolicitation requestDelivery( RequestDelivery requestDelivery ); //Solicitar Entrega

    DeliveryStatus carryOutDelivery( String passwordDelivery, Long deliveryId ); //Realizar entrega

    Boolean CheckIfDeliveryIsCompleted( Long deliveryId );

}
