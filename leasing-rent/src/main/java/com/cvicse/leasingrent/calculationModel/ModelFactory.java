package com.cvicse.leasingrent.calculationModel;

import com.cvicse.leasingrent.domain.BasicElements;
import com.cvicse.leasingrent.domain.RentSchedule;

/**
 * 租金计算模型工厂
 */
public class ModelFactory {
    public RentSchedule getBasicModel(String modelType, BasicElements basicElements){
        if(modelType.equals("等额本金")){
            return new EqualInterestModel().createRentSchedule(basicElements);
        }
        else if(modelType.equals("等额本息")){
            return new EqualPrincipalModel().createRentSchedule(basicElements);
        }
        return null;
    }
}
