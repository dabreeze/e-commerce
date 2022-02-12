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

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> itemList;

    @Transient
    private Double totalPrice;

    public void addItem(Item item){
        if(itemList == null){
            itemList = new ArrayList<>();
        }
        itemList.add(item);
    }

}
