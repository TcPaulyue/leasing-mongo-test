package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.RentCell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExcludingTaxPrincipalSum {
    /**
     * 计算所有不含税本金之和
     * @param rentCells
     * @return
     */
    public double getExcludingTaxPrincipalSum(ArrayList<RentCell> rentCells){
        double sum = 0;
        for(RentCell rentCell : rentCells){
            sum+= rentCell.getExcludingTaxPrincipal();
        }
        return (double)Math.round(sum*100)/100;
    }
}
