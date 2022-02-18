package com.phoenix.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Product product;
    private Integer quantityAddedToCart;


    public void setQuantityAddedToCart(){//this validates input before adding to cart at construction
        if(product.getQuantity() >= quantityAddedToCart){
            this.quantityAddedToCart = quantityAddedToCart;
        }else {
            this.quantityAddedToCart = 0;
        }
    }

    public Item(Product product, int quantityAddedToCart) {

            this.quantityAddedToCart = 0;

        this.product = product;
//        return product;
    }

}
