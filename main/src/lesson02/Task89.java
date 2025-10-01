package lesson02;

import java.util.Scanner;

public class Task89 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //8 задание
        int num = 1001;
        while (num % 7 != 0) {
            num++;
        }
        System.out.println("Первое число > 1000, которое делится на 7: " + num);

        // 9. задание
        System.out.print("Введите N для поиска простых чисел: ");
        int limit = sc.nextInt();
        System.out.println("Простые числа до " + limit + ":");
        for (int i = 2; i <= limit; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
