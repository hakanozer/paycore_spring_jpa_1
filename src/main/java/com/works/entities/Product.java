package com.works.entities;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotEmpty
    @NotNull
    @Column(unique = true, length = 200)
    private String title;

    @Max(1000000)
    @Min(1)
    @NotNull
    @NumberFormat(pattern = "000000.00")
    private Double price;

    @NotEmpty
    @NotNull
    @Lob
    private String detail;

    @NotNull
    private Date date;


    @PostLoad
    public void postLoad() {
        System.out.println("Product postLoad");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Product postPersist");
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Product prePersist");
    }
}
