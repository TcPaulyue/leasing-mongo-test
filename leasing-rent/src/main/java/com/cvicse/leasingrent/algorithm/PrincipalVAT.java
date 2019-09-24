package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class PrincipalVAT {
    /**
     * 计算本金增值税
     */
    public double getPrincipalVAT(double principal,double principalInterestRate){
        return (double)Math.round(principal*principalInterestRate/(double)(1+principalInterestRate)*100)/100;
    }
}
