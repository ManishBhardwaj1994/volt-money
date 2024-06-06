package com.voltmoney.pojo.testcase;

import com.voltmoney.constant.ResultStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class TestCase {

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;

    @SerializedName("result_status")
    @Expose
    public ResultStatus resultStatus;

    @SerializedName("expected_message")
    @Expose
    public String expectedResult;

}
