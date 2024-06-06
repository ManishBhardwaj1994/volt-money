package com.voltmoney.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Driver {

    CHROME("webdriver.chrome.driver", "/src/main/resources/browser_driver/chromedriver");
    private String driverType;
    private String driverLocation;
}
