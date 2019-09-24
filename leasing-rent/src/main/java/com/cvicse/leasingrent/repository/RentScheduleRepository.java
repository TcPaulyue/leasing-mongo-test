package com.cvicse.leasingrent.repository;

import com.cvicse.leasingrent.domain.RentSchedule;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface RentScheduleRepository extends MongoRepository<RentSchedule,String> {
    RentSchedule getRentScheduleByContractId(String contractId);

    Optional<RentSchedule> findRentScheduleByContractId(String contractId);
}
