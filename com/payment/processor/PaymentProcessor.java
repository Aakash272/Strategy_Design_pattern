package com.payment.processor;

import com.payment.model.PaymentDetails;
import com.payment.model.PaymentResult;
import com.payment.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * CONTEXT CLASS — PaymentProcessor
 * ---------------------------------
 * Holds a reference to a PaymentStrategy.
 * The strategy can be swapped at runtime without changing this class.
 * This is the core of the Strategy Pattern.
 */
public class PaymentProcessor {

    private PaymentStrategy strategy;
    private List<PaymentResult> transactionHistory = new ArrayList<>();

    // Set strategy at construction
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // Swap strategy at runtime — key power of Strategy Pattern
    public void setStrategy(PaymentStrategy strategy) {
        System.out.println("\n🔄 Switching payment method to: " + strategy.getMethodName());
        this.strategy = strategy;
    }

    // Delegate payment processing to the active strategy
    public PaymentResult pay(PaymentDetails details) {
        System.out.println("\n========================================");
        System.out.println("  Processing Payment via: " + strategy.getMethodName());
        System.out.println("  Customer : " + details.getCustomerName());
        System.out.println("  Amount   : " + details.getAmount() + " " + details.getCurrency());
        System.out.println("  For      : " + details.getDescription());
        System.out.println("========================================");

        PaymentResult result = strategy.process(details);
        transactionHistory.add(result);
        System.out.println(result);
        return result;
    }

    // Show all past transactions
    public void printTransactionHistory() {
        System.out.println("\n\n========== TRANSACTION HISTORY ==========");
        if (transactionHistory.isEmpty()) {
            System.out.println("  No transactions found.");
        } else {
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + transactionHistory.get(i).getMethod()
                        + " | " + transactionHistory.get(i).getTransactionId()
                        + " | " + (transactionHistory.get(i).isSuccess() ? "SUCCESS" : "FAILED")
                        + " | " + transactionHistory.get(i).getAmount()
                        + " " + transactionHistory.get(i).getCurrency());
            }
        }
        System.out.println("==========================================");
    }
}
