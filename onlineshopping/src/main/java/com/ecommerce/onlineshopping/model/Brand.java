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
@Entity(name = "Brand")
@Table(name = "brand")
public class Brand {
    public Brand(){}

    public Brand(Long id, String name, String desc){
        this.id = id;
        this.name = name;
        this.description = desc;
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

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "brand"
    )
    private List<Product> products = new ArrayList<>();
}
