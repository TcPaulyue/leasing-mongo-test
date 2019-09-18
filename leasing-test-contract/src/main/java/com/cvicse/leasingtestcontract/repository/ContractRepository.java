package com.cvicse.leasingtestcontract.repository;

import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingtestcontract.model.Contract;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ContractRepository extends MongoRepository<Contract, String> {

    Contract findByContent(JSONObject content);

    Contract findByTemplateIdAndCommitId(String templateId,String CommitId);

    @Override
    <S extends Contract> List<S> findAll(Example<S> example);

    List<Contract> findByContractPackageId(String cid);
}