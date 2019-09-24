package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class InterestVAT {
    /**
     * 计算利息增值税
     */
    public double getInterestVAT(double interest,double principalInterestRate){
        return (double)Math.round(interest*principalInterestRate/(double)(1+principalInterestRate)*100)/100;
    }

}
