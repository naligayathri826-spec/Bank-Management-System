import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a customer's bank account.
 * Demonstrates Object-Oriented Programming (OOP) principles including
 * encapsulation, data hiding, business logic validation, and aggregation
 * with Transaction objects.
 */
public class Account {
    // Encapsulated private fields
    private long accountNumber;
    private String customerName;
    private double balance;
    private final ArrayList<Transaction> transactionHistory;

    /**
     * Constructs a new Account with the specified details.
     * If an initial deposit greater than 0 is provided, it sets the initial balance
     * and logs the initial DEPOSIT transaction.
     *
     * @param accountNumber  Unique numeric account number
     * @param customerName   Name of the account holder
     * @param initialDeposit Initial amount to deposit (>= 0)
     */
    public Account(long accountNumber, String customerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();

        if (initialDeposit > 0) {
            this.balance = initialDeposit;
            this.transactionHistory.add(new Transaction("DEPOSIT", initialDeposit, this.balance));
        }
    }

    // --- Getters & Setters ---

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Returns an unmodifiable view of the transaction history to preserve encapsulation.
     *
     * @return List of transactions
     */
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    // --- Business Logic Methods ---

    /**
     * Deposits money into the account.
     * Validates that deposit amount is strictly positive (> 0).
     * Updates balance and logs the transaction.
     *
     * @param amount The amount to deposit
     * @return true if deposit succeeded, false if invalid amount
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactionHistory.add(new Transaction("DEPOSIT", amount, this.balance));
        return true;
    }

    /**
     * Withdraws money from the account.
     * Validates that withdrawal amount is positive (> 0) and does not exceed current balance.
     * Deducts amount from balance and logs the transaction.
     *
     * @param amount The amount to withdraw
     * @return true if withdrawal succeeded, false if invalid amount or insufficient balance
     */
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        this.transactionHistory.add(new Transaction("WITHDRAWAL", amount, this.balance));
        return true;
    }

    /**
     * Formats and returns full account details as a structured string.
     */
    public String getAccountDetailsFormatted() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("          ACCOUNT DETAILS\n");
        sb.append("========================================\n");
        sb.append(String.format("Account Number : %d\n", accountNumber));
        sb.append(String.format("Customer Name  : %s\n", customerName));
        sb.append(String.format("Current Balance: INR %.2f\n", balance));
        sb.append("========================================");
        return sb.toString();
    }
}
