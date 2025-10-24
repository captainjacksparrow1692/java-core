package lesson12.Task2.task4;

import java.util.function.Consumer;

public class Upper {
    public static void main(String[] args) {
        Consumer<String> upperCase = s -> System.out.println(s.toUpperCase());
        upperCase.accept("hello world as always");
    }
}
