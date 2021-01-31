package com.packing.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "packing")
@Data
public class Packing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "case_ID")
    private Case aCase;

    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product;
}
