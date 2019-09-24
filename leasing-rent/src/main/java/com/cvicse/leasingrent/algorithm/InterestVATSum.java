package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InterestVATSum {
    public double getInterestVATSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getInterestVAT();
        }
        return (double)Math.round(sum*100)/100;
    }
}
