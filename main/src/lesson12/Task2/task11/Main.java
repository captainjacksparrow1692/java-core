package lesson12.Task2.task11;

import java.util.function.Predicate;
import java.util.*;

public class Main {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> even = filter(numbers, n -> n % 2 == 0);
        System.out.println(even);
    }
}
