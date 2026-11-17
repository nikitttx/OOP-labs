package org.example.model;

import org.example.exceptions.AccountException;
import java.time.LocalDate;

public class CheckingAccount extends BankAccount {
    private double dailyWithdrawalLimit = 300;
    private double withdrawnToday;
    private LocalDate withdrawalDate = LocalDate.now();

    public CheckingAccount(String customerName, Long accountNumber, Long userId, String userPassword) {
        super(customerName, accountNumber, userId, userPassword);
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
        checkAmount(dailyWithdrawalLimit);
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    @Override
    public void withdraw(double amount) {
        checkAmount(amount);
        LocalDate today = LocalDate.now();
        if (!today.equals(withdrawalDate)) {
            withdrawalDate = today;
            withdrawnToday = 0;
        }
        if (amount > dailyWithdrawalLimit - withdrawnToday) {
            throw new AccountException("Daily withdrawal limit exceeded");
        }
        super.withdraw(amount);
        withdrawnToday += amount;
    }
}
