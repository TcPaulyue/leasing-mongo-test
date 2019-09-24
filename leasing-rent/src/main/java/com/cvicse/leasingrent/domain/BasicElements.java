package com.cvicse.leasingrent.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 租赁所需的基本要素
* */


@Document(collection = "BasicElements")
public class BasicElements{

    @Id
    private String id;


    private String startDate;   //开始时间 例如：2019-04-20T00:00:00.000+08:00
    private String endDate;     //结束时间 例如：2021-04-20T00:00:00.000+08:00
    private Integer yearCountDays;     //年计算天数  例如：360

    private double annualInterestRate;   //年利率  例如：0.052250
    private double principal;         //租赁本金   例如：200000000.00
    private double assetValue;     //资产余值    例如：0.00

    private Integer leasePeriod;       //租期   例如：24
    private Integer rentalFrequency;    //租金收取频次  例如：3
    private Integer repaymentDate;     //每期还款日  例如：10

    private String payTag;   //先付后付标志  例如：后付
    private String taxPlanType; //税务方案类型  例如：直租

    private double inputTaxRate;  //进项税税率   例如：0.1
    private double principalInterestRate;  //本金利息税率  例如：0.13
    private double tariffRate;     //手续费税率  例如：0.13

    private double handlingFee;    //手续费  例如：4000000.00
    private double margin;         //保证金  例如：24400000.00
    private double purchasePrice;  //留购价款  例如：1000.00

    private String algTemplate;   //租金计算模板  例如：等额本金


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getAlgTemplate() {
        return algTemplate;
    }
    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getAssetValue() {
        return assetValue;
    }

    public double getInputTaxRate() {
        return inputTaxRate;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getPrincipalInterestRate() {
        return principalInterestRate;
    }

    public double getTariffRate() {
        return tariffRate;
    }

    public Integer getLeasePeriod() {
        return leasePeriod;
    }

    public Integer getRentalFrequency() {
        return rentalFrequency;
    }

    public Integer getRepaymentDate() {
        return repaymentDate;
    }

    public Integer getYearCountDays() {
        return yearCountDays;
    }

    public String getPayTag() {
        return payTag;
    }

    public String getTaxPlanType() {
        return taxPlanType;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setInputTaxRate(double inputTaxRate) {
        this.inputTaxRate = inputTaxRate;
    }

    public void setLeasePeriod(Integer leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public void setPayTag(String payTag) {
        this.payTag = payTag;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setPrincipalInterestRate(double principalInterestRate) {
        this.principalInterestRate = principalInterestRate;
    }

    public void setRentalFrequency(Integer rentalFrequency) {
        this.rentalFrequency = rentalFrequency;
    }

    public void setRepaymentDate(Integer repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTariffRate(double tariffRate) {
        this.tariffRate = tariffRate;
    }

    public void setTaxPlanType(String taxPlanType) {
        this.taxPlanType = taxPlanType;
    }

    public void setYearCountDays(Integer yearCountDays) {
        this.yearCountDays = yearCountDays;
    }

    public void setAlgTemplate(String algTemplate) {
        this.algTemplate = algTemplate;
    }
}
