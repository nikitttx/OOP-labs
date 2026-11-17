package org.example;

import org.example.exceptions.AccountException;
import org.example.model.BankAccount;
import org.example.model.CheckingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientInterface {
    private final List<BankAccount> accounts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private long nextAccountNumber = 1;

    public static void main(String[] args) {
        new ClientInterface().start();
    }

    public void start() {
        while (true) {
            System.out.println("\n1 - Create account\n2 - Delete account\n3 - Deposit"
                    + "\n4 - Withdraw\n5 - Print account info\n6 - Exit");
            try {
                switch (read("Choose your option")) {
                    case "1" -> addNewAccount();
                    case "2" -> deleteAccount();
                    case "3" -> deposit();
                    case "4" -> withdraw();
                    case "5" -> printInfo();
                    case "6" -> { return; }
                    default -> throw new AccountException("Choose an option from 1 to 6");
                }
            } catch (AccountException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: enter a valid number");
            } catch (NoSuchElementException e) {
                return;
            }
        }
    }

    private String read(String prompt) {
        System.out.print(">>> " + prompt + ": ");
        return scanner.nextLine();
    }

    private void addNewAccount() {
        String name = read("Customer name");
        long userId = Long.parseLong(read("User id"));
        String password = read("Password");
        String confirmation = read("Confirm password");
        for (BankAccount account : accounts) {
            if (account.getUserId().equals(userId)) {
                throw new AccountException("User id already exists");
            }
        }
        if (!password.equals(confirmation)) {
            throw new AccountException("Passwords do not match");
        }
        accounts.add(new CheckingAccount(name, nextAccountNumber, userId, password));
        nextAccountNumber++;
        System.out.println("Account created");
    }

    private BankAccount login() {
        long userId = Long.parseLong(read("User id"));
        String password = read("Password");
        for (BankAccount account : accounts) {
            if (account.getUserId().equals(userId) && account.getUserPassword().equals(password)) {
                return account;
            }
        }
        throw new AccountException("Incorrect user id or password");
    }

    private void deleteAccount() {
        BankAccount account = login();
        if (account.getAccountBalance() != 0) {
            throw new AccountException("Cannot delete an account with a non-zero balance");
        }
        accounts.remove(account);
        System.out.println("Account deleted");
    }

    private void deposit() {
        BankAccount account = login();
        account.deposit(Double.parseDouble(read("Amount")));
        System.out.println("Deposit successful");
    }

    private void withdraw() {
        BankAccount account = login();
        account.withdraw(Double.parseDouble(read("Amount")));
        System.out.println("Withdrawal successful");
    }

    private void printInfo() {
        CheckingAccount account = (CheckingAccount) login();
        System.out.println("Customer name: " + account.getCustomerName());
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getAccountBalance());
        System.out.println("Account type: Checking");
        System.out.println("Daily withdrawal limit: " + account.getDailyWithdrawalLimit());
    }
}
