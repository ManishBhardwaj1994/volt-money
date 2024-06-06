package com.voltmoney.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {

    THANKS("Thank you for your order!"),
    DISPATCHED("Your order has been dispatched, and will arrive just as fast as the pony can get there!"),
    EXPRESS_DELIVERY("Free Pony Express Delivery!"),;

    private String message;
}
