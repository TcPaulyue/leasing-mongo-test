package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExcludingTaxXIRR {
    /**
     * 计算整体的不含税XIRR
     */
    public double getExcludingTaxXIRR(ArrayList<RentCell> rentCells,double rate){
        List<Transaction> transactionList = new ArrayList<>();
        for(RentCell rentCell : rentCells){
            transactionList.add(new Transaction(rentCell.getExcludingTaxXIRR(), rentCell.getPayDate().toDate()));
        }
        RentCell rentCell = rentCells.get(rentCells.size()-1);

        double amount = rentCells.get(0).getPurchasePrice()-rentCells.get(0).getPurchasePrice()*rate/(1+rate);
        transactionList.add(new Transaction(-rentCells.get(0).getMargin(),rentCell.getPayDate().toDate()));
        transactionList.add(new Transaction(amount,rentCell.getPayDate().toDate()));
        double date = new Xirr(transactionList).xirr();
        return (double)Math.round(date*1000000)/1000000;
    }
}
