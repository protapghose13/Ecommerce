package com.ecommerce.onlineshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Category")
@Table(name = "category")
public class Category {
    public Category(){}

    public Category(Long id, String name, String des){
        this.id = id;
        this.name = name;
        this.description = des;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_active")
    private boolean active = true;

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "category"
    )
    private List<Product> products = new ArrayList<>();
}
