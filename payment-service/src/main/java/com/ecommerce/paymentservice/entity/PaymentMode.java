package com.ecommerce.paymentservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentMode {

    WEB("Web"),
    USSD("USSD"),
    TRANSFER("Transfer");

    private final String name;

}
