package com.cvicse.leasingcashflow3.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document("CashFlow")
public class CashFlow {
    @Id
    public String id;

    public String name;

    private String contractId;

    public ArrayList<CashCell> cashCells = new ArrayList<>();


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractId() {
        return contractId;
    }

    public ArrayList<CashCell> getCashCells() {
        return cashCells;
    }

    public void setCashCells(ArrayList<CashCell> cashCells) {
        this.cashCells = cashCells;
    }
}
