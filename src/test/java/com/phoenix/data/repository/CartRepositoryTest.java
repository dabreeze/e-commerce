package com.phoenix.data.repository;

import com.phoenix.data.model.Cart;
import com.phoenix.data.model.Item;
import com.phoenix.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

//import static java.awt.Container.log;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"/db/insert.sql"})
@SpringBootTest
@Slf4j
class CartRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;



    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Add product to cart")

    void addProductToCartTest(){
        Product product = productRepository.findByName("Macbook Air").orElse(null);
        assertThat(product).isNotNull();

        Item item = new Item(product, 2);
        Cart cart = new Cart();
        cart.addItem(item);
        cartRepository.save(cart);

        assertThat(cart.getId()).isNotNull();
        assertThat(cart.getItemList().isEmpty()).isFalse();
        assertThat(cart.getItemList().get(0).getProduct()).isNotNull();
        log.info("");
//        Cart cart =
    }
}