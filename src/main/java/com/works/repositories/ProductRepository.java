package com.works.repositories;

import com.works.entities.ProCatJoin;
import com.works.entities.Product;
import com.works.projections.ProCat;
import com.works.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.Set;

//public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from product where title = 'title'
    Optional<Product> findProductByTitleEquals(String title);

    List<Product> findByTitleContainsOrDetailContainsAllIgnoreCase(String title, String detail);

    @Query("select p from Product p")
    List<Product> queryAllProduct();

    // use projection
    @Query(value = "select P.PID, P.TITLE, P.DETAIL, P.PRICE, C.CID, C.TITLE as CTITLE from\n" +
            "    PRODUCT as P inner join PRODUCT_CATEGORIES as PC on P.PID = PC.PRODUCT_PID\n" +
            "    inner join CATEGORY as C on PC.CATEGORIES_CID = C.CID order by P.PID", nativeQuery = true)
    List<ProCat> projectionProduct();

    @Query(value = "select PRODUCT.* from\n" +
            "        PRODUCT inner join PRODUCT_CATEGORIES as PC on PRODUCT.PID = PC.PRODUCT_PID\n" +
            "        inner join CATEGORY as C on PC.CATEGORIES_CID = C.CID where C.CID = ?1", nativeQuery = true)
    Page<List<ProCat>> projectionProductCat( Long cid, Pageable pageable );


    // search - page - sort
    Page<List<Product>> findByTitleContainsOrDetailContainsOrCategories_TitleContainsAllIgnoreCase(String title, String detail, String title1, Pageable pageable);

    // call view
    @Query(value = "SELECT * FROM JOIN_VIEW", nativeQuery = true)
    Set<ProCat> viewAllProduct();

    // Storge Procedure
    //@Query("CALL procedure_name(?1)")
    //Set<ProCat> viewAllProductPro(Boolean price);


}