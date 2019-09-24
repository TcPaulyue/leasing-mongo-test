package com.cvicse.leasingrent.repository;

import com.cvicse.leasingrent.domain.BasicElements;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicElementsRepository extends MongoRepository<BasicElements,String> {

    BasicElements findBasicElementsById(String id);
}