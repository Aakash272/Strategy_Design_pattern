package com.payment.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentResult {
    private boolean success;
    private String transactionId;
    private String method;
    private double amount;
    private String currency;
    private String timestamp;
    private String message;

    public PaymentResult(boolean success, String transactionId, String method,
                         double amount, String currency, String message) {
        this.success = success;
        this.transactionId = transactionId;
        this.method = method;
        this.amount = amount;
        this.currency = currency;
        this.message = message;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean isSuccess()        { return success; }
    public String getTransactionId()  { return transactionId; }
    public String getMethod()         { return method; }
    public double getAmount()         { return amount; }
    public String getCurrency()       { return currency; }
    public String getTimestamp()      { return timestamp; }
    public String getMessage()        { return message; }

    @Override
    public String toString() {
        String status = success ? "✅ SUCCESS" : "❌ FAILED";
        return String.format(
            "\n-------------------------------------------\n" +
            "  Status       : %s\n" +
            "  Method       : %s\n" +
            "  Amount       : %.2f %s\n" +
            "  Transaction  : %s\n" +
            "  Timestamp    : %s\n" +
            "  Message      : %s\n" +
            "-------------------------------------------",
            status, method, amount, currency, transactionId, timestamp, message
        );
    }
}
