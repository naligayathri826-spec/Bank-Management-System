import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Task 4 - Bank Management System
 * A robust, console-based banking management application written in Core Java.
 * Demonstrates Object-Oriented Programming (OOP) principles, clean architecture,
 * data encapsulation, list collections, and safe input validation.
 */
public class BankManagementSystem {

    // Central in-memory storage of bank accounts using ArrayList
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reconfigures scanner input stream (used for automated testing).
     */
    public static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMainMenu();
            String choiceInput = scanner.nextLine().trim();

            switch (choiceInput) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    depositMoney();
                    break;
                case "3":
                    withdrawMoney();
                    break;
                case "4":
                    checkAccountBalance();
                    break;
                case "5":
                    showAccountDetails();
                    break;
                case "6":
                    viewTransactionHistory();
                    break;
                case "7":
                    viewAllAccounts();
                    break;
                case "8":
                    System.out.println("\nThank you for using the Bank Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 8.");
                    break;
            }

            if (running) {
                System.out.println(); // Add clean spacing between menu iterations
            }
        }
    }

    /**
     * Displays the main menu banner and options.
     */
    private static void displayMainMenu() {
        System.out.println("========================================");
        System.out.println("        BANK MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Account Balance");
        System.out.println("5. Show Account Details");
        System.out.println("6. View Transaction History");
        System.out.println("7. View All Accounts");
        System.out.println("8. Exit");
        System.out.print("\nEnter your choice: ");
    }

    /**
     * Feature 1: Create a new customer account with account number, customer name, and initial deposit.
     */
    public static void createAccount() {
        System.out.println("\n--- CREATE ACCOUNT ---");

        // 1. Validate Account Number
        System.out.print("Enter Account Number: ");
        String accNumStr = scanner.nextLine().trim();

        if (!isNumeric(accNumStr)) {
            System.out.println("Invalid account number. Please enter a valid numeric account number.");
            return;
        }

        long accountNumber;
        try {
            accountNumber = Long.parseLong(accNumStr);
            if (accountNumber <= 0) {
                System.out.println("Invalid account number. Account number must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid account number. Number out of acceptable range.");
            return;
        }

        if (findAccount(accountNumber) != null) {
            System.out.println("Account with this number already exists. Please choose a unique account number.");
            return;
        }

        // 2. Validate Customer Name
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        // 3. Validate Initial Deposit
        System.out.print("Enter Initial Deposit: ");
        String depositStr = scanner.nextLine().trim();

        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(depositStr);
            if (initialDeposit < 0) {
                System.out.println("Invalid amount. Please enter a positive amount or zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid numeric value.");
            return;
        }

        // Create Account and store in collection
        Account newAccount = new Account(accountNumber, customerName, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully.");
    }

    /**
     * Feature 2: Deposit money into an existing account.
     */
    public static void depositMoney() {
        System.out.println("\n--- DEPOSIT MONEY ---");
        Account account = getAccountFromUserPrompt();
        if (account == null) {
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        String amountStr = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid numeric value.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }

        boolean success = account.deposit(amount);
        if (success) {
            System.out.println("Deposit successful.");
            System.out.printf("Updated Balance: INR %.2f\n", account.getBalance());
        } else {
            System.out.println("Invalid amount. Please enter a positive amount.");
        }
    }

    /**
     * Feature 3: Withdraw money from an existing account.
     */
    public static void withdrawMoney() {
        System.out.println("\n--- WITHDRAW MONEY ---");
        Account account = getAccountFromUserPrompt();
        if (account == null) {
            return;
        }

        System.out.print("Enter Withdrawal Amount: ");
        String amountStr = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid numeric value.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Withdrawal successful.");
            System.out.printf("Updated Balance: INR %.2f\n", account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Please check the amount.");
        }
    }

    /**
     * Feature 4: Check and display the current balance of an account.
     */
    public static void checkAccountBalance() {
        System.out.println("\n--- CHECK ACCOUNT BALANCE ---");
        Account account = getAccountFromUserPrompt();
        if (account == null) {
            return;
        }

        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Customer Name  : " + account.getCustomerName());
        System.out.printf("Current Balance: INR %.2f\n", account.getBalance());
    }

    /**
     * Feature 5: Show formatted account details banner.
     */
    public static void showAccountDetails() {
        System.out.println("\n--- SHOW ACCOUNT DETAILS ---");
        Account account = getAccountFromUserPrompt();
        if (account == null) {
            return;
        }

        System.out.println(account.getAccountDetailsFormatted());
    }

    /**
     * Feature 6: View the full transaction history of an account.
     */
    public static void viewTransactionHistory() {
        System.out.println("\n--- VIEW TRANSACTION HISTORY ---");
        Account account = getAccountFromUserPrompt();
        if (account == null) {
            return;
        }

        List<Transaction> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transaction history available.");
            return;
        }

        System.out.println("========================================================");
        System.out.println("                  TRANSACTION HISTORY");
        System.out.println("========================================================");
        System.out.printf("%-13s %-15s %-19s %s\n", "Type", "Amount", "Balance After", "Date/Time");
        System.out.println("--------------------------------------------------------");
        for (Transaction tx : history) {
            System.out.println(tx);
        }
        System.out.println("========================================================");
    }

    /**
     * Feature 7: View all registered customer accounts in a structured table.
     */
    public static void viewAllAccounts() {
        System.out.println("\n--- ALL ACCOUNTS ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("================================================================================");
        System.out.println("                                ALL ACCOUNTS");
        System.out.println("================================================================================");
        System.out.printf("%-16s | %-35s | %s\n", "Account No", "Customer Name", "Balance");
        System.out.println("--------------------------------------------------------------------------------");
        for (Account acc : accounts) {
            System.out.printf("%-16d | %-35s | INR %.2f\n",
                    acc.getAccountNumber(), acc.getCustomerName(), acc.getBalance());
        }
        System.out.println("================================================================================");
    }

    // --- Helper Methods ---

    /**
     * Prompts the user for an account number and finds the corresponding Account object.
     * Returns null and prints "Account not found." if no match is located.
     */
    private static Account getAccountFromUserPrompt() {
        System.out.print("Enter Account Number: ");
        String accNumStr = scanner.nextLine().trim();

        if (!isNumeric(accNumStr)) {
            System.out.println("Account not found.");
            return null;
        }

        try {
            long accountNumber = Long.parseLong(accNumStr);
            Account account = findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found.");
                return null;
            }
            return account;
        } catch (NumberFormatException e) {
            System.out.println("Account not found.");
            return null;
        }
    }

    /**
     * Finds an account in the accounts list by account number.
     *
     * @param accountNumber The unique account number to search
     * @return The Account object if found, null otherwise
     */
    public static Account findAccount(long accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    /**
     * Utility method to check if a string consists entirely of digits.
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter for accounts list (useful for automated testing/inspection).
     */
    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Clears all accounts (useful for test resets).
     */
    public static void clearAccounts() {
        accounts.clear();
    }
}
