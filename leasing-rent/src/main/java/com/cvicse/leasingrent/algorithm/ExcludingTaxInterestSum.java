package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExcludingTaxInterestSum {
    public double getExcludingTaxInterestSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getExcludingTaxInterest();
        }
        return (double)Math.round(sum*100)/100;
    }
}
