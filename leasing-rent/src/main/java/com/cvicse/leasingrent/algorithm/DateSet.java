package com.cvicse.leasingrent.algorithm;

import com.cvicse.leasingrent.domain.BasicElements;
import com.iceyyy.workday.WorkUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DateSet {

    public boolean isWorkDay(DateTime dateTime){
        return WorkUtils.isWorkendDay(dateTime.toString("yyyyMMdd"));
    }

    /**
     * 根据参数推算出每次还款日，返回一个list
     * todo: 31&30
     */
    public List<DateTime> getDateSet(BasicElements basicElements){

        List<DateTime> dateList = new LinkedList<>();
        int times = basicElements.getLeasePeriod()/basicElements.getRentalFrequency();
        DateTime dt = new DateTime(basicElements.getStartDate());
        dateList.add(dt);
        for(int i=0;i<times-1;i++){
            DateTime temp =dt.plusMonths(basicElements.getRentalFrequency());
            dt = temp;
            DateTime temp1 = new DateTime(temp.getYear(),temp.getMonthOfYear(),basicElements.getRepaymentDate(),0,0,0);
            dateList.add(temp1);
        }
        dateList.add(new DateTime(basicElements.getEndDate()));
        return dateList;
    }

    /**
     * 获取每次还款日之间的时间间隔
     */
    public List<Integer>  getDateInterval(List<DateTime> dateSet){
        List<Integer> dateIntervalList = new LinkedList<>();
        dateIntervalList.add(0);
        for(int i=1;i<dateSet.size();i++){
            Period period = new Period(dateSet.get(i-1), dateSet.get(i), PeriodType.days());
            dateIntervalList.add(period.getDays());
        }
        return dateIntervalList;
    }
}
