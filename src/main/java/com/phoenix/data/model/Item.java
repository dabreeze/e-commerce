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




    public void setQuantityAddedToCart(){
        if(quantityAddedToCart <= product.getQuantity()){
            this.quantityAddedToCart = quantityAddedToCart;
        }

    }

    public Item(Product product, int quantityAddedToCart) {
        if(quantityAddedToCart <= product.getQuantity()){
            this.quantityAddedToCart = quantityAddedToCart;

        }
        this.product = product;
//        return product;
    }

}
