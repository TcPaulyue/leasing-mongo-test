package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PrincipalSum {
    /**
     * 计算所有本金之和
     * @param rentCells
     * @return
     */
    public double getPrincipalSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getPrincipal();
        }
        return (double)Math.round(sum*100)/100;
    }
}
