package com.cvicse.leasingcashflow1.repository;

import com.cvicse.leasingcashflow1.domain.CashFlow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CashFlowRepository extends MongoRepository<CashFlow,String> {
        CashFlow getCashFlowByContractId(String contractId);

        Optional<CashFlow> findCashFlowByContractId(String contractId);
}
