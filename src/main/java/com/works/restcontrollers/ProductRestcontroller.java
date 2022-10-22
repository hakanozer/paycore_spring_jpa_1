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

    @GetMapping("/allCat")
    public ResponseEntity allCat(
            @RequestParam(defaultValue = "1") Long cid,
            @RequestParam(defaultValue = "0") int pageCount) {
        return pService.allProductCat(cid, pageCount);
    }

    @GetMapping("/search/{q}")
    public ResponseEntity search( @PathVariable String q ) {
        return pService.productSearch(q);

    }

    @GetMapping("/searchPage/{q}/{p}/{s}")
    public ResponseEntity searchPage( @PathVariable String q, @PathVariable int p, @PathVariable String s ) {
        return pService.searchPage(q,p,s);
    }



}
