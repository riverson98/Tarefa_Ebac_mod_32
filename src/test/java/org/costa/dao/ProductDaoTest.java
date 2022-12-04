package org.costa.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rcosta.dao.ProductDao;
import org.rcosta.dao.impl.ProductDaoImpl;
import org.rcosta.domain.Product;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductDaoTest {

    ProductDao productDao;

    public ProductDaoTest() {
        productDao = new ProductDaoImpl();
    }
    Product product;

    @BeforeEach
    public void initializeProductObjectBeforeTesting(){
        product = new Product(
                "Tv",
                2000d
        );
    }

    @AfterEach
    public void cleanDatabaseAfterTest(){
        for (Product product: productDao.findAll()) {
            productDao.delete(product);
        }
    }

    @Test
    public void shouldRegisterProduct(){
        product = productDao.register(product);
        assertNotNull(product);
        assertNotNull(product.getId());
    }

    @Test
    public void shouldDeleteProductById() {
        product = productDao.register(product);

        productDao.deleteById(product.getId());
        assertEquals(0, productDao.findAll().size());
    }

    @Test
    public void shouldSearchAll(){
        Product anotherProduct = new Product(
                "smartphone",
                3499.99
        );
        productDao.register(product);
        productDao.register(anotherProduct);
        assertEquals(2, productDao.findAll().size());
    }
    @Test
    public void shouldSearchById(){
        productDao.register(product);
            var productFindById = productDao.findById(product.getId());
        assertEquals(product.getId(), productFindById.getId());
    }

    @Test
    public void shouldUpdateProductInformation(){
        productDao.register(product);
        var product1 = productDao.findById(product.getId());
        product.setName("notebook");
        product.setPrice(1999.99);
        var updatedProduct = productDao.updatesInformation(product);
        assertNotEquals(product1.getName(), updatedProduct.getName());
        assertNotEquals(product1.getPrice(), updatedProduct.getPrice());
    }
}
