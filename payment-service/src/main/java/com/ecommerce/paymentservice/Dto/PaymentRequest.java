package com.ecommerce.paymentservice.Dto;

import com.ecommerce.paymentservice.entity.PaymentMode;
import com.ecommerce.paymentservice.entity.PaymentStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private Boolean isPayed;
    private String referenceNumber;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

}
