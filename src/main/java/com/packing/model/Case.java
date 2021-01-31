package com.packing.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cases")
@Data
public class Case implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "case_id")
    private Integer id;

    @Column(name = "size_x")
    private Integer sizeX;

    @Column(name = "size_y")
    private Integer sizeY;

    @Column(name = "size_z")
    private Integer sizeZ;


    @OneToMany
    @JoinTable(
            name = "packing",
            joinColumns = {@JoinColumn(name = "case_ID")},
            inverseJoinColumns = {@JoinColumn(name = "product_ID")})
    private List<Product> products;


}
