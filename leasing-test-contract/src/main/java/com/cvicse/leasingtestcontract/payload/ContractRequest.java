package com.cvicse.leasingtestcontract.payload;


import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;

public class ContractRequest {
    private String id;

    private String contractPackageId;

    @NotNull
    private JSONObject content;

    @NotNull
    private String processInstanceId;

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setContent(JSONObject content){
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }

    public JSONObject getBasicElements(){
        return this.getContent().getJSONObject("basicElements");
    }

    public String getContractPackageId() {
        return contractPackageId;
    }

    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getCashFlowId(){
        return this.getContent().getString("cashFlowId");
    }

    public String getRentScheduleId(){
        return this.getContent().getString("rentScheduleId");
    }
    public void setContractPackageId(String contractPackageId) {
        this.contractPackageId = contractPackageId;
    }
}
