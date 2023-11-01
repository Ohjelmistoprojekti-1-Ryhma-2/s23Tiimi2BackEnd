package com.dogstore.dogstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Manufacturer manufacturer = new Manufacturer("Rukka", "Address", "Europe", "123456789", "email@email.com");
        Product product = new Product("Dog Food", "black", "S", 30, manufacturer.getName());
        productRepository.save(product);
        assertNotNull(product.getId());
    }

    @Test
    public void testFindAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        assertEquals(2, productList.size());
    }
}
