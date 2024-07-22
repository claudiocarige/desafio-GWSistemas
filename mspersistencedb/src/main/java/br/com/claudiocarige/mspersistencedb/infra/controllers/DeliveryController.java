package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.DeliveryDTO;
import br.com.claudiocarige.mspersistencedb.core.dtos.RequestDelivery;
import br.com.claudiocarige.mspersistencedb.core.dtos.ResponseOfSolicitation;
import br.com.claudiocarige.mspersistencedb.core.usecases.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "/api/v1/delivery" )
@Tag(name = "Delivery", description = "Contains operations related to the request, " +
            "editing, deletion and confirmation of deliveries features of the company GW SISTEMAS.")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController( DeliveryService deliveryService ) { this.deliveryService = deliveryService; }

    @GetMapping
    @Operation(summary = "Finds All Deliveries", description = "Finds All Deliveries",
            tags = {"Delivery"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Delivery.class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< List< DeliveryDTO > > findAllDeliveries() {

        return ResponseEntity.ok( deliveryService.findAllDeliveries() );
    }

    @GetMapping( "/{id}" )
    @Operation(summary = "Find a Delivery by id", description = "Find a Delivery by id",
            tags = {"Delivery"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Delivery.class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Delivery not found", content = @Content),
            })
    public ResponseEntity< DeliveryDTO > findDeliveryById(
            @Parameter(description = "O ID do Delivery", required = true)
            @PathVariable Long id ) {

        return ResponseEntity.ok( deliveryService.findDeliveryById( id ) );
    }

    @GetMapping( "/check-delivery/{deliveryId}" )
    @Operation(summary = "Check if Delivery is Completed", description = "Check if Delivery is Completed",
            tags = {"Delivery"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Boolean.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Delivery not found", content = @Content),
            })
    public ResponseEntity< Boolean > CheckIfDeliveryIsCompleted(
            @Parameter(description = "O ID do Delivery", required = true)
            @PathVariable Long deliveryId ) {

        return ResponseEntity.ok( deliveryService.CheckIfDeliveryIsCompleted( deliveryId ) );
    }

    @PostMapping( "/request-delivery/save" )
    @Operation(summary = "Save a new Delivery", description = "Save a new Delivery",
            tags = {"Delivery"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseOfSolicitation.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< ResponseOfSolicitation > requestDelivery( @RequestBody RequestDelivery requestDelivery )
                                                                                            throws MessagingException {

        return ResponseEntity.ok().body( deliveryService.requestDelivery( requestDelivery ) );
    }

    @PutMapping( "/delivery-confirmation" )
    @Operation(summary = "Delivery confirmation", description = "Delivery confirmation",
            tags = {"Delivery"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DeliveryDTO.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity<DeliveryDTO> carryOutDelivery(
            @Parameter(description = "O ID do Delivery", required = true)
            @RequestParam Long deliveryId,
            @Parameter(description = "O Código de confirmação para entrega", required = true)
            @RequestParam String confirmationCode )
                                                                                            throws MessagingException {

        return ResponseEntity.ok( deliveryService.carryOutDelivery( confirmationCode, deliveryId ) );
    }

}
