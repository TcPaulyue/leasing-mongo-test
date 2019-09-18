package com.cvicse.leasingtestcontract.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingtestcontract.exception.ContractNotFoundException;
import com.cvicse.leasingtestcontract.model.Contract;
import com.cvicse.leasingtestcontract.payload.ContractRequest;
import com.cvicse.leasingtestcontract.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;


    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);

    public List<Contract> getAllContract() {
        logger.info("contracts returned");
        List<Contract> contracts = contractRepository.findAll();
        return contracts;
    }

    public Contract getContract(String id) throws ContractNotFoundException {
        if (!this.contractRepository.findById(id).isPresent())
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        return this.contractRepository.findById(id).get();
    }

    public Contract createContract(ContractRequest contractRequest) {
        logger.info("contract saved");
        Contract contract = new Contract(contractRequest.getContent());
        contract.setContractPackageId(contractRequest.getContractPackageId());
        return contractRepository.save(contract);
    }

    public Contract createContractByTemplateId(ContractRequest contractRequest,String templateId,String commitId){
        logger.info("create contract from templateId"+templateId);
        Contract contract = new Contract(contractRequest.getContent());
        contract.setBasicElements(contractRequest.getBasicElements());
        contract.setTemplateId(templateId);
        contract.setCommitId(commitId);
        contract.setProcessInstanceId(contractRequest.getProcessInstanceId());
        contractRepository.save(contract);
        logger.info("new Contract's id is "+contractRepository.findByTemplateIdAndCommitId(templateId,commitId).getId());
        return contractRepository.findByTemplateIdAndCommitId(templateId,commitId);
    }

    public Contract updateContract(ContractRequest contractRequest, String id) throws ContractNotFoundException {
        this.contractRepository.findById(id).ifPresent(contract -> {
            contract.content = contractRequest.getContent();
            contract.setBasicElements(contractRequest.getBasicElements());
            contract.setProcessInstanceId(contractRequest.getProcessInstanceId());
            this.contractRepository.save(contract);
        });
        if (!this.contractRepository.findById(id).isPresent()) {
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        }
        return this.contractRepository.findById(id).get();
    }

    public void deleteContract(String id) throws ContractNotFoundException {
        if (!this.contractRepository.findById(id).isPresent())
            throw new ContractNotFoundException("ContractRequest Not Found in contractRepository.");
        logger.info("contract deleted");
        this.contractRepository.deleteById(id);
    }

}

