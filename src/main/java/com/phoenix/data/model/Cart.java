package com.phoenix.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Builder
@Data

//@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)// fetches the object and its children persist them to db
    private List<Item> itemList;
    private Double totalPrice;//calculates total price of items inside hte cart
    public void addItem(Item item){// this creates a new cart list at construction time
        if(itemList == null){
            itemList = new ArrayList<>();
        }
        itemList.add(item);
    }

}
