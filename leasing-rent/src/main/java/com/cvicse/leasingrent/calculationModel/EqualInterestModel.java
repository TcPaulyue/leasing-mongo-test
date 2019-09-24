package com.cvicse.leasingrent.calculationModel;

import com.cvicse.leasingrent.algorithm.*;
import com.cvicse.leasingrent.domain.BasicElements;
import com.cvicse.leasingrent.domain.RentCell;
import com.cvicse.leasingrent.domain.RentSchedule;

import java.util.List;

public class EqualInterestModel extends BasicModel {
    /**
     * 等额本金计算模型
     * @param basicElements
     * @return
     */
    public RentSchedule createRentSchedule(BasicElements basicElements){
        //计算每期本金
        double principal = (double)Math.round((basicElements.getPrincipal()/(double)(basicElements.getLeasePeriod()/basicElements.getRentalFrequency()))*100)/100;
        //System.out.println(principal);
        //获取还款日之间的时间间隔
        List<Integer> dateIntervals = new DateSet().getDateInterval(new DateSet().getDateSet(basicElements));

        RentSchedule rentSchedule = new RentSchedule();
        //插入第一行初始化的数据
        rentSchedule.getRentCells().add(this.initialDesignatedPrincipal(basicElements));

        double count =0;
        for(int i=1;i<dateIntervals.size();i++) {
            RentCell rentCell = new RentCell();
            rentCell.setPayDate(new DateSet().getDateSet(basicElements).get(i));     //导入支付日
            rentCell.setPrincipal(principal);     //导入每期本金

            int lastIndex = rentSchedule.getRentCells().size()-1;

            double interest = rentSchedule.getRentCells().get(lastIndex).getRemainingPrincipal()
                    *dateIntervals.get(i)*basicElements.getAnnualInterestRate()/basicElements.getYearCountDays();

            rentCell.setInterest((double)Math.round(interest*100)/100);      //导入利息

            rentCell.setCurrentRent(interest+principal);          //导入当期租金

            count = count+ principal;
            rentCell.setRemainingPrincipal(basicElements.getPrincipal()-count);   //剩余本金

            rentCell.setInterestVAT(new InterestVAT().getInterestVAT(interest,basicElements.getPrincipalInterestRate())); //计算利息增值税

            rentCell.setPrincipalVAT(new PrincipalVAT().getPrincipalVAT(principal,basicElements.getPrincipalInterestRate())); //计算本金增值税

            rentCell.setExcludingTaxInterest(new ExcludingTaxInterest().getExcludingTaxInterest(interest, rentCell.getInterestVAT()));  //计算不含税利息

            rentCell.setExcludingTaxPrincipal(new ExcludingTaxPrincipal().getExcludingTaxPrincipal(principal, rentCell.getPrincipalVAT()));//计算不含税本金

            rentCell.setXIRR(new XIRRInTable().getXIRRInTable(rentCell.getCurrentRent()));  //获取表格中的XIRR

            rentCell.setExcludingTaxXIRR(new ExcludingTaxXIRRInTable().getExcludingTaxXIRRInTable(rentCell.getExcludingTaxInterest()
                    , rentCell.getExcludingTaxPrincipal()));     //获取表格中的不含税XIRR

            rentSchedule.getRentCells().add(rentCell);
        }
        this.setCommonElements(rentSchedule);
        return rentSchedule;
    }
}
