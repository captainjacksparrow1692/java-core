package lesson12.Task2.task2;

import java.util.function.Function;

public class ReturnFunction {
    public static void main(String[] args) {
        Function<String, Integer> lengthString = s -> s.length();
        System.out.println(lengthString.apply("Hello World!"));
    }
}
