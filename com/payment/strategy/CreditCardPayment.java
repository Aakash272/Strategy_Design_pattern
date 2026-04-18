package com.payment.strategy;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;

import java.util.UUID;

/**
 * CONCRETE STRATEGY 1 — Credit Card Payment
 */
public class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName,
                              String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        System.out.println("[CreditCard] Validating card details...");
        if (cardNumber == null || cardNumber.replaceAll("\\s", "").length() != 16) {
            System.out.println("[CreditCard] ❌ Invalid card number.");
            return false;
        }
        if (cvv == null || cvv.length() != 3) {
            System.out.println("[CreditCard] ❌ Invalid CVV.");
            return false;
        }
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            System.out.println("[CreditCard] ❌ Invalid expiry date format (MM/YY).");
            return false;
        }
        System.out.println("[CreditCard] ✅ Card validated successfully.");
        return true;
    }

    @Override
    public PaymentResult process(PaymentDetails details) {
        if (!validate()) {
            return new PaymentResult(false, "N/A", getMethodName(),
                    details.getAmount(), details.getCurrency(),
                    "Validation failed. Payment rejected.");
        }

        System.out.println("[CreditCard] 🔒 Encrypting card data...");
        System.out.println("[CreditCard] 🌐 Contacting bank gateway...");
        System.out.println("[CreditCard] 💳 Charging card ending in "
                + cardNumber.substring(cardNumber.length() - 4) + "...");

        String txnId = "CC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new PaymentResult(true, txnId, getMethodName(),
                details.getAmount(), details.getCurrency(),
                "Payment successful for " + details.getCustomerName());
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}
