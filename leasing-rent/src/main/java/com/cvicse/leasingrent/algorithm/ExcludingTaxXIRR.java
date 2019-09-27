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
    public double getExcludingTaxXIRR(ArrayList<RentCell> rentCells,double handlingFee
            ,double margin,double purchasePrice,double firstTax,double lastTax){
        List<Transaction> transactionList = new ArrayList<>();
        for(RentCell rentCell : rentCells){
            transactionList.add(new Transaction(rentCell.getExcludingTaxXIRR(), rentCell.getPayDate().toDate()));
        }
        transactionList.add(new Transaction(firstTax,rentCells.get(0).getPayDate().toDate()));

        RentCell rentCell = rentCells.get(rentCells.size()-1);
        transactionList.add(new Transaction(lastTax,rentCell.getPayDate().toDate()));
        double date = new Xirr(transactionList).xirr();
        return (double)Math.round(date*1000000)/1000000;
    }
}
