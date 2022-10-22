package com.works.repositories;

import com.works.entities.ProCatJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProCatJoinRepository extends JpaRepository<ProCatJoin, Long> {

    @Query(value = "select P.PID, P.TITLE, P.DETAIL, P.PRICE, P.CREATED_BY, C.CID, C.TITLE as CTITLE from " +
            "PRODUCT as P inner join PRODUCT_CATEGORIES as PC on P.PID = PC.PRODUCT_PID " +
            "inner join CATEGORY as C on PC.CATEGORIES_CID = C.CID order by P.PID",
            nativeQuery = true)
    List<ProCatJoin> allProduct();
}
