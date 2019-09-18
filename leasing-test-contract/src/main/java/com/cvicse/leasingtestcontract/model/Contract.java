package com.cvicse.leasingtestcontract.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Document(collection = "ContractRequest")
public class Contract {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String id;

    public JSONObject content;

    private String contractPackageId;

    private String templateId;   //合同的模板

    private String commitId;   //模板的版本号

    private String processInstanceId;   //流程ID

    private JSONObject basicElements;   //租金计划表基本参数

    public void setBasicElements(JSONObject basicElements) {
        this.basicElements = basicElements;
    }

    public JSONObject getBasicElements() {
        return basicElements;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Contract() {
    }

    public Contract(JSONObject content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("ContractRequest[id=%s, content='%s']", getId(), content);
    }

    public JSONObject getContent() {
        return content;
    }

    public String getContractPackageId() {
        return contractPackageId;
    }

    public void setContractPackageId(String contractPackageId) {
        this.contractPackageId = contractPackageId;
    }

    public String getId() {
        return id;
    }

}