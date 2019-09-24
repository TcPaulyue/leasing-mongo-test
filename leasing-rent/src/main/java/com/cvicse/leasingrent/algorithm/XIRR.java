package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算整体的XIRR
 */
@Component
public class XIRR {
    public double getXIRR(ArrayList<RentCell> rentCells){
        List<Transaction> transactionList = new ArrayList<>();
        for(RentCell rentCell : rentCells){
            transactionList.add(new Transaction(rentCell.getXIRR(), rentCell.getPayDate().toDate()));
        }
        RentCell rentCell = rentCells.get(rentCells.size()-1);
        //System.out.println(rentCell.getPayDate().toDate()+" "+rentCells.get(0).getMargin()+" "+rentCells.get(0).getPurchasePrice());
        transactionList.add(new Transaction(-rentCells.get(0).getMargin(),rentCell.getPayDate().toDate()));
        transactionList.add(new Transaction(rentCells.get(0).getPurchasePrice(),rentCell.getPayDate().toDate()));
        double date = new Xirr(transactionList).xirr();
        return (double)Math.round(date*1000000)/1000000;
    }
}
