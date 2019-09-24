package com.cvicse.leasingrent.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Document("RentSchedule")
public class RentSchedule {
    @Id
    private String id;

    private String contractId;   //合同Id


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
