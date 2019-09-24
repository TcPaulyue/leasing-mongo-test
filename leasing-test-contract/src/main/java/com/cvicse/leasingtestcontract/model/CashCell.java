package com.cvicse.leasingtestcontract.model;

public class CashCell {
    private String number;   //期数
    private String changeDate;  //资金改变时间
    private String cashChangeType;   //资金变动类型
    private double cash;   //资金改变金额

    public String getChangeDate() {
        return changeDate;
    }

    public double getCash() {
        return cash;
    }

    public String getCashChangeType() {
        return cashChangeType;
    }

    public String getNumber() {
        return number;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setCashChangeType(String cashChangeType) {
        this.cashChangeType = cashChangeType;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
