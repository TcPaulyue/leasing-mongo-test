package com.cvicse.leasingtestcontract.repository;

import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingtestcontract.model.Contract;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;


public interface ContractRepository extends MongoRepository<Contract, String> {

    Contract findByContent(JSONObject content);

    Contract findByTemplateIdAndCommitId(String templateId,String CommitId);

    @Override
    <S extends Contract> List<S> findAll(Example<S> example);

    List<Contract> findByContractPackageId(String cid);


}