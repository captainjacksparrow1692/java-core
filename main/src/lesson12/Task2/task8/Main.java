package lesson12.Task2.task8;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven = i -> i % 2 == 0;
        Predicate<Integer> isPositive = i -> i > 0;

        Predicate<Integer> oddOrNegative = isEven.negate().or(isPositive.negate());

        System.out.println(oddOrNegative.test(4));
        System.out.println(oddOrNegative.test(5));
        System.out.println(oddOrNegative.test(-4));
    }
}
