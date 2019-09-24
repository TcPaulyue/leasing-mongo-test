package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class ExcludingTaxInterest {
    /**
     * 计算不含税利息
     */
    public double getExcludingTaxInterest(double interest,double interestVAT){
        return (double)Math.round((interest-interestVAT)*100)/100;
    }
}
