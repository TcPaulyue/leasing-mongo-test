package com.cvicse.leasingcashflow1.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingcashflow1.domain.CashCell;
import com.cvicse.leasingcashflow1.domain.CashFlow;
import com.cvicse.leasingcashflow1.repository.CashFlowRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashFlowService {
    @Autowired
    CashFlowRepository cashFlowRepository;

    private static final Logger logger = LoggerFactory.getLogger(CashFlowService.class);
    /**
     * 获取所有现金流列表
     * @return
     */
    public List<CashFlow> getAllCashFlows(){
        return cashFlowRepository.findAll();
    }

    /**
     * 根据id获取现金流
     * @param id
     * @return
     */
    public CashFlow getCashFlowById(String id){
        return cashFlowRepository.findById(id).get();
    }

    public CashFlow getCashFlowByContractId(String contractId){
        if(cashFlowRepository.findCashFlowByContractId(contractId).isPresent()){
        return cashFlowRepository.getCashFlowByContractId(contractId);
        }else {
            return null;
        }
    }

    public Map<String,CashFlow> getCashFlowsByids(List<String> ids){
        Map<String,CashFlow> cashFlows =new HashMap<>();
        for(String id:ids){
            cashFlows.put(id,cashFlowRepository.findById(id).get());
            //cashFlows.add(cashFlowRepository.findById(id).get());
        }
        return cashFlows;
    }


    public CashFlow createCashFlow(){
        CashFlow cashFlow = new CashFlow();
        CashFlow cashFlow1 = cashFlowRepository.save(cashFlow);
        cashFlow1.setName(cashFlow1.getId());
        return cashFlowRepository.save(cashFlow1);
    }


    public CashFlow createCashFlowByContractId(String id){
        logger.info("create cashFlow service");
        CashFlow cashFlow = new CashFlow();
        cashFlow.setContractId(id);
        CashFlow cashFlow1 = cashFlowRepository.save(cashFlow);
        cashFlow1.setName(cashFlow1.getId());
        return cashFlowRepository.save(cashFlow1);
    }

    /**
     * 根据现金流id删除
     * @param id
     * @return  所有现金流列表
     */
    public List<CashFlow> deleteCashFlowById(String id){
        cashFlowRepository.deleteById(id);
        return cashFlowRepository.findAll();
    }

    /**
     * update by cashFlow id
     * @param id
     * @param
     * @return
     */
    public CashFlow updateCashFlowById(String id, JSONObject params){

//        this.cashFlowRepository.findById(id).ifPresent(cashFlow -> {
//            ArrayList<CashCell> cashCells = new ArrayList<>();
//            for(int i=0;i<params.size();i++){
//                CashCell cashCell = new CashCell();
//                JSONObject jsonObject =params.getJSONObject(i);
//                cashCell.setCash(jsonObject.getDouble("cash"));
//                cashCell.setCashChangeType(jsonObject.getString("cashChangeType"));
//                cashCell.setNumber(jsonObject.getString("number"));
//                cashCell.setChangeDate(jsonObject.getString("changeDate"));
//                cashCells.add(cashCell);
//            }
//            cashFlow.setCashCells(cashCells);
//            cashFlowRepository.save(cashFlow);
//        });
//        if (!this.cashFlowRepository.findById(id).isPresent()) {
//            return null;
//        }
        this.cashFlowRepository.findById(id).ifPresent(cashFlow -> {
            CashCell cashCell = new CashCell();
            cashCell.setCash(params.getDouble("cash"));
            cashCell.setCashChangeType(params.getString("cashChangeType"));
            cashCell.setNumber(params.getString("number"));
            cashCell.setChangeDate(params.getString("changeDate"));
            cashFlow.getCashCells().add(cashCell);
            cashFlowRepository.save(cashFlow);
        });
        return cashFlowRepository.findById(id).get();
    }

}
