package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("📍 Сцена 1: Пробуждение");
        System.out.println("Ты приходишь в себя на холодном песке у берега озера...");
        System.out.println("Выбор:");
        System.out.println("1. Пойти на восток, где в тумане мерцает свет. (Сцена 2A)");
        System.out.println("2. Пойти на север, как советует записка. (Сцена 2B)");
        System.out.println("3. Остаться у озера. (Сцена 2C)");
        System.out.print("Твой выбор: ");
        int choice1 = sc.nextInt();

        if (choice1 == 1) {
            // Сцена 2A
            System.out.println("\n📍 Сцена 2A: Восточный свет");
            System.out.println("Ты находишь старую электростанцию. Прожектор слепит глаза...");
            System.out.println("Выбор:");
            System.out.println("1. Ответить: «Я человек, я потерялся!» (Сцена 3A)");
            System.out.println("2. Убежать назад в лес. (Сцена 3B)");
            System.out.print("Твой выбор: ");
            int choice2 = sc.nextInt();

            if (choice2 == 1) {
                System.out.println("\n📍 Сцена 3A: Попытка общения");
                System.out.println("Тебя усыпляют. Очнулся в лаборатории...");
                System.out.println("Концовка: ❌ Неудача");
            } else {
                System.out.println("\n📍 Сцена 3B: Побег");
                System.out.println("Ты исчез без следа...");
                System.out.println("Концовка: ❌ Неудача");
            }

        } else if (choice1 == 2) {
            // Сцена 2B
            System.out.println("\n📍 Сцена 2B: Северный путь");
            System.out.println("Ты находишь заброшенную деревню и дневник...");
            System.out.println("Выбор:");
            System.out.println("1. Остаться в доме до рассвета. (Сцена 3C)");
            System.out.println("2. Выйти и идти дальше в туман. (Сцена 3D)");
            System.out.print("Твой выбор: ");
            int choice2 = sc.nextInt();

            if (choice2 == 1) {
                System.out.println("\n📍 Сцена 3C: Ожидание");
                System.out.println("Выжившие находят тебя утром...");
                System.out.println("Концовка: 🏆 Победа");
            } else {
                System.out.println("\n📍 Сцена 3D: Поиски");
                System.out.println("Ты находишь маяк и рацию...");
                System.out.println("Концовка: 🏆 Победа");
            }

        } else if (choice1 == 3) {
            // Сцена 2C
            System.out.println("\n📍 Сцена 2C: Ожидание у озера");
            System.out.println("Слышен детский голос в тумане...");
            System.out.println("Выбор:");
            System.out.println("1. Пойти на голос. (Сцена 3E)");
            System.out.println("2. Убежать прочь в лес. (Сцена 3F)");
            System.out.print("Твой выбор: ");
            int choice2 = sc.nextInt();

            if (choice2 == 1) {
                System.out.println("\n📍 Сцена 3E: Девочка");
                System.out.println("Ты попал под проклятие озера...");
                System.out.println("Концовка: ☠️ Проклятие озера");
            } else {
                // Сцена 3F
                System.out.println("\n📍 Сцена 3F: Побег в лес");
                System.out.println("Ты находишь подземный бункер...");
                System.out.println("Выбор:");
                System.out.println("1. Разрушить центр управления. (Сцена 4A)");
                System.out.println("2. Присоединиться к проекту. (Сцена 4B)");
                System.out.print("Твой выбор: ");
                int choice3 = sc.nextInt();

                if (choice3 == 1) {
                    System.out.println("\n📍 Сцена 4A: Разрушение");
                    System.out.println("Ты спас мир, но погиб...");
                    System.out.println("Концовка: 💀 Герой");
                } else {
                    System.out.println("\n📍 Сцена 4B: Присоединение");
                    System.out.println("Ты стал частью проекта...");
                    System.out.println("Концовка: 🕵 Тайный участник");
                }
            }
        } else {
            System.out.println("Неверный выбор. Игра окончена.");
        }

        sc.close();
    }
}

