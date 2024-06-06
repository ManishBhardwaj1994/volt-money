package com.voltmoney.testdata;

import com.voltmoney.helper.FileHelper;
import com.voltmoney.pojo.testcase.TestCase;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
import java.util.Arrays;
import java.util.List;
import static com.voltmoney.constant.FilePath.E2E;
import static com.voltmoney.constant.FilePath.LOGIN_TEST_CASES;
import static com.voltmoney.helper.GsonHelper.getGsonWithNull;

public class TestData {

    @SneakyThrows
    public static Object[][] fetchTestCases(String filePath){
        String file = FileHelper.readFileAsString(filePath);

        List<TestCase> testCases = Arrays.asList(getGsonWithNull().fromJson(file, TestCase[].class));

        return testCases.stream()
                .map(test -> new Object[]{test})
                .toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] loginTestCases() {
        return fetchTestCases(LOGIN_TEST_CASES.getFilePath());
    }

    @DataProvider
    public Object[][] productPageTestCases() {
        return fetchTestCases(E2E.getFilePath());
    }



}
