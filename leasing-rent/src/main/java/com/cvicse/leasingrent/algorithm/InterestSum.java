package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InterestSum {
    /**
     * 计算所有利息之和
     * @param rentCells
     * @return
     */
    public double getInterestSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getInterest();
        }
        return (double)Math.round(sum*100)/100;
    }
}
