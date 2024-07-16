package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.domain.enums.DeliveryStatus;
import br.com.claudiocarige.mspersistencedb.core.usecases.DeliveryService;


public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public Delivery requestDelivery( Long senderId, Long recipientId, Product product ) {

        return null;
    }

    @Override
    public DeliveryStatus carryOutDelivery( Delivery delivery ) {

        return null;
    }

    @Override
    public boolean CheckIfDeliveryIsCompleted( Long deliveryId ) {

        return false;
    }

}
