package com.cvicse.leasingcashflow1;

import com.cvicse.leasingcashflow1.domain.CashFlow;
import com.cvicse.leasingcashflow1.repository.CashFlowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeasingCashflow1ApplicationTests {

    @Autowired
    private CashFlowRepository cashFlowRepository;
    @Test
    public void contextLoads() {
       CashFlow cashFlow = cashFlowRepository.findById("5d823593e48407f929dec45c").get();
       cashFlow.setName(cashFlow.id);
       cashFlowRepository.save(cashFlow);
    }

}
