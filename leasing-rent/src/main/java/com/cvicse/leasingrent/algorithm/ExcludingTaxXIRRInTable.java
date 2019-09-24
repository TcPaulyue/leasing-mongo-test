package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class ExcludingTaxXIRRInTable {
    /**
     * 计算表格中的不含税XIRR数值
     */
    public double getExcludingTaxXIRRInTable(double excludingTaxInterest,double excludingTaxPrincipal){
        return (double)Math.round((excludingTaxInterest+excludingTaxPrincipal)*100)/100;
    }

}
