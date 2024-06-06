package com.voltmoney.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Url {
    SAUCE_DEMO_URL("https://www.saucedemo.com/"),
    PRODUCT_PAGE("https://www.saucedemo.com/inventory.html");
    private String url;
}
