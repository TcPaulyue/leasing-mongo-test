package com.cvicse.leasingrent.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvicse.leasingrent.domain.BasicElements;
import com.cvicse.leasingrent.domain.RentSchedule;
import com.cvicse.leasingrent.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/rent")
public class RentController {
    @Autowired
    RentService rentService;

    private static final Logger logger = LoggerFactory.getLogger(RentController.class);

    @PostMapping("/new")
    public RentSchedule createRentSchedule(@RequestBody JSONArray params
    ,@RequestParam(value = "contractId",defaultValue = "null") String contractId){
        logger.info("create RentSchedule");
        return rentService.createRentSchedule(params,contractId);
    }

    @PostMapping("/calc")
    public RentSchedule calcRentSchedule(@RequestBody JSONObject params
    ,@RequestParam(value = "withmargin",defaultValue = "true") String withmargin){
        logger.info("pure calculation");
        return rentService.calculateRentSchedule(params,withmargin);

    }

    @GetMapping
    public List<RentSchedule> getRentSchedules(){
        logger.info("get all rentSchedules");
        return rentService.getRentSchedules();
    }

    @GetMapping("/{id}")
    public RentSchedule getRentSchedule(@PathVariable String id
    ,@RequestParam(value = "type",defaultValue = "rentScheduleId") String type){
        if(type.equals("rentScheduleId")) {
            logger.info("get rentSchedule by rentScheduleId " + id);
            return rentService.getRentScheduleById(id);
        }else{
            logger.info("get rentSchedule by contractId "+id);
            return rentService.getRentScheduleByContractId(id);
        }
    }

    @DeleteMapping("/{id}")
    public List<RentSchedule> deleteRentSchedule(@PathVariable String id){
        logger.info("delete rentSchedule "+id);
        return rentService.deleteRentScheduleById(id);
    }

    @PutMapping("/{id}")
    public RentSchedule updateRentSchedule(@PathVariable String id
            ,@RequestBody JSONObject params){
        logger.info("update rentSchedule by Id "+ id);
        return rentService.updateRentScheduleById(id,params);
    }

    @GetMapping("/{contractId}/commits")
    public JSONArray getContractWithCommitId(@PathVariable String contractId, @RequestParam(value = "commitId", defaultValue = "null") String commitId) {
        if (commitId.equals("null")) {
            logger.info("Get RentSchedule commits with TemplateRequest.id " + contractId);
            return this.rentService.trackRentScheduleChangesWithJavers(contractId);

        } else {
            logger.info("Cet RentSchedule commit with commitId" + commitId);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(this.rentService.getRentScheduleWithJaversCommitId(contractId, commitId));
            return jsonArray;
        }
    }
}
