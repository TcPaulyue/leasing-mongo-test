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
    public double getXIRR(ArrayList<RentCell> rentCells,double handlingFee
            ,double margin,double purchasePrice,double principal){
        List<Transaction> transactionList = new ArrayList<>();
        for(RentCell rentCell : rentCells){
            transactionList.add(new Transaction(rentCell.getXIRR(), rentCell.getPayDate().toDate()));
        }
        RentCell lastCell = rentCells.get(rentCells.size()-1);
        RentCell firstCell = rentCells.get(0);
        //System.out.println(rentCell.getPayDate().toDate()+" "+rentCells.get(0).getMargin()+" "+rentCells.get(0).getPurchasePrice());
        transactionList.add(new Transaction((handlingFee+margin-principal),firstCell.getPayDate().toDate()));
        transactionList.add(new Transaction(purchasePrice-margin,lastCell.getPayDate().toDate()));
        double date = new Xirr(transactionList).xirr();
        return (double)Math.round(date*1000000)/1000000;
    }
}
