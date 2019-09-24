package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;

@Component
public class InterestRatePerPeriod {
    public double getInterestRatePerPeriod(double annualInterestRate, Integer rentalFrequency,Integer  yearCountDays){
        double I = 1+annualInterestRate/(yearCountDays*365)/12;
        return Math.pow(I,rentalFrequency)-1;
    }
}
