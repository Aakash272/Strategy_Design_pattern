package com.payment.strategy;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;

/**
 * STRATEGY INTERFACE
 * ------------------
 * Defines the contract that every payment method must follow.
 * All concrete strategies implement this interface.
 */
public interface PaymentStrategy {
    boolean validate();
    PaymentResult process(PaymentDetails details);
    String getMethodName();
}
