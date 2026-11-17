package org.example.model;

import org.example.exceptions.AccountException;

public class BankAccount {
    private String customerName;
    private Long accountNumber;
    private Long userId;
    private String userPassword;
    private double accountBalance;

    public BankAccount(String customerName, Long accountNumber, Long userId, String userPassword) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.userId = userId;
        setUserPassword(userPassword);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        if (userPassword == null || userPassword.length() < 8 || !userPassword.matches(".*[0-9].*")) {
            throw new AccountException("Password must have at least 8 characters and a digit");
        }
        this.userPassword = userPassword;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        if (!Double.isFinite(accountBalance) || accountBalance < 0) {
            throw new AccountException("Balance must be finite and non-negative");
        }
        this.accountBalance = accountBalance;
    }

    protected void checkAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new AccountException("Amount must be finite and greater than zero");
        }
    }

    public void deposit(double amount) {
        checkAmount(amount);
        setAccountBalance(accountBalance + amount);
    }

    public void withdraw(double amount) {
        checkAmount(amount);
        if (amount > accountBalance) {
            throw new AccountException("Insufficient funds");
        }
        setAccountBalance(accountBalance - amount);
    }
}
