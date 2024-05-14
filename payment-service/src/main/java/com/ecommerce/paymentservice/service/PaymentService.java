package com.ecommerce.paymentservice.service;

import com.ecommerce.paymentservice.Dto.PaymentRequest;
import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.exception.BusinessException;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import com.ecommerce.paymentservice.service.service_interface.PaymentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentInterface {

    private final PaymentRepository paymentRepository;

    @Override
    public void doPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setIsPayed(request.getIsPayed());
        payment.setOrderId(request.getOrderId());
        payment.setReferenceNo(request.getReferenceNumber());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaymentStatus(request.getPaymentStatus());
        paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentByOrderId(Long Id) {
        Optional<Payment> payment = paymentRepository.findByOrderId(Id);
        if(payment.isPresent()){
            return payment;
        }
        throw new BusinessException(" No payment detail found", HttpStatus.NOT_FOUND);
    }
}
