import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountType;
    private double balance;

    public Account(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }
}

class User {
    private String username;
    private String password;
    private Map<String, Account> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void createAccount(String accountNumber, String accountType) {
        Account account = new Account(accountNumber, accountType);
        accounts.put(accountNumber, account);
    }

    public void performTransaction(String accountNumber, double amount, boolean isDeposit) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        } else {
            System.out.println("Account not found");
        }
    }

    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("Account not found");
            return -1;
        }
    }

    public List<String> getAccountNumbers() {
        return new ArrayList<>(accounts.keySet());
    }
}

public class OnlineBankingSystem {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        // Register some users
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);

        // Perform operations
        user1.createAccount("123456789", "Savings");
        user1.performTransaction("123456789", 100.0, true);
        user1.performTransaction("123456789", 50.0, false);

        List<String> accountNumbers = user1.getAccountNumbers();
        for (String accNum : accountNumbers) {
            System.out.println("Account Number: " + accNum);
            System.out.println("Account Type: " + user1.getBalance(accNum));
        }
    }
}
