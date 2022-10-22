package com.works.services;

import com.querydsl.core.types.Predicate;
import com.works.entities.Product;
// import com.works.entities.QProduct;
import com.works.repositories.ProCatJoinRepository;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository pRepo;
    final ProCatJoinRepository proRepo;

    public ResponseEntity save(Product product) {
        Map<String, Object> hm = new LinkedHashMap<>();
        product.setDate( new Date() );
        try {
            Optional<Product> optionalProduct = pRepo.findProductByTitleEquals(product.getTitle());
            if (optionalProduct.isPresent() ) {
                throw new Exception("");
            }
            pRepo.save(product);
            hm.put("status", true);
            hm.put("product", product);
        }catch (Exception ex) {
            hm.put("status", false);
        }
        return new ResponseEntity(hm, HttpStatus.OK );
    }


    public ResponseEntity allProduct() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        //hm.put("product", proRepo.allProduct() );
        //hm.put("product", pRepo.queryAllProduct());
        //hm.put("product", pRepo.projectionProduct());
        hm.put("product", pRepo.viewAllProduct());
        return new ResponseEntity(hm, HttpStatus.OK );
    }

    public ResponseEntity allProductCat(Long cid, int pageCount) {
        Pageable pageable = PageRequest.of(pageCount, 2);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("product", pRepo.projectionProductCat(cid, pageable));
        return new ResponseEntity(hm, HttpStatus.OK );
    }

    public ResponseEntity productSearch( String q ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", pRepo.findByTitleContainsOrDetailContainsAllIgnoreCase(q,q));
        return new ResponseEntity(hm, HttpStatus.OK );
    }

    // search - page
    public ResponseEntity searchPage( String q, int pageCount, String s ) {

        // sorted
        Sort.TypedSort<Product> productTypedSort = Sort.sort(Product.class);
        Sort sort = productTypedSort.by(Product::getPrice).ascending();
        if ( s.equals("desc")) {
            sort = productTypedSort.by(Product::getPrice).descending();
        }

        Pageable pageable = PageRequest.of(pageCount, 2, sort);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", pRepo.findByTitleContainsOrDetailContainsOrCategories_TitleContainsAllIgnoreCase(q,q,q, pageable));
        return new ResponseEntity(hm, HttpStatus.OK );
    }

    // queryDSL
    public ResponseEntity xqueryDSL( ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        //QProduct qpr = QProduct.product;
        //Predicate predicate = qpr.title.in("tab");
        //hm.put("status", true);
        //hm.put("result", pRepo.queryFindAll(predicate));
        return new ResponseEntity(hm, HttpStatus.OK );
    }


}
