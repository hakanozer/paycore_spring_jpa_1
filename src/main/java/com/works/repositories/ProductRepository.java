package com.works.repositories;

import com.works.entities.ProCatJoin;
import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from product where title = 'title'
    Optional<Product> findProductByTitleEquals(String title);

    List<Product> findByTitleContainsOrDetailContainsAllIgnoreCase(String title, String detail);

    @Query("select p from Product p")
    List<Product> queryAllProduct();

}