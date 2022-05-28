package com.ecommerce.onlineshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "code",
            nullable = false
    )
    private String code;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "unit_price",
            nullable = false
    )
    private double unitPrice;

    @Column(
            name = "quantity",
            nullable = false
    )
    private int quantity;

    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(
            name = "brand_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_brand_fk"
            )
    )
    private Brand brand;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "product_category_fk"
            )
    )
    private Category category;

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "product"
    )
    private List<FileContainer> files = new ArrayList<>();

    private int purchases;

    private int views;

    @Transient
    private Long brandId;

    @Transient
    private Long categoryId;
}
