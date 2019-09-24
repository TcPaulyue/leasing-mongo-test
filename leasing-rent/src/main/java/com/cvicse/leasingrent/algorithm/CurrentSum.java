package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CurrentSum {
    /**
     * 计算所有租金之和
     * @param rentCells
     * @return
     */
    public double getCurrentSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getCurrentRent();
        }
        return (double)Math.round(sum*100)/100;
    }
}
