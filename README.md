# Payment Gateway System

A Java implementation of a payment gateway system demonstrating the **Strategy Design Pattern**. This project allows processing payments through multiple methods: Credit Card, PayPal, UPI, and Net Banking.

## Design Pattern

- **Strategy Pattern**: Defines a family of algorithms (payment methods), encapsulates each one, and makes them interchangeable. The `PaymentStrategy` interface allows switching payment methods at runtime without changing the client code.

## Project Structure

```
com/
├── payment/
│   ├── main/
│   │   └── Main.java              # Main class demonstrating the payment system
│   ├── model/
│   │   ├── PaymentDetails.java    # Data class for payment information
│   │   └── PaymentResult.java     # Data class for payment results
│   ├── processor/
│   │   └── PaymentProcessor.java  # Context class that uses the strategy
│   └── strategy/
│       ├── PaymentStrategy.java   # Strategy interface
│       ├── CreditCardPayment.java # Concrete strategy for credit card
│       ├── PayPalPayment.java     # Concrete strategy for PayPal
│       ├── UPIPayment.java        # Concrete strategy for UPI
│       └── NetBankingPayment.java # Concrete strategy for net banking
```

## How to Compile and Run

1. Ensure you have Java JDK installed (version 8 or higher).
2. Compile the project:
   ```
   javac -d out -sourcepath . com/payment/main/Main.java
   ```
3. Run the application:
   ```
   java -cp out com.payment.main.Main
   ```

## Features

- **Multiple Payment Methods**: Support for Credit Card, PayPal, UPI, and Net Banking.
- **Runtime Strategy Switching**: Change payment methods dynamically.
- **Transaction History**: Tracks all payment attempts.
- **Validation**: Basic validation for each payment method.
- **Error Handling**: Handles failed payments gracefully.

## Sample Output

The program demonstrates processing payments with different methods, including successful and failed transactions, and displays a transaction history at the end.

## Requirements

- Java JDK 8+
- Command line interface for compilation and execution</content>
<parameter name="filePath">c:\Users\lenovo\Downloads\files\README.md