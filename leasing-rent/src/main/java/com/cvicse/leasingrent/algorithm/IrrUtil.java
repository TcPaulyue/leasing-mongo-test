package com.cvicse.leasingrent.algorithm;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class IrrUtil {
    /**迭代次数*/
    public static int LOOPNUM=1000;
    /**最小差异*/
    public static final double MINDIF=0.00000001;


    /**
     * @desc 使用方法参考main方法
     * @param cashFlow  资金流
     * @return 收益率
     */
    public double getIrr(List<Double> cashFlow){
        double flowOut=cashFlow.get(0);
        double minValue=0d;
        double maxValue=1d;
        double testValue=0d;
        while(LOOPNUM>0){
            testValue=(minValue+maxValue)/2;
            double npv=NPV(cashFlow,testValue);
            if(Math.abs(flowOut+npv)<MINDIF){
                break;
            }else if(Math.abs(flowOut)>npv){
                maxValue=testValue;
            }else{
                minValue=testValue;
            }
            LOOPNUM--;
        }
        return testValue;
    }

    public double NPV(List<Double> flowInArr,double rate){
        double npv=0;
        for(int i=1;i<flowInArr.size();i++){
            npv+=flowInArr.get(i)/Math.pow(1+rate, i);
        }
        return npv;
    }

    public double irr(List<Double> cashFlow, double guess) {
        int maxIterationCount = 20;
        double absoluteAccuracy = 1.0E-007D;

        double x0 = guess;

        int i = 0;
        while (i < maxIterationCount) {
            double fValue = 0.0D;
            double fDerivative = 0.0D;
            for (int k = 0; k < cashFlow.size(); k++) {
                fValue += cashFlow.get(k) / Math.pow(1.0D + x0, k);
                fDerivative += -k * cashFlow.get(k) / Math.pow(1.0D + x0, k + 1);
            }
            double x1 = x0 - fValue / fDerivative;
            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }
            x0 = x1;
            i++;
        }
        return (0.0D / 0.0D);
    }
}
