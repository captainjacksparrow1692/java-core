package lesson12.Task2.task5;

import java.util.function.BiFunction;

public class BiFunctionEx {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        System.out.println(sum.apply(3, 3));
    }
}
