package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 100)
    private String title;

}
