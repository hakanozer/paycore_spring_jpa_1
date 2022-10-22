package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestcontroller {

    final ProductService pService;

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Product product) {
        return pService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity all() {
       return pService.allProduct();
    }

    @GetMapping("/search/{q}")
    public ResponseEntity search( @PathVariable String q ) {
        return pService.productSearch(q);
    }

}
