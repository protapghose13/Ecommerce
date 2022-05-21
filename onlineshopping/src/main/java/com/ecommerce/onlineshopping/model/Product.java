package com.ecommerce.onlineshopping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    private int quantity;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "brand_id")
    private int brandId;

    private int purchases;

    private int views;
}
