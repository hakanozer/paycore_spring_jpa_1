package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ProCatJoin {

    @Id
    private Long pid;
    private String title;
    private String detail;
    private Double price;
    private String createdBy;
    private Long cid;
    private String ctitle;

}
