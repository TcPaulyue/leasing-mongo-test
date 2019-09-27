package com.cvicse.leasingrent.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Document("RentSchedule")
public class RentSchedule {
    @Id
    private String id;

    private String name;

    private String contractId;   //合同Id

    private double principal;   //本金

    public double firstTax;  //三金税

    public double lastTax;


    public ArrayList<RentCell> rentCells = new ArrayList<>();
    private double currentRentSum;    //当期租金之和
    private double principalSum;       //本金之和
    private double interestSum;        //利息之和
    private double excludingTaxPrincipalSum;   //不含税本金之和
    private double excludingTaxInterestSum;    //不含税利息之和
    private double principalVATSum;          //本金增值税之和
    private double interestVATSum;         //利息增值税之和
    private double XIRR;                //XIRR
    private double IRR;                 //IRR
    private double excludingTaxXIRR;    //不含税XIRR
    private double excludingTaxIRR;     //不含税IRR
    private double accountingIRR;      //会计IRR

    private double handlingFee;    //手续费  例如：4000000.00
    private double margin;         //保证金  例如：24400000.00
    private double purchasePrice;  //留购价款  例如：1000.00


    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setXIRR(double XIRR) {
        this.XIRR = XIRR;
    }

    public void setIRR(double IRR) {
        this.IRR = IRR;
    }

    public void setExcludingTaxXIRR(double excludingTaxXIRR) {
        this.excludingTaxXIRR = excludingTaxXIRR;
    }

    public void setExcludingTaxIRR(double excludingTaxIRR) {
        this.excludingTaxIRR = excludingTaxIRR;
    }

    public void setAccountingIRR(double accountingIRR) {
        this.accountingIRR = accountingIRR;
    }

    public void setCurrentRentSum(double currentRentSum) {
        this.currentRentSum = currentRentSum;
    }

    public void setRentCells(ArrayList<RentCell> rentCells) {
        this.rentCells = rentCells;
    }

    public void setExcludingTaxInterestSum(double excludingTaxInterestSum) {
        this.excludingTaxInterestSum = excludingTaxInterestSum;
    }

    public void setExcludingTaxPrincipalSum(double excludingTaxPrincipalSum) {
        this.excludingTaxPrincipalSum = excludingTaxPrincipalSum;
    }

    public void setInterestSum(double interestSum) {
        this.interestSum = interestSum;
    }

    public void setInterestVATSum(double interestVATSum) {
        this.interestVATSum = interestVATSum;
    }

    public void setPrincipalSum(double principalSum) {
        this.principalSum = principalSum;
    }

    public void setPrincipalVATSum(double principalVATSum) {
        this.principalVATSum = principalVATSum;
    }

    public double getXIRR() {
        return XIRR;
    }

    public double getIRR() {
        return IRR;
    }

    public double getExcludingTaxXIRR() {
        return excludingTaxXIRR;
    }

    public double getExcludingTaxIRR() {
        return excludingTaxIRR;
    }

    public double getAccountingIRR() {
        return accountingIRR;
    }

    public ArrayList<RentCell> getRentCells() {
        return rentCells;
    }

    public double getCurrentRentSum() {
        return currentRentSum;
    }

    public double getExcludingTaxInterestSum() {
        return excludingTaxInterestSum;
    }

    public double getExcludingTaxPrincipalSum() {
        return excludingTaxPrincipalSum;
    }

    public double getInterestSum() {
        return interestSum;
    }

    public double getInterestVATSum() {
        return interestVATSum;
    }

    public double getPrincipalSum() {
        return principalSum;
    }

    public double getPrincipalVATSum() {
        return principalVATSum;
    }
}
