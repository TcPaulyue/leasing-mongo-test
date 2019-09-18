package com.cvicse.leasingcashflow2.repository;

import com.cvicse.leasingcashflow2.domain.CashFlow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CashFlowRepository extends MongoRepository<CashFlow,String> {
        CashFlow getCashFlowByContractId(String contractId);

        Optional<CashFlow> findCashFlowByContractId(String contractId);
}
