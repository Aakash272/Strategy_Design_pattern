package com.payment.strategy;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * CONCRETE STRATEGY 4 — Net Banking Payment
 */
public class NetBankingPayment implements PaymentStrategy {

    private String bankName;
    private String userId;
    private String password;

    private static final List<String> SUPPORTED_BANKS =
            Arrays.asList("SBI", "HDFC", "ICICI", "AXIS", "PNB", "BOB");

    public NetBankingPayment(String bankName, String userId, String password) {
        this.bankName = bankName.toUpperCase();
        this.userId = userId;
        this.password = password;
    }

    @Override
    public boolean validate() {
        System.out.println("[NetBanking] Validating net banking credentials...");
        if (!SUPPORTED_BANKS.contains(bankName)) {
            System.out.println("[NetBanking] ❌ Bank '" + bankName + "' not supported.");
            System.out.println("[NetBanking] Supported banks: " + SUPPORTED_BANKS);
            return false;
        }
        if (userId == null || userId.isEmpty()) {
            System.out.println("[NetBanking] ❌ User ID cannot be empty.");
            return false;
        }
        if (password == null || password.length() < 6) {
            System.out.println("[NetBanking] ❌ Password must be at least 6 characters.");
            return false;
        }
        System.out.println("[NetBanking] ✅ Credentials validated for bank: " + bankName);
        return true;
    }

    @Override
    public PaymentResult process(PaymentDetails details) {
        if (!validate()) {
            return new PaymentResult(false, "N/A", getMethodName(),
                    details.getAmount(), details.getCurrency(),
                    "Net banking validation failed. Payment rejected.");
        }

        System.out.println("[NetBanking] 🌐 Redirecting to " + bankName + " portal...");
        System.out.println("[NetBanking] 🔑 Authenticating user: " + userId);
        System.out.println("[NetBanking] 💰 Initiating NEFT/IMPS transfer...");

        String txnId = "NB-" + bankName + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        return new PaymentResult(true, txnId, getMethodName(),
                details.getAmount(), details.getCurrency(),
                "Net banking payment via " + bankName + " successful.");
    }

    @Override
    public String getMethodName() {
        return "Net Banking (" + bankName + ")";
    }
}
