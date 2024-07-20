package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.DeliveryDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.RequestDelivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.ResponseOfSolicitation;
import br.com.claudiocarige.mspersistencedb.core.usecases.DeliveryService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "/api/v1/delivery" )
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController( DeliveryService deliveryService ) { this.deliveryService = deliveryService; }

    @GetMapping
    public ResponseEntity< List< DeliveryDTO > > findAllDeliveries() {

        return ResponseEntity.ok( deliveryService.findAllDeliveries() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity< DeliveryDTO > findDeliveryById( @PathVariable Long id ) {

        return ResponseEntity.ok( deliveryService.findDeliveryById( id ) );
    }

    @GetMapping( "/check-delivery/{deliveryId}" )
    public ResponseEntity< Boolean > CheckIfDeliveryIsCompleted( @PathVariable Long deliveryId ) {

        return ResponseEntity.ok( deliveryService.CheckIfDeliveryIsCompleted( deliveryId ) );
    }

    @PostMapping( "/request-delivery/save" )
    public ResponseEntity< ResponseOfSolicitation > requestDelivery( @RequestBody RequestDelivery requestDelivery )
                                                                                            throws MessagingException {

        return ResponseEntity.ok().body( deliveryService.requestDelivery( requestDelivery ) );
    }

    @PutMapping( "/delivery-confirmation" )
    public ResponseEntity<DeliveryDTO> carryOutDelivery( @RequestParam Long deliveryId, String confirmationCode )
                                                                                            throws MessagingException {

        return ResponseEntity.ok( deliveryService.carryOutDelivery( confirmationCode, deliveryId ) );
    }

}
