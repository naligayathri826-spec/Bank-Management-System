import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a financial transaction (DEPOSIT or WITHDRAWAL) associated with a bank account.
 * Maintains an immutable record of transaction type, amount, resulting balance, and timestamp.
 */
public class Transaction {
    private final String transactionType;
    private final double amount;
    private final double balanceAfter;
    private final String dateTime;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor to create a transaction with an automatically generated current timestamp.
     *
     * @param transactionType Type of transaction ("DEPOSIT" or "WITHDRAWAL")
     * @param amount          Amount of money involved in the transaction
     * @param balanceAfter    Account balance immediately after this transaction
     */
    public Transaction(String transactionType, double amount, double balanceAfter) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now().format(FORMATTER);
    }

    /**
     * Overloaded constructor allowing explicit date/time string (useful for testing or migration).
     *
     * @param transactionType Type of transaction ("DEPOSIT" or "WITHDRAWAL")
     * @param amount          Amount of money involved in the transaction
     * @param balanceAfter    Account balance immediately after this transaction
     * @param dateTime        Formatted date and time string
     */
    public Transaction(String transactionType, double amount, double balanceAfter, String dateTime) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = dateTime;
    }

    // --- Getter Methods (Encapsulation) ---

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDateTime() {
        return dateTime;
    }

    /**
     * Returns a formatted table row string for transaction history tables.
     */
    @Override
    public String toString() {
        return String.format("%-13s %-15.2f %-19.2f %s",
                transactionType, amount, balanceAfter, dateTime);
    }
}
