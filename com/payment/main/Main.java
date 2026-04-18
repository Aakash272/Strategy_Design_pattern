package com.payment.main;

import com.payment.model.PaymentDetails;
import com.payment.processor.PaymentProcessor;
import com.payment.strategy.*;

/**
 * ╔══════════════════════════════════════════════════╗
 * ║       PAYMENT GATEWAY SYSTEM —                   ║
 * ║         Design Pattern: Strategy Pattern         ║
 * ╚══════════════════════════════════════════════════╝
 *
 * Strategy Pattern Roles:
 *  - PaymentStrategy        → Strategy Interface
 *  - CreditCardPayment      → Concrete Strategy 1
 *  - PayPalPayment          → Concrete Strategy 2
 *  - UPIPayment             → Concrete Strategy 3
 *  - NetBankingPayment      → Concrete Strategy 4
 *  - PaymentProcessor       → Context Class
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     PAYMENT GATEWAY SYSTEM (Strategy Pattern) ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ── Order 1: Pay via Credit Card ──────────────────────────────
        PaymentDetails order1 = new PaymentDetails(1999.99, "INR", "Ravi Sharma",
                "Purchase: Gaming Keyboard");

        PaymentStrategy creditCard = new CreditCardPayment(
                "4111 1111 1111 1111", "Ravi Sharma", "12/26", "123");

        PaymentProcessor processor = new PaymentProcessor(creditCard);
        processor.pay(order1);


        // ── Order 2: Switch to PayPal at runtime ──────────────────────
        PaymentDetails order2 = new PaymentDetails(499.00, "USD", "Anita Desai",
                "Subscription: Netflix Premium");

        PaymentStrategy paypal = new PayPalPayment("anita.desai@gmail.com", "securePass99");

        processor.setStrategy(paypal);
        processor.pay(order2);


        // ── Order 3: Switch to UPI ────────────────────────────────────
        PaymentDetails order3 = new PaymentDetails(350.00, "INR", "Karan Mehta",
                "Food Order: Zomato");

        PaymentStrategy upi = new UPIPayment("karan@okicici", "4321");

        processor.setStrategy(upi);
        processor.pay(order3);


        // ── Order 4: Switch to Net Banking ───────────────────────────
        PaymentDetails order4 = new PaymentDetails(15000.00, "INR", "Priya Nair",
                "Flight Booking: Mumbai to Delhi");

        PaymentStrategy netBanking = new NetBankingPayment("HDFC", "priya_nair_01", "mybank@123");

        processor.setStrategy(netBanking);
        processor.pay(order4);


        // ── Order 5: Invalid UPI (Validation Failure demo) ───────────
        PaymentDetails order5 = new PaymentDetails(100.00, "INR", "Test User",
                "Test with bad UPI");

        PaymentStrategy badUPI = new UPIPayment("invalid-upi", "1"); // Invalid!

        processor.setStrategy(badUPI);
        processor.pay(order5);


        // ── Print full transaction history ────────────────────────────
        processor.printTransactionHistory();
    }
}
