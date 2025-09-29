package task56;

import java.util.Scanner;

public class Main {
    //5 задание
    public static int sumDigits(int n) {
        n = Math.abs(n);  // чтобы работало и для отрицательных чисел
        int sum = 0;

        while (n > 0) {
            sum += n % 10; // последняя цифра
            n /= 10;       // убираем последнюю цифру
        }

        return sum;
    }

    //6 задание
    public static int reverseNumber(int n) {
        boolean isNegative = n < 0;
        String str = Integer.toString(Math.abs(n));
        String reversedStr = new StringBuilder(str).reverse().toString();
        int reversedNum = Integer.parseInt(reversedStr);

        return isNegative ? -reversedNum : reversedNum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите число: ");
        int num = sc.nextInt();

        System.out.println("Сумма цифр числа " + num + " = " + sumDigits(num));

        num = 1234;
        System.out.println("Перевёрнуто: " + reverseNumber(num));
    }


}
