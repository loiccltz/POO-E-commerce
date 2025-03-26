package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.PaymentMethod;
import com.backend.ecommerce_backend.entity.User;
import com.backend.ecommerce_backend.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod, User user) {
        paymentMethod.setUser(user);
        return paymentMethodRepository.save(paymentMethod);
    }

    public boolean processPayment(PaymentMethod paymentMethod, double amount) {
        return paymentMethod.processPayment(amount);
    }
}