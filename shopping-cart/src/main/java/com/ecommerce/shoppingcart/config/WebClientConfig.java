package com.ecommerce.shoppingcart.config;

import com.ecommerce.shoppingcart.client.BookClient;
import com.ecommerce.shoppingcart.client.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient bookWebClient() {
        return WebClient.builder()
                .baseUrl("http://book-inventory")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public WebClient paymentWebClient() {
        return WebClient.builder()
                .baseUrl("http://payment-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public BookClient bookClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(bookWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(BookClient.class);
    }

    @Bean
    public PaymentClient paymentClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(paymentWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(PaymentClient.class);
    }

}
