package com.cvicse.leasingrent.calculationModel;

import com.cvicse.leasingrent.algorithm.*;
import com.cvicse.leasingrent.domain.BasicElements;
import com.cvicse.leasingrent.domain.RentCell;
import com.cvicse.leasingrent.domain.RentSchedule;
import org.springframework.stereotype.Component;


/**
 * 基本的租金计算模型
 */
@Component
public class BasicModel {
    /**
     * 初始化首行列表
     */
    public RentCell initialDesignatedPrincipal(BasicElements basicElements){
        RentCell rentCell = new RentCell();
        rentCell.setPayDate(new DateSet().getDateSet(basicElements).get(0));
        rentCell.setCurrentRent(0);
        rentCell.setPrincipal(0);
        rentCell.setInterest(0);
        rentCell.setRemainingPrincipal(basicElements.getPrincipal());
        rentCell.setHandlingFee(basicElements.getHandlingFee());
        rentCell.setMargin(basicElements.getMargin());
        rentCell.setPurchasePrice(basicElements.getPurchasePrice());
        rentCell.setXIRR((-basicElements.getPrincipal())+rentCell.getHandlingFee()+rentCell.getMargin());
        double principalNoTax = basicElements.getPrincipal()-(basicElements.getPrincipal()*basicElements.getInputTaxRate())/(1+basicElements.getInputTaxRate());
        double handingFeeNoTax = rentCell.getHandlingFee()-rentCell.getHandlingFee()*basicElements.getPrincipalInterestRate()/(1+basicElements.getPrincipalInterestRate());
        rentCell.setExcludingTaxXIRR(((double)Math.round(-principalNoTax)*100)/100+(double)Math.round(handingFeeNoTax*100)/100+rentCell.getMargin());
        return rentCell;
    }

    /**
     * 设置一些基本的参数
     * @param rentSchedule
     * @return
     */
    public RentSchedule setCommonElements(RentSchedule rentSchedule){
        rentSchedule.setXIRR(new XIRR()
                .getXIRR(rentSchedule.getRentCells()));      //计算整体的XIRR

        rentSchedule.setExcludingTaxXIRR(new ExcludingTaxXIRR()
                .getExcludingTaxXIRR(rentSchedule.getRentCells(),0.13));   //计算整体的不含税XIRR

        rentSchedule.setPrincipalSum(new PrincipalSum()
                .getPrincipalSum(rentSchedule.getRentCells()));  //计算本金之和

        rentSchedule.setCurrentRentSum(new CurrentSum()
                .getCurrentSum(rentSchedule.getRentCells()));    //计算所有租金之和

        rentSchedule.setInterestSum(new InterestSum()
                .getInterestSum(rentSchedule.getRentCells()));    //计算所有利息之和

        rentSchedule.setExcludingTaxPrincipalSum(new ExcludingTaxPrincipalSum()
                .getExcludingTaxPrincipalSum(rentSchedule.getRentCells())); //计算所有不含税本金之和

        rentSchedule.setExcludingTaxInterestSum(new ExcludingTaxInterestSum()
                .getExcludingTaxInterestSum(rentSchedule.getRentCells()));   //计算所有不含税利息之和

        rentSchedule.setPrincipalVATSum(new PrincipalVATSum()
                .getPrincipalVATSum(rentSchedule.getRentCells()));   //计算所有本金增值税之和

        rentSchedule.setInterestVATSum(new InterestVATSum()
                .getInterestVATSum(rentSchedule.getRentCells()));   //计算所有利息增值税之和
        return rentSchedule;
    }
}
