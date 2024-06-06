package com.voltmoney.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiEndpoint {

    TO_DO("/todos"),
    USERS("/users");

    private String endpoint;
}
