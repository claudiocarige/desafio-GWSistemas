package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Delivery;
import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.usecases.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "/api/v1/products" )
@Tag(name = "Product", description = "Contains operations related to insertion, editing, " +
                                     "deletion and product searches functionalities")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController( ProductService productService ) { this.productService = productService; }

    @GetMapping( "/{id}" )
    @Operation(summary = "Find a Product by id", description = "Find a Product by id",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product  .class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            })
    public ResponseEntity< Product > findProductById( @PathVariable Long id ) {

        return ResponseEntity.ok().body( productService.findProductById( id ) );
    }

    @GetMapping
    @Operation(summary = "Finds All Product", description = "Finds All Product",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product  .class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< List< Product > > findAllProducts() {

        return ResponseEntity.ok().body( productService.findAllProducts() );
    }

    @PostMapping( "/save" )
    @Operation(summary = "Save a Product", description = "Save a Product",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product  .class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< Product > saveProduct( @RequestBody Product product ) {

        return ResponseEntity.ok().body( productService.saveProduct( product ) );
    }

    @PutMapping
    @Operation(summary = "Update a Product", description = "Update a Product",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product  .class))
                                    )
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< Product > updateProduct( @RequestBody Product product ) {

        return ResponseEntity.ok().body( productService.updateProduct( product ) );
    }

    @DeleteMapping
    @Operation(summary = "Delete a Product", description = "Delete a Product",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Void.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            })
    public ResponseEntity< Void > deleteProduct( Long id ) {

        productService.deleteProduct( id );
        return ResponseEntity.noContent().build();
    }

}
