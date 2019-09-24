package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class ExcludingTaxPrincipal {
    /**
     * 计算不含税本金
     */
    public double getExcludingTaxPrincipal(double principal,double principalVAT){
        return (double)Math.round((principal-principalVAT)*100)/100;
    }

}
