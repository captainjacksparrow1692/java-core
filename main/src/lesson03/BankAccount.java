package lesson03;

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
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Jack Sparrow", 123456789, 1000000);

        System.out.println("Начальный баланс " + account.getBalance());

    }
}
