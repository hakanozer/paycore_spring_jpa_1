package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
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

}
