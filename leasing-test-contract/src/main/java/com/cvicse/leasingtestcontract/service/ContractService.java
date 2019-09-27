package com.cvicse.leasingtestcontract.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingtestcontract.exception.ContractNotFoundException;
import com.cvicse.leasingtestcontract.model.Contract;
import com.cvicse.leasingtestcontract.payload.ContractRequest;
import com.cvicse.leasingtestcontract.repository.ContractRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);

    public List<Contract> getContractAggregation(String id){
  return null;
    }

    public List<Contract> getAllContract() throws ContractNotFoundException {
        logger.info("contracts returned");
        List<Contract> contracts = contractRepository.findAll();
        List<Contract> contractList = new ArrayList<>();
        for(Contract contract:contracts){
           Contract contract1 = this.getContract(contract.getId());
           contractList.add(contract1);
        }
        return contractList;
    }



    public Contract getContract(String id) throws ContractNotFoundException {
        if (!this.contractRepository.findById(id).isPresent())
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        List<AggregationOperation> operations = Lists.newArrayList();
        operations.add(Aggregation.match(Criteria.where("_id").is(id)));
        
        //operations.add(Aggregation.lookup("CashFlow","content.content1","_id","content.content1"));
        LookupOperation lookupOperation = LookupOperation.newLookup().from("CashFlow")
                .localField("content.cashFlowId")
                .foreignField("name")
                .as("content.cashFlowId");
        operations.add(lookupOperation);


        LookupOperation lookupOperation1 = LookupOperation.newLookup().from("RentSchedule")
                .localField("content.rentScheduleId")
                .foreignField("name")
                .as("content.rentScheduleId");
        operations.add(lookupOperation1);


        //operations.add(Aggregation.project("_id").and("content.content1").as("content.cashFlow"));
        // operations.add(Aggregation.project().and("content.content2").as("content.cashFlow2"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Contract>  contractAggregationResults= mongoTemplate.aggregate(aggregation,"ContractRequest", Contract.class);
        List<Contract> contracts = contractAggregationResults.getMappedResults();
        if(contracts.size()==1)
            return contracts.get(0);
        else return null;
    }

    public Contract createContract(ContractRequest contractRequest) {
        logger.info("contract saved");
        Contract contract = new Contract(contractRequest.getContent());
        contract.setContractPackageId(contractRequest.getContractPackageId());
        return contractRepository.save(contract);
    }

    public Contract createContractByTemplateId(ContractRequest contractRequest,String templateId,String commitId){
        logger.info("create contract from templateId"+templateId);
        Contract contract = new Contract(contractRequest.getContent());
        contract.setBasicElements(contractRequest.getBasicElements());
        contract.setTemplateId(templateId);
        contract.setCommitId(commitId);
        contract.setProcessInstanceId(contractRequest.getProcessInstanceId());
        contractRepository.save(contract);
        logger.info("new Contract's id is "+contractRepository.findByTemplateIdAndCommitId(templateId,commitId).getId());
        return contractRepository.findByTemplateIdAndCommitId(templateId,commitId);
    }

    public Contract updateContract(ContractRequest contractRequest, String id) throws ContractNotFoundException {
        this.contractRepository.findById(id).ifPresent(contract -> {
            contract.content = contractRequest.getContent();
            contract.setBasicElements(contractRequest.getBasicElements());
            contract.setProcessInstanceId(contractRequest.getProcessInstanceId());
            this.contractRepository.save(contract);
        });
        if (!this.contractRepository.findById(id).isPresent()) {
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        }
        return this.contractRepository.findById(id).get();
    }

    public void deleteContract(String id) throws ContractNotFoundException {
        if (!this.contractRepository.findById(id).isPresent())
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        logger.info("contract deleted");
        this.contractRepository.deleteById(id);
    }

}

