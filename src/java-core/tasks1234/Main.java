package tasks1234;

import java.util.Scanner;

public class Main {
    public static int sumwithloop(int n) {
        int sum =0;
        for(int i=1;i<=n;i++){
            sum += i;
        }
        return sum;
    }
    //3 задание
    public static int productwithloop(int n){
        int product =1;
        for (int i=1;i<=n;i++){
            product *=i;
        }
        return product;
    }

    //4 задание
    public static int sumofevennums(int n){
        int sum =0;
        for (int i=2; i<=n; i+=2){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args){
        for (int i = 1; i < 100; i++) {
            System.out.println(i + " ");
            if (i % 10 == 0) {
                System.out.println();
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = sc.nextInt();

        System.out.println("Сумма чисел = " + sumwithloop(n));
        System.out.println("Произведение чисел= " +  productwithloop(n));
        System.out.println("Сумма Четных чисел= " + sumofevennums(n));
    }
}
