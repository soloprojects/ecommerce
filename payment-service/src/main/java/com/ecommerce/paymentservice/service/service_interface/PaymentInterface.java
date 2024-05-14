package com.ecommerce.paymentservice.service.service_interface;

import com.ecommerce.paymentservice.Dto.PaymentRequest;
import com.ecommerce.paymentservice.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentInterface {

    void doPayment(PaymentRequest request);

    Optional<Payment> getPaymentByOrderId(Long Id);

}
