package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from product where title = 'title'
    Optional<Product> findProductByTitleEquals(String title);

}