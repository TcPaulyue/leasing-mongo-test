package com.cvicse.leasingtestcontract;

import com.alibaba.fastjson.JSONArray;
import com.cvicse.leasingtestcontract.model.Contract;
import com.cvicse.leasingtestcontract.repository.ContractRepository;
import com.google.common.collect.Lists;
import com.sun.xml.internal.stream.buffer.AbstractCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeasingTestContractApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMongoAggregation(){
        List<AggregationOperation> operations = Lists.newArrayList();
        operations.add(Aggregation.match(Criteria.where("_id").is("5d823593e48407f927de4c52")));
        //operations.add(Aggregation.lookup("CashFlow","content.content1","_id","content.content1"));
        LookupOperation lookupOperation = LookupOperation.newLookup().from("CashFlow")
                .localField("content.content1")
                .foreignField("name")
                .as("content.content1");
        operations.add(lookupOperation);
        operations.add(Aggregation.project("_id").and("content.content1").as("content.cashFlow"));
       // operations.add(Aggregation.project().and("content.content2").as("content.cashFlow2"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Contract>  contracts= mongoTemplate.aggregate(aggregation,"ContractRequest", Contract.class);
        List<Contract> contracts1 = contracts.getMappedResults();
        System.out.println(contracts1.size());
        for(Contract contract:contracts1)
            System.out.println(contract.toString());
    }

    @Test
    public void test(){
        Contract contract = contractRepository.findById("5d823593e48407f927de4c52").get();
        List<String> ids = new ArrayList<>();
        ids.add(contract.getContent().getString("content1"));
        ids.add(contract.getContent().getString("content2"));
        ids.add(contract.getContent().getString("content3"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("CashFlow","content.content1","id","CashFlow")
        );
    }
}
