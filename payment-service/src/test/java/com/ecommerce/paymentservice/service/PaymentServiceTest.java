package com.ecommerce.paymentservice.service;

import com.ecommerce.paymentservice.Dto.PaymentRequest;
import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.entity.PaymentMode;
import com.ecommerce.paymentservice.entity.PaymentStatus;
import com.ecommerce.paymentservice.exception.BusinessException;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceTest {

    private PaymentRepository paymentRepository;

    PaymentService paymentService;

    @BeforeEach
    void setup(){
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    void test_When_doPayment_isSuccess() {

        PaymentRequest paymentRequest = getMockPaymentRequest();

        Payment payment = getMockPayment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment doPayment = paymentService.doPayment(paymentRequest);
        verify(paymentRepository, times(1))
                .save(any());

        assertEquals(payment.getId(), doPayment.getId());
    }

    @Test
    void test_When_getPaymentByOrderId_isSuccess() {

        Payment payment = getMockPayment();

        when(paymentRepository.findByOrderId(anyLong())).thenReturn(Optional.of(payment));

        //Actual
        Optional<Payment> paymentResponse = paymentService.getPaymentByOrderId(1L);
        assertTrue(paymentResponse.isPresent());
        Payment result = paymentResponse.get();
        //Verification
        verify(paymentRepository, times(1)).findByOrderId(anyLong());

        //Assert
        assertNotNull(paymentResponse);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void test_When_getPaymentDetailsByOrderId_isNotFound() {

        when(paymentRepository.findByOrderId(anyLong())).thenReturn(Optional.ofNullable(null));

        //Assert
        BusinessException exception
                = assertThrows(BusinessException.class, () -> paymentService.getPaymentByOrderId(1L));
        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorCode());
        assertEquals(" No payment detail found", exception.getErrorMessage());

        //Verify
        verify(paymentRepository, times(1)).findByOrderId(anyLong());
    }

    private PaymentRequest getMockPaymentRequest() {
        return PaymentRequest.builder()
                .isPayed(true)
                .orderId(1)
                .paymentMode(PaymentMode.TRANSFER)
                .paymentStatus(PaymentStatus.COMPLETED)
                .referenceNumber(null)
                .build();

    }

    private Payment getMockPayment() {
        return Payment.builder()
                .id(1)
                .orderId(1L)
                .paymentMode(PaymentMode.WEB)
                .paymentStatus(PaymentStatus.COMPLETED)
                .referenceNo(null)
                .isPayed(true)
                .build();
    }
}
