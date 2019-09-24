package com.cvicse.leasingrent.domain;

import org.joda.time.DateTime;


public class RentCell {

    private DateTime payDate;   //支付日

    private double currentRent;   //当期租金
    private double principal;     //本金
    private double interest;      //利息
    private double remainingPrincipal;    //剩余本金

    private double excludingTaxPrincipal;  //不含税本金
    private double excludingTaxInterest;   //不含税利息
    private double principalVAT;     //本金增值税
    private double interestVAT;     //利息增值税

    private double handlingFee;     //手续费
    private double margin;     //保证金
    private double purchasePrice;  //留购价款

    private double XIRR;     //XIRR
    private double IRR;    //IRR
    private double excludingTaxXIRR;   //不含税XIRR
    private double excludingTaxIRR;    //不含税IRR
    private double accountingIRR;      //会计IRR

    public double getPrincipal() {
        return principal;
    }

    public double getAccountingIRR() {
        return accountingIRR;
    }

    public double getCurrentRent() {
        return currentRent;
    }

    public double getExcludingTaxInterest() {
        return excludingTaxInterest;
    }

    public double getExcludingTaxIRR() {
        return excludingTaxIRR;
    }

    public double getExcludingTaxPrincipal() {
        return excludingTaxPrincipal;
    }

    public double getExcludingTaxXIRR() {
        return excludingTaxXIRR;
    }

    public double getHandlingFee() {
        return handlingFee;
    }

    public double getInterest() {
        return interest;
    }

    public double getInterestVAT() {
        return interestVAT;
    }

    public double getIRR() {
        return IRR;
    }

    public double getMargin() {
        return margin;
    }

    public double getPrincipalVAT() {
        return principalVAT;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public double getXIRR() {
        return XIRR;
    }

    public DateTime getPayDate() {
        return payDate;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setAccountingIRR(double accountingIRR) {
        this.accountingIRR = accountingIRR;
    }

    public void setCurrentRent(double currentRent) {
        this.currentRent = currentRent;
    }

    public void setExcludingTaxInterest(double excludingTaxInterest) {
        this.excludingTaxInterest = excludingTaxInterest;
    }

    public void setExcludingTaxIRR(double excludingTaxIRR) {
        this.excludingTaxIRR = excludingTaxIRR;
    }

    public void setExcludingTaxPrincipal(double excludingTaxPrincipal) {
        this.excludingTaxPrincipal = excludingTaxPrincipal;
    }

    public void setExcludingTaxXIRR(double excludingTaxXIRR) {
        this.excludingTaxXIRR = excludingTaxXIRR;
    }

    public void setHandlingFee(double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setInterestVAT(double interestVAT) {
        this.interestVAT = interestVAT;
    }

    public void setIRR(double IRR) {
        this.IRR = IRR;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public void setPayDate(DateTime payDate) {
        this.payDate = payDate;
    }

    public void setPrincipalVAT(double principalVAT) {
        this.principalVAT = principalVAT;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setRemainingPrincipal(double remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public void setXIRR(double XIRR) {
        this.XIRR = XIRR;
    }
}
