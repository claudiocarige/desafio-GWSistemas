package br.com.claudiocarige.mspersistencedb.core.usecases.impl;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;
import br.com.claudiocarige.mspersistencedb.core.exceptions.NoSuchElementException;
import br.com.claudiocarige.mspersistencedb.core.usecases.ProductService;
import br.com.claudiocarige.mspersistencedb.infra.persistence.repositories.postgresrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl( ProductRepository productRepository ) { this.productRepository = productRepository; }

    @Override
    public Product findProductById( Long id ) {

        return productRepository.findById( id ).orElseThrow( () -> new NoSuchElementException( "Product not found" ) );
    }

    @Override
    public List< Product > findAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product saveProduct( Product product ) {

        return productRepository.save( product );
    }

    @Override
    public Product updateProduct( Product product ) {

        return productRepository.save( product );
    }

    @Override
    public void deleteProduct( Long id ) {

        productRepository.deleteById( id );
    }

}
