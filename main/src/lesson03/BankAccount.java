package lesson03;

import java.util.Scanner;

public class BankAccount {
    private String fio;
    private double accountNumber;
    private double balance;

    public BankAccount(String fio, double accountNumber, double balance) {
        this.fio = fio;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Пополнено: " + amount + ". Новый баланс: " + balance);
        } else {
            System.out.println("Сумма должна быть положительной!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Снято: " + amount + ". Новый баланс: " + balance);
        } else if (amount > balance) {
            System.out.println("Недостаточно средств на счёте!");
        } else {
            System.out.println("Сумма должна быть положительной!");
        }
    }

    public double getBalance() {
        return balance;
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount("Jack Sparrow", 123465789, 1000000);

        int choice;
        do {
            System.out.println("\n=== Bank Account Menu ===");
            System.out.println("1. Пополнение");
            System.out.println("2. Снятие наличных");
            System.out.println("3. Баланс");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите сумму пополнения: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Введите сумму снятия: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Баланс: " + account.getBalance());
                    break;
                case 0:
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неправильный ввод!");
            }
        } while (choice != 0);

        sc.close();
    }
}