package lesson12.Task2.task7;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> print = System.out::println;
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        Consumer<String> combined = print.andThen(printLength);

        combined.accept("Java-core");
    }
}
