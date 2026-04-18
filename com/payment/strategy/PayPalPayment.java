package com.payment.strategy;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;

import java.util.UUID;

/**
 * CONCRETE STRATEGY 2 — PayPal Payment
 */
public class PayPalPayment implements PaymentStrategy {

    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean validate() {
        System.out.println("[PayPal] Validating PayPal credentials...");
        if (email == null || !email.contains("@")) {
            System.out.println("[PayPal] ❌ Invalid email address.");
            return false;
        }
        if (password == null || password.length() < 6) {
            System.out.println("[PayPal] ❌ Password too short.");
            return false;
        }
        System.out.println("[PayPal] ✅ Credentials validated.");
        return true;
    }

    @Override
    public PaymentResult process(PaymentDetails details) {
        if (!validate()) {
            return new PaymentResult(false, "N/A", getMethodName(),
                    details.getAmount(), details.getCurrency(),
                    "PayPal login failed. Payment rejected.");
        }

        System.out.println("[PayPal] 🔐 Authenticating with PayPal servers...");
        System.out.println("[PayPal] 🤝 Establishing secure session...");
        System.out.println("[PayPal] 💸 Transferring funds via PayPal for: " + email);

        String txnId = "PP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new PaymentResult(true, txnId, getMethodName(),
                details.getAmount(), details.getCurrency(),
                "PayPal payment successful. Receipt sent to " + email);
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
