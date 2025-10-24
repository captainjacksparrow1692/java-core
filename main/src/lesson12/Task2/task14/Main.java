package lesson12.Task2.task14;

import java.util.*;
import java.util.function.Supplier;

public class Main {
    public static <T> List<T> generate(Supplier<T> supplier, int n) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    public static void main(String[] args) {
        Supplier<Integer> randomNum = () -> (int) (Math.random() * 100);
        List<Integer> numbers = generate(randomNum, 10);
        System.out.println(numbers);
    }
}
