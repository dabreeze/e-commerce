package com.phoenix.data.repository;

import com.phoenix.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Save a new product to the database")
    void saveProductToDatabaseTest(){
        Product product = new Product();
        product.setName("Bamboo chair");
        product.setDescription("World class bamboo");
        product.setPrice(5540);
        product.setQuantity(9);

        assertThat(product.getId()).isNull();
        // save a product to database
        productRepository.save(product);
        log.info("product saved :: {}", product);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Bamboo chair");
        assertThat(product.getPrice()).isEqualTo(5540);
        assertThat(product.getDateCreated()).isNotNull();
    }

    @Test
    @DisplayName("Find an existing product from database")
    void findExistingProductFromDatabaseTest(){
        Product product = productRepository.findById(12L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Mop");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);

        log.info("Product retrieved :: {}", product);
    }

    @Test
    @DisplayName("Find all product in the database")
    void findAllProductsTest(){
        List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Find product by name")
    void findProductByName(){
        Product product = productRepository.findByName("Luxury Mop").orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Mop");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);

        log.info("Product retrieved :: {}", product);

    }

    @Test
    @DisplayName("Update a product attribute to database")
    void updateProductAttributeToDatabase(){
        //check product exist

        Product savedProduct = productRepository.findByName("Luxury Map").orElse(null);
        assertThat(savedProduct.getName()).isNotNull();

        //update product
        assertThat(savedProduct.getName()).isEqualTo("Luxury Map");
        assertThat(savedProduct.getPrice()).isEqualTo(18320);
        savedProduct.setName("Beautiful Mat");
        savedProduct.setPrice(34897);

        // save product
        productRepository.save(savedProduct);
        assertThat(savedProduct.getName()).isEqualTo("Beautiful Mat");
        assertThat(savedProduct.getPrice()).isEqualTo(34897);
    }

}