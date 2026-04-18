package com.payment.strategy;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;

import java.util.UUID;

/**
 * CONCRETE STRATEGY 3 — UPI Payment
 */
public class UPIPayment implements PaymentStrategy {

    private String upiId;
    private String upiPin;

    public UPIPayment(String upiId, String upiPin) {
        this.upiId = upiId;
        this.upiPin = upiPin;
    }

    @Override
    public boolean validate() {
        System.out.println("[UPI] Validating UPI ID...");
        if (upiId == null || !upiId.contains("@")) {
            System.out.println("[UPI] ❌ Invalid UPI ID. Must be in format: name@bank");
            return false;
        }
        if (upiPin == null || upiPin.length() < 4 || upiPin.length() > 6) {
            System.out.println("[UPI] ❌ UPI PIN must be 4-6 digits.");
            return false;
        }
        System.out.println("[UPI] ✅ UPI ID and PIN validated.");
        return true;
    }

    @Override
    public PaymentResult process(PaymentDetails details) {
        if (!validate()) {
            return new PaymentResult(false, "N/A", getMethodName(),
                    details.getAmount(), details.getCurrency(),
                    "UPI validation failed. Payment rejected.");
        }

        System.out.println("[UPI] 📲 Sending payment request to UPI ID: " + upiId);
        System.out.println("[UPI] 🏦 Connecting to NPCI gateway...");
        System.out.println("[UPI] ✔️  Deducting amount from linked bank account...");

        String txnId = "UPI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new PaymentResult(true, txnId, getMethodName(),
                details.getAmount(), details.getCurrency(),
                "UPI transfer successful to " + upiId);
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}
