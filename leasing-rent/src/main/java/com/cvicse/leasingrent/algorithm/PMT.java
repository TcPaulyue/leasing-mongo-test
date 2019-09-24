package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class PMT {
    public double getPMT(double principal,double assetValue,double InterestRatePerPeriod,double frequency,String tag){
        double firstPart =principal-assetValue/Math.pow(1+InterestRatePerPeriod,frequency);
        if(tag.equals("先付")){
            double secondPart = Math.pow(1+InterestRatePerPeriod,frequency-1)*(InterestRatePerPeriod)/(Math.pow(1+InterestRatePerPeriod,frequency)-1);
            return firstPart*secondPart;
        }
        else if(tag.equals("后付")){
            double secondPart = Math.pow(1+InterestRatePerPeriod,frequency)*InterestRatePerPeriod/(Math.pow(1+InterestRatePerPeriod,frequency)-1);
            return firstPart*secondPart;
        }
        return 0.00;
    }
}
