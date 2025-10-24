package lesson12.Task2.task3;

import java.util.UUID;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<UUID>  uuidSupplier = () -> UUID.randomUUID();
        System.out.println("UUID: " + uuidSupplier.get());
    }
}
