package lesson12.Task2.task13;

import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T item : list) {
            consumer.accept(item);
        }
    }

    public static void main(String[] args) {
        List<String> names = List.of("Java", "Uzum", "Stream API");
        forEach(names, System.out::println);
    }
}
