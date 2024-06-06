package com.voltmoney.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePath {

    E2E("src/test/resources/e2e.json"),
    LOGIN_TEST_CASES("src/test/resources/login_test_case.json");
    private String filePath;
}
