# Bank Management System

A robust, interactive, and beginner-friendly Java console application designed to manage customer bank accounts and maintain accurate transaction histories. Built strictly using **Core Java** and fundamental **Object-Oriented Programming (OOP)** concepts, this system provides safe, menu-driven banking operations including account creation, deposits, withdrawals, balance checks, detailed account reviews, and complete transaction auditing.

---

## Table of Contents

1. [Project Title](#bank-management-system)
2. [Project Description](#project-description)
3. [Features](#features)
4. [Technologies Used](#technologies-used)
5. [OOP Concepts Used](#oop-concepts-used)
6. [Data Structures Used](#data-structures-used)
7. [Transaction Rules](#transaction-rules)
8. [Project Structure](#project-structure)
9. [How to Compile](#how-to-compile)
10. [How to Run](#how-to-run)
11. [Sample Usage](#sample-usage)
12. [Future Enhancements](#future-enhancements)

---

## Project Description

The **Bank Management System** simulates the core operations of a commercial banking service in a secure, in-memory environment. It offers bank administrators and customers the ability to open new accounts with optional initial deposits, execute instant monetary deposits and withdrawals with automated balance updates, view structured customer reports, and inspect time-stamped transaction ledgers for any given account.

The project is structured with high code quality, defensive input handling (preventing crashes against invalid or unexpected inputs), and adherence to strict financial transaction integrity constraints.

---

## Features

- **1. Create Account**: Register a new bank account with a unique numeric account number, customer name, and optional initial deposit ($\ge 0$).
- **2. Deposit Money**: Safely deposit any positive monetary amount into an existing account, instantly updating the balance and recording the transaction.
- **3. Withdraw Money**: Safely withdraw funds from an existing account, strictly checking for positive amounts and preventing overdrafts / insufficient balance.
- **4. Check Account Balance**: Quickly query and display the current balance and account holder name for any account number.
- **5. Show Account Details**: View structured account information including Account Number, Customer Name, and Current Balance in an aligned banner.
- **6. View Transaction History**: Review a formatted ledger table of all successful deposits and withdrawals for a given account, complete with timestamps and resulting balance after each transaction.
- **7. View All Accounts**: Display an administrative summary table containing all customer accounts currently registered in the system.
- **8. Safe Exit**: Gracefully close the application with a goodbye message.
- **Defensive Error Handling**: Comprehensive validation prevents crashes on non-numeric inputs, negative amounts, duplicate account numbers, empty names, or non-existent accounts.

---

## Technologies Used

- **Programming Language**: Java (Core Java / JDK 8+)
- **Standard Libraries**:
  - `java.util.Scanner`: Interactive console input parsing.
  - `java.util.ArrayList`: Dynamic in-memory record storage.
  - `java.util.Collections`: Immutable data encapsulation.
  - `java.time.LocalDateTime` & `java.time.format.DateTimeFormatter`: Formatted transaction timestamps.
- **Build / Execution**: Standard Java CLI (`javac` and `java`).

---

## OOP Concepts Used

1. **Encapsulation & Data Hiding**:
   - Class fields (`accountNumber`, `customerName`, `balance`, `transactionHistory`) are declared `private`.
   - Access to state is mediated through public getter methods and validated business logic methods (`deposit()`, `withdraw()`).
   - Transaction lists are returned using `Collections.unmodifiableList()` to prevent external tampering.
2. **Classes and Objects**:
   - `Account`: Models a bank account entity containing state and behaviors.
   - `Transaction`: Represents an individual financial movement entry.
   - `BankManagementSystem`: Coordinates system menus, user I/O, and account storage.
3. **Constructors**:
   - Parameterized constructors ensure valid initialization and automatic logging of opening deposits.
4. **Aggregation / Object Relationships**:
   - An `Account` object has-a collection of `Transaction` objects (`ArrayList<Transaction>`).
   - `BankManagementSystem` manages a collection of `Account` objects (`ArrayList<Account>`).
5. **Business Logic & Validation**:
   - Integrity constraints are checked at the domain level before mutating states.

---

## Data Structures Used

- **`ArrayList<Account>`**:
  - Dynamically resizable list storing all bank accounts in memory during application execution.
- **`ArrayList<Transaction>`**:
  - Dedicated list stored within each `Account` instance to maintain a chronological ledger of all successful operations.

---

## Transaction Rules

1. **Initial Deposits**:
   - If an account is created with an initial deposit $> 0$, an initial `DEPOSIT` transaction is automatically recorded.
   - If created with `0.00`, balance starts at 0 with no initial transaction record.
2. **Successful Deposits**:
   - Adds amount to balance.
   - Records transaction type (`DEPOSIT`), `amount`, `balanceAfter`, and `dateTime`.
3. **Successful Withdrawals**:
   - Deducts amount from balance.
   - Records transaction type (`WITHDRAWAL`), `amount`, `balanceAfter`, and `dateTime`.
4. **Failed Transactions Are Never Recorded**:
   - Zero or negative deposits ($amount \le 0$).
   - Zero or negative withdrawals ($amount \le 0$).
   - Withdrawals exceeding the available balance ($amount > balance$).
   - Operations on non-existent accounts.

---

## Project Structure

```
BankManagementSystem/
├── Account.java                # Account entity, encapsulation, balance operations
├── Transaction.java            # Transaction entity and formatting
├── BankManagementSystem.java   # Main CLI runner, menus, and validation
├── README.md                   # Project overview, instructions, and guides
├── PROJECT_DOCUMENTATION.md    # Detailed architecture and task documentation
└── .gitignore                  # Git ignore rules for build artifacts
```

---

## How to Compile

Navigate to the project root directory in your command line or terminal:

```bash
javac Account.java Transaction.java BankManagementSystem.java
```

---

## How to Run

After compilation, launch the console application using:

```bash
java BankManagementSystem
```

---

## Sample Usage

### 1. Main Menu
```text
========================================
        BANK MANAGEMENT SYSTEM
========================================
1. Create Account
2. Deposit Money
3. Withdraw Money
4. Check Account Balance
5. Show Account Details
6. View Transaction History
7. View All Accounts
8. Exit

Enter your choice: 1
```

### 2. Creating an Account
```text
--- CREATE ACCOUNT ---
Enter Account Number: 1001
Enter Customer Name: John Doe
Enter Initial Deposit: 5000
Account created successfully.
```

### 3. Depositing Money
```text
--- DEPOSIT MONEY ---
Enter Account Number: 1001
Enter Deposit Amount: 2500
Deposit successful.
Updated Balance: INR 7500.00
```

### 4. Withdrawing Money
```text
--- WITHDRAW MONEY ---
Enter Account Number: 1001
Enter Withdrawal Amount: 1500
Withdrawal successful.
Updated Balance: INR 6000.00
```

### 5. Viewing Account Details
```text
--- SHOW ACCOUNT DETAILS ---
Enter Account Number: 1001
========================================
          ACCOUNT DETAILS
========================================
Account Number : 1001
Customer Name  : John Doe
Current Balance: INR 6000.00
========================================
```

### 6. Viewing Transaction History
```text
--- VIEW TRANSACTION HISTORY ---
Enter Account Number: 1001
========================================================
                  TRANSACTION HISTORY
========================================================
Type          Amount          Balance After       Date/Time
--------------------------------------------------------
DEPOSIT       5000.00         5000.00             2026-10-03 21:30:15
DEPOSIT       2500.00         7500.00             2026-10-03 21:31:02
WITHDRAWAL    1500.00         6000.00             2026-10-03 21:32:18
========================================================
```

### 7. Viewing All Accounts
```text
--- ALL ACCOUNTS ---
================================================================================
                                ALL ACCOUNTS
================================================================================
Account No       | Customer Name                  | Balance
--------------------------------------------------------------------------------
1001             | John Doe                       | INR 6000.00
================================================================================
```

---

## Future Enhancements

- **Persistent Storage**: Integration with relational databases (MySQL/PostgreSQL) via JDBC or file-based serialization (JSON/CSV).
- **Multiple Account Types**: Support for Savings, Checking, and Fixed Deposit accounts with interest calculation via polymorphism.
- **Funds Transfer**: Inter-account money transfers with atomic transactional logging.
- **PIN / Authentication**: Multi-user credentials and role-based access for Customers vs. Bank Managers.
- **Account Statements Export**: Exporting transaction history to PDF or CSV formats.
