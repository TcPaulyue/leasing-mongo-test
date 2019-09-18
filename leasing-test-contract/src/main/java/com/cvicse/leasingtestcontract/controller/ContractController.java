package com.cvicse.leasingtestcontract.controller;

import com.alibaba.fastjson.JSONArray;
import com.cvicse.leasingtestcontract.exception.ContractNotFoundException;
import com.cvicse.leasingtestcontract.model.Contract;
import com.cvicse.leasingtestcontract.payload.ContractRequest;
import com.cvicse.leasingtestcontract.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contract/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;


    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @GetMapping
    public List<Contract> getContracts() {
        logger.info("All contracts requested.");
        return contractService.getAllContract();
    }

    @GetMapping("/{id}")
    public Contract getContract(@PathVariable String id) {
        try {
            logger.info("Get contract with contract.id " + id);
            return this.contractService.getContract(id);
            // return this.contractService.getAllContract();
        } catch (ContractNotFoundException e) {
            logger.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ContractRequest Not Found", e);
        }
    }

    @PostMapping("/new")
    public Contract createContractByTemplateId(@RequestParam(value = "templateId",defaultValue = "null") String templateId
            ,@RequestParam(value = "commitId", defaultValue = "null") String commitId
            ,@RequestBody ContractRequest contractRequest){
        if (templateId.equals("null")) {
            try {
                logger.info("Create contract");
                Contract contract = this.contractService.createContract(contractRequest);
                logger.info(contract.getId());
                return contract;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TemplateRequest Not Found", e);
            }
        }
        else{
            try {
                logger.info("Create contract from templateId and commitId"+" "+templateId+" "+commitId);
                return this.contractService.createContractByTemplateId(contractRequest,templateId,commitId);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TemplateRequest Not Found", e);
            }
        }
    }

    @PutMapping(value = "/{id}")
    public Contract updateContract(@RequestBody ContractRequest contractRequest, @PathVariable String id) {
        try {
            logger.info("UpdateContract with contract.id " + id);
            return this.contractService.updateContract(contractRequest, id);
        } catch (ContractNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TemplateRequest Not Found", e);
        }
    }

    @DeleteMapping("/{id}")
    public List<Contract> deleteContract(@PathVariable String id) {
        try {
            logger.info("Delete contract with contract.id " + id);
            this.contractService.deleteContract(id);
            return this.contractService.getAllContract();
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ContractRequest Not Found");
        }
    }
}