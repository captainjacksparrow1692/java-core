package task1234;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Вывести числа от 1 до 100 (каждые 10 в строке)
        System.out.println("Числа от 1 до 100:");
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + "\n");
            if (i % 10 == 0) { // каждые 10 чисел переход на новую строку
                System.out.println();
            }
        }

        // Ввод N
        System.out.print("\nВведите число N: ");
        int N = sc.nextInt();

        // 2) Сумма чисел от 1 до N
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
        }
        System.out.println("Сумма чисел от 1 до " + N + " = " + sum);

        // 3) Произведение чисел от 1 до N
        long product = 1;
        for (int i = 1; i <= N; i++) {
            product *= i;
        }
        System.out.println("Произведение чисел от 1 до " + N + " = " + product);

        // 4) Сумма четных чисел от 1 до N
        int evenSum = 0;
        for (int i = 2; i <= N; i += 2) { // идем только по четным
            evenSum += i;
        }
        System.out.println("Сумма четных чисел от 1 до " + N + " = " + evenSum);

    }
}
