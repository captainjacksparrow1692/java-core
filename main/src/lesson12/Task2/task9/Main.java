package lesson12.Task2.task9;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, String> toStr = x -> "Result: " + x;

        BiFunction<Integer, Integer, String> multiplyThenToStr = multiply.andThen(toStr);

        System.out.println(multiplyThenToStr.apply(10, 10));
    }
}
