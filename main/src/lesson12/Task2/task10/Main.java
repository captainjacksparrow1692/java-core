package lesson12.Task2.task10;

import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<String> operator = s -> s + "!!!";
        System.out.println(operator.apply("Hello"));
    }
}
