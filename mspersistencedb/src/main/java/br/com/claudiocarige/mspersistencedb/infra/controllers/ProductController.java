package br.com.claudiocarige.mspersistencedb.infra.controllers;


import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.usecases.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "/api/v1/products" )
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController( ProductService productService ) { this.productService = productService; }

    @GetMapping( "/{id}" )
    public ResponseEntity< Product > findProductById( @PathVariable Long id ) {

        return ResponseEntity.ok().body( productService.findProductById( id ) );
    }

    @GetMapping
    public ResponseEntity< List< Product > > findAllProducts() {

        return ResponseEntity.ok().body( productService.findAllProducts() );
    }

    @PostMapping( "/save" )
    public ResponseEntity< Product > saveProduct( @RequestBody Product product ) {

        return ResponseEntity.ok().body( productService.saveProduct( product ) );
    }

    @PutMapping
    public ResponseEntity< Product > updateProduct( @RequestBody Product product ) {

        return ResponseEntity.ok().body( productService.updateProduct( product ) );
    }

    @DeleteMapping
    public ResponseEntity< Void > deleteProduct( Long id ) {

        productService.deleteProduct( id );
        return ResponseEntity.noContent().build();
    }

}
