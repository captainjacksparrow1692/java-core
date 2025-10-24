package lesson12.Task2.task12;

import java.util.*;
import java.util.function.Function;

public class Main {
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> words = List.of("Backend", "Uzum", "Java", "Stream API");

        List<Integer> lengths = map(words, String::length);

        System.out.println(lengths);
    }
}
