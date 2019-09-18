package com.cvicse.leasingcashflow3.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingcashflow3.domain.CashFlow;
import com.cvicse.leasingcashflow3.service.CashFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/cashFlows")
public class CashFlowController {
    @Autowired
    CashFlowService cashFlowService;


    private static final Logger logger = LoggerFactory.getLogger(CashFlowController.class);


    @PostMapping
    public Map<String,CashFlow> getCashFlows(@RequestBody List<String> cashFlowIds){
        logger.info("get all cashFlows");
        return cashFlowService.getCashFlowsByids(cashFlowIds);
        //return cashFlowService.getAllCashFlows();
    }

    @GetMapping("/{id}")
    public CashFlow getCashFlowById(@PathVariable String id
    ,@RequestParam(value = "type", defaultValue = "cashFlowId") String type){
        if(type.equals("cashFlowId")){
            logger.info("get cashFlow by cashFlowId "+id);
            return cashFlowService.getCashFlowById(id);
        }else if(type.equals("contractId")){
            logger.info("get cashFlow by contractId "+id);
            return cashFlowService.getCashFlowByContractId(id);
        }else{
            logger.info("can not get cashFlow");
            return null;
        }
    }


    @PostMapping("/new")
    public CashFlow createCashFlowByContractId(@RequestParam(value = "contractId", defaultValue = "null") String contractId
            ,@RequestBody String name){
        if(contractId.equals("null")){
            logger.info("create cashFlow without contractId");
            return cashFlowService.createCashFlow(name);
        }else{
            logger.info("create cashFlow by contractId");
            return cashFlowService.createCashFlowByContractId(contractId,name);
        }
    }

    @DeleteMapping("/{id}")
    public List<CashFlow> deleteCashFlowById(@PathVariable String id){
        logger.info("delete cashFlow by id "+id);
        return cashFlowService.deleteCashFlowById(id);
    }

    @PutMapping("/{id}")
    public CashFlow updateCashFlowById(@PathVariable String id, @RequestBody JSONObject params){
        logger.info("update cashFlow by id "+id);
        return cashFlowService.updateCashFlowById(id,params);
    }

}
