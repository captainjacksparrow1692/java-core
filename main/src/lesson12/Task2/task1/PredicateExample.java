package lesson12.Task2.task1;

import java.awt.*;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<String> checking = s -> s != null && !s.isEmpty() && s.length() > 3;
        System.out.println(checking.test("Hello World!")); // absolute true
        System.out.println(checking.test("Hi")); // false
    }
}
