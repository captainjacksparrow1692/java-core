package lesson12.Task2.task6;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, String> trim = String::trim;
        Function<String, String> toUpperCase = String::toUpperCase;

        Function<String, String> trimThenUpper = trim.andThen(toUpperCase);

        System.out.println(trimThenUpper.apply("    java  "));
    }
}
