package com.voltmoney.pojo.testcase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class TestCaseSteps {

    @SerializedName("execution_order")
    @Expose
    public Integer executionOrder;
    @SerializedName("service")
    @Expose
    public String service;
    @SerializedName("validations")
    @Expose
    public Map<String, String> validations;
    @SerializedName("actions")
    @Expose
    public List<String> actions;

}
