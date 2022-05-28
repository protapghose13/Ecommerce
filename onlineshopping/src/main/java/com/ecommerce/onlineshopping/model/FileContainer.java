package com.ecommerce.onlineshopping.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FileContainer")
@Table(name = "file_container")
public class FileContainer {
    public FileContainer(){}

    public FileContainer(String name, String type, byte[] content){
        this.name = name;
        this.type = type;
        this.content = content;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "type",
            nullable = false
    )
    private String type;

    @Column(
            name="content",
            nullable = false
    )
    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_file_fk"
            )
    )
    private Product product;
}
