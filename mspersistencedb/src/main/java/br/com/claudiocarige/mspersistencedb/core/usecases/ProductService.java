package br.com.claudiocarige.mspersistencedb.core.usecases;

import br.com.claudiocarige.mspersistencedb.core.domain.entities.Product;

import java.util.List;


public interface ProductService {


    Product findProductById( Long id);

    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
