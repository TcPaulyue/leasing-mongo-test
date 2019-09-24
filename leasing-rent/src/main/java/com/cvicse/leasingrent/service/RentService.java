package com.cvicse.leasingrent.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingrent.algorithm.*;
import com.cvicse.leasingrent.calculationModel.ModelFactory;
import com.cvicse.leasingrent.domain.BasicElements;
import com.cvicse.leasingrent.domain.RentCell;
import com.cvicse.leasingrent.domain.RentSchedule;
import com.cvicse.leasingrent.repository.BasicElementsRepository;
import com.cvicse.leasingrent.repository.RentScheduleRepository;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentService {

    @Autowired
    private BasicElementsRepository basicElementsRepository;

    @Autowired
    private RentScheduleRepository rentScheduleRepository;

    @Autowired
    private Javers javers;

    private static final Logger logger = LoggerFactory.getLogger(RentService.class);


    /**
     * 根据id取租金计划表
     * @param id
     * @return
     */
    public RentSchedule getRentScheduleById(String id){
        return rentScheduleRepository.findById(id).get();
    }


    public RentSchedule getRentScheduleByContractId(String contractId){
        if(this.rentScheduleRepository.findRentScheduleByContractId(contractId).isPresent()){
            return rentScheduleRepository.getRentScheduleByContractId(contractId);
        }else return null;
    }
    /**
     * 根据id删除租金计划表
     * @param id
     * @return  返回租金计划表的列表
     */
    public List<RentSchedule> deleteRentScheduleById(String id){
        rentScheduleRepository.deleteById(id);
        return rentScheduleRepository.findAll();
    }

    /**
     * 查询所有的租金计划表
     * @return 返回租金计划表的列表
     */
    public List<RentSchedule> getRentSchedules(){
        return rentScheduleRepository.findAll();
    }

    /**
     * 根据前端传来的参数生成BasicElement
     * @param params 前端传来的参数
     * @return  生成一个basicElements
     */
    public BasicElements getBasicElements(JSONObject params){
        BasicElements basicElements = new BasicElements();
        basicElements.setStartDate(params.getString("startDate"));
        basicElements.setEndDate(params.getString("endDate"));
        basicElements.setYearCountDays(params.getInteger("yearCountDays"));
        basicElements.setAnnualInterestRate(params.getDouble("annualInterestRate"));
        basicElements.setPrincipal(params.getDouble("principal"));
        basicElements.setAssetValue(params.getDouble("assetValue"));
        basicElements.setLeasePeriod(params.getInteger("leasePeriod"));
        basicElements.setRentalFrequency(params.getInteger("rentalFrequency"));
        basicElements.setRepaymentDate(params.getInteger("repaymentDate"));
        basicElements.setPayTag(params.getString("payTag"));
        basicElements.setTaxPlanType(params.getString("taxPlanType"));
        basicElements.setInputTaxRate(params.getDouble("inputTaxRate"));
        basicElements.setPrincipalInterestRate(params.getDouble("principalInterestRate"));
        basicElements.setTariffRate(params.getDouble("tariffRate"));
        basicElements.setAlgTemplate(params.getString("algTemplate"));
        basicElements.setHandlingFee(params.getDouble("handlingFee"));
        basicElements.setMargin(params.getDouble("margin"));
        basicElements.setPurchasePrice(params.getDouble("purchasePrice"));
        return basicElements;
    }


    /**
     * 根据不同的租金模板生成相应的租金计划表
     * @param params
     * @return
     */
    public RentSchedule createRentSchedule(JSONObject params,String contractId){
        BasicElements basicElements = this.getBasicElements(params);
        basicElementsRepository.save(basicElements);
        String algTemplate = basicElements.getAlgTemplate();
        RentSchedule rentSchedule = new ModelFactory().getBasicModel(algTemplate,basicElements);
        rentSchedule.setContractId(contractId);
        this.saveRentSchedule(rentSchedule);
        return rentSchedule;
    }

    public RentSchedule calculateRentSchedule(JSONObject params){
        BasicElements basicElements = this.getBasicElements(params);
        String algTemplate = basicElements.getAlgTemplate();
        RentSchedule rentSchedule = new ModelFactory().getBasicModel(algTemplate,basicElements);
        return rentSchedule;
    }

    /**
     * 更新租金计划表
     * @param id
     * @param params
     * @return
     */
    public RentSchedule updateRentScheduleById(String id,JSONObject params){
        BasicElements basicElements = this.getBasicElements(params);
        String algTemplate = basicElements.getAlgTemplate();
        RentSchedule rentScheduleTemp = new ModelFactory().getBasicModel(algTemplate,basicElements);

        RentSchedule rentSchedule = rentScheduleRepository.findById(id).get();

        rentSchedule.setRentCells(rentScheduleTemp.getRentCells());
        rentSchedule.setCurrentRentSum(rentScheduleTemp.getCurrentRentSum());
        rentSchedule.setExcludingTaxInterestSum(rentScheduleTemp.getExcludingTaxInterestSum());
        rentSchedule.setExcludingTaxPrincipalSum(rentScheduleTemp.getExcludingTaxPrincipalSum());
        rentSchedule.setExcludingTaxXIRR(rentScheduleTemp.getExcludingTaxXIRR());
        rentSchedule.setInterestSum(rentScheduleTemp.getInterestSum());
        rentSchedule.setInterestVATSum(rentScheduleTemp.getInterestVATSum());
        rentSchedule.setPrincipalSum(rentScheduleTemp.getPrincipalSum());
        rentSchedule.setPrincipalVATSum(rentScheduleTemp.getPrincipalVATSum());
        rentSchedule.setXIRR(rentScheduleTemp.getXIRR());
        rentSchedule.setAccountingIRR(rentScheduleTemp.getAccountingIRR());
        rentSchedule.setExcludingTaxIRR(rentScheduleTemp.getExcludingTaxIRR());
        rentSchedule.setIRR(rentScheduleTemp.getIRR());

        return this.saveRentSchedule(rentSchedule);

    }

    /**
     * 暂存
     * @param contractId
     * @param params
     * @return
     */
    public RentSchedule temporarySaveRentScheduleByContractId(String contractId,JSONObject params){
        BasicElements basicElements = this.getBasicElements(params);
        String algTemplate = basicElements.getAlgTemplate();
        return new ModelFactory().getBasicModel(algTemplate,basicElements);
    }

    /**
     * 保存租金计划表到数据库
     * @param rentSchedule
     */
    public RentSchedule saveRentSchedule(RentSchedule rentSchedule){
        logger.info("save rentSchedule");
        return rentScheduleRepository.save(rentSchedule);
    }


    public JSONArray trackRentScheduleChangesWithJavers(String contractId){
        RentSchedule rentSchedule = rentScheduleRepository.getRentScheduleByContractId(contractId);
        JSONArray jsonArray = new JSONArray();
        JqlQuery jqlQuery = QueryBuilder.byInstance(rentSchedule).build();
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery);
        for (CdoSnapshot snapshot : snapshots) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commitId", snapshot.getCommitId().getMajorId());
            jsonObject.put("commitDate", snapshot.getCommitMetadata().getCommitDate());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public RentSchedule getRentScheduleWithJaversCommitId(String contractId, String commitId){
        RentSchedule rentSchedule = rentScheduleRepository.getRentScheduleByContractId(contractId);
        JqlQuery jqlQuery = QueryBuilder.byInstance(rentSchedule).build();
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery);
        for (CdoSnapshot snapshot : snapshots) {
            if (snapshot.getCommitId().getMajorId() == Integer.parseInt(commitId))
                return JSON.parseObject(javers.getJsonConverter().toJson(snapshot.getState()), RentSchedule.class);
        }
        return null;
    }

}
