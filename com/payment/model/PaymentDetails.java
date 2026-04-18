package com.payment.model;

public class PaymentDetails {
    private double amount;
    private String currency;
    private String customerName;
    private String description;

    public PaymentDetails(double amount, String currency, String customerName, String description) {
        this.amount = amount;
        this.currency = currency;
        this.customerName = customerName;
        this.description = description;
    }

    public double getAmount()         { return amount; }
    public String getCurrency()       { return currency; }
    public String getCustomerName()   { return customerName; }
    public String getDescription()    { return description; }
}
