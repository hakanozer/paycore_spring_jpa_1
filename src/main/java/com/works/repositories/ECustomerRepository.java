package com.works.repositories;

import com.works.documents.ElasticCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ECustomerRepository extends ElasticsearchRepository<ElasticCustomer, String> {

    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"email\":\"?0\"}},{\"match\":{\"name\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}")
    Page<ElasticCustomer> searchEmailAndName(String data, Pageable pageable);

    List<ElasticCustomer> findByEmailContainsOrCidContains(String email, String cid);

}