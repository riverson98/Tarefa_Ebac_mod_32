package org.rcosta.dao;

import org.rcosta.domain.Product;

import java.util.Collection;

public interface ProductDao {

    Product register(Product product);

    void delete(Product product);

    void deleteById(Long id);

    Collection<Product> findAll();

    Product findById(Long id);

    Product updatesInformation(Product product);

}
