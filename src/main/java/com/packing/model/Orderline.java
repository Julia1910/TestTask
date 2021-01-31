package com.packing.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderline")
@Data
public class Orderline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderline_id")
    private Integer id;

    @Column(name = "number_of_products")
    private Integer numberOfProducts;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
