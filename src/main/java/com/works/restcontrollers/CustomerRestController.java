package com.works.restcontrollers;

import com.works.documents.ElasticCustomer;
import com.works.repositories.ECustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    final ECustomerRepository eRepo;
    public CustomerRestController(ECustomerRepository eRepo) {
        this.eRepo = eRepo;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ElasticCustomer customer) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", eRepo.save(customer));
        return new ResponseEntity(hm, HttpStatus.OK );
    }

    @GetMapping("/search")
    public ResponseEntity search( @RequestParam String q ) {
        Pageable pageable = PageRequest.of(0, 2 );
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", eRepo.searchEmailAndName(q, pageable ));
        return new ResponseEntity(hm, HttpStatus.OK );
    }


}
