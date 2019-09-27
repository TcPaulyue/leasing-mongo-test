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
    private RentScheduleRepository mainRentScheduleRepository;

    @Autowired
    private Javers javers;

    private static final Logger logger = LoggerFactory.getLogger(RentService.class);


    /**
     * 根据id取租金计划表
     * @param id
     * @return
     */
    public RentSchedule getRentScheduleById(String id){
        return mainRentScheduleRepository.findById(id).get();
    }


    public RentSchedule getRentScheduleByContractId(String contractId){
        if(this.mainRentScheduleRepository.findRentScheduleByContractId(contractId).isPresent()){
            return mainRentScheduleRepository.getRentScheduleByContractId(contractId);
        }else return null;
    }
    /**
     * 根据id删除租金计划表
     * @param id
     * @return  返回租金计划表的列表
     */
    public List<RentSchedule> deleteRentScheduleById(String id){
        mainRentScheduleRepository.deleteById(id);
        return mainRentScheduleRepository.findAll();
    }

    /**
     * 查询所有的租金计划表
     * @return 返回租金计划表的列表
     */
    public List<RentSchedule> getRentSchedules(){
        return mainRentScheduleRepository.findAll();
    }
    
    
    public List<BasicElements> getBasicElementList(JSONArray jsonArray){
        List<BasicElements> basicElementsList = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            basicElementsList.add(this.getBasicElements(jsonArray.getJSONObject(i)));
        }
        return basicElementsList;
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
    public RentSchedule createRentSchedule(JSONArray params,String contractId){
        List<BasicElements> basicElementsList = this.getBasicElementList(params);
        BasicElements mainBasicElements = basicElementsList.get(0);
        RentSchedule mainRentSchedule = new ModelFactory().getBasicModel(mainBasicElements.getAlgTemplate()
        ,mainBasicElements);
        basicElementsList.remove(mainBasicElements);
        for(BasicElements basicElements:basicElementsList){
            String algTemplate = basicElements.getAlgTemplate();
            RentSchedule rentSchedule = new ModelFactory().getBasicModel(algTemplate,basicElements);
            //todo
        }
        mainRentSchedule = this.setElementsExcludingRentCells(mainRentSchedule);
        mainRentSchedule.setContractId(contractId);
        mainRentSchedule.setName(mainRentSchedule.getId());
        
        this.saveRentSchedule(mainRentSchedule);
        return mainRentSchedule;
    }

    public RentSchedule calculateRentSchedule(JSONObject params,String withmargin){
        BasicElements basicElements = this.getBasicElements(params);
        String algTemplate = basicElements.getAlgTemplate();
        RentSchedule mainRentSchedule = new ModelFactory().getBasicModel(algTemplate,basicElements);
        mainRentSchedule = this.setElementsExcludingRentCells(mainRentSchedule);
        if(withmargin.equals(true))
            return mainRentSchedule;
        else
        {
            mainRentSchedule.rentCells.remove(0);
            return mainRentSchedule;
        }
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
        RentSchedule mainRentScheduleTemp = new ModelFactory().getBasicModel(algTemplate,basicElements);

        RentSchedule mainRentSchedule = mainRentScheduleRepository.findById(id).get();

        mainRentSchedule.setRentCells(mainRentScheduleTemp.getRentCells());
        mainRentSchedule.setCurrentRentSum(mainRentScheduleTemp.getCurrentRentSum());
        mainRentSchedule.setExcludingTaxInterestSum(mainRentScheduleTemp.getExcludingTaxInterestSum());
        mainRentSchedule.setExcludingTaxPrincipalSum(mainRentScheduleTemp.getExcludingTaxPrincipalSum());
        mainRentSchedule.setExcludingTaxXIRR(mainRentScheduleTemp.getExcludingTaxXIRR());
        mainRentSchedule.setInterestSum(mainRentScheduleTemp.getInterestSum());
        mainRentSchedule.setInterestVATSum(mainRentScheduleTemp.getInterestVATSum());
        mainRentSchedule.setPrincipalSum(mainRentScheduleTemp.getPrincipalSum());
        mainRentSchedule.setPrincipalVATSum(mainRentScheduleTemp.getPrincipalVATSum());
        mainRentSchedule.setXIRR(mainRentScheduleTemp.getXIRR());
        mainRentSchedule.setAccountingIRR(mainRentScheduleTemp.getAccountingIRR());
        mainRentSchedule.setExcludingTaxIRR(mainRentScheduleTemp.getExcludingTaxIRR());
        mainRentSchedule.setIRR(mainRentScheduleTemp.getIRR());

        return this.saveRentSchedule(mainRentSchedule);

    }

    public RentSchedule setElementsExcludingRentCells(RentSchedule mainRentSchedule){
        mainRentSchedule.setXIRR(new XIRR()
                .getXIRR(mainRentSchedule.getRentCells(),mainRentSchedule.getHandlingFee()
                        ,mainRentSchedule.getMargin(),mainRentSchedule.getPurchasePrice(),mainRentSchedule.getPrincipal()));      //计算整体的XIRR

        mainRentSchedule.setExcludingTaxXIRR(new ExcludingTaxXIRR()
                .getExcludingTaxXIRR(mainRentSchedule.rentCells,mainRentSchedule.getHandlingFee()
                        ,mainRentSchedule.getMargin(),mainRentSchedule.getPurchasePrice(),mainRentSchedule.firstTax,mainRentSchedule.lastTax));   //计算整体的不含税XIRR

        mainRentSchedule.setPrincipalSum(new PrincipalSum()
                .getPrincipalSum(mainRentSchedule.getRentCells()));  //计算本金之和

        mainRentSchedule.setCurrentRentSum(new CurrentSum()
                .getCurrentSum(mainRentSchedule.getRentCells()));    //计算所有租金之和

        mainRentSchedule.setInterestSum(new InterestSum()
                .getInterestSum(mainRentSchedule.getRentCells()));    //计算所有利息之和

        mainRentSchedule.setExcludingTaxPrincipalSum(new ExcludingTaxPrincipalSum()
                .getExcludingTaxPrincipalSum(mainRentSchedule.getRentCells())); //计算所有不含税本金之和

        mainRentSchedule.setExcludingTaxInterestSum(new ExcludingTaxInterestSum()
                .getExcludingTaxInterestSum(mainRentSchedule.getRentCells()));   //计算所有不含税利息之和

        mainRentSchedule.setPrincipalVATSum(new PrincipalVATSum()
                .getPrincipalVATSum(mainRentSchedule.getRentCells()));   //计算所有本金增值税之和

        mainRentSchedule.setInterestVATSum(new InterestVATSum()
                .getInterestVATSum(mainRentSchedule.getRentCells()));   //计算所有利息增值税之和
        return mainRentSchedule;
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
     * @param mainRentSchedule
     */
    public RentSchedule saveRentSchedule(RentSchedule mainRentSchedule){
        logger.info("save mainRentSchedule");
        return mainRentScheduleRepository.save(mainRentSchedule);
    }


    public JSONArray trackRentScheduleChangesWithJavers(String contractId){
        RentSchedule mainRentSchedule = mainRentScheduleRepository.getRentScheduleByContractId(contractId);
        JSONArray jsonArray = new JSONArray();
        JqlQuery jqlQuery = QueryBuilder.byInstance(mainRentSchedule).build();
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
        RentSchedule mainRentSchedule = mainRentScheduleRepository.getRentScheduleByContractId(contractId);
        JqlQuery jqlQuery = QueryBuilder.byInstance(mainRentSchedule).build();
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery);
        for (CdoSnapshot snapshot : snapshots) {
            if (snapshot.getCommitId().getMajorId() == Integer.parseInt(commitId))
                return JSON.parseObject(javers.getJsonConverter().toJson(snapshot.getState()), RentSchedule.class);
        }
        return null;
    }

}
