package lesson12.Task1;
import java.util.*;
import java.util.stream.*;

public class StreamAPI {
    record Product(String name, String category, double price) {}

    public static void main(String[] args) {

        //1. Четные числа и их квадраты
        System.out.println("\t1. Четные числа и их квадраты");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        //через for
        List<Integer> squares = new ArrayList<>();
        for (Integer number : numbers) {
            if(number % 2 ==0){
                squares.add(number*number);
            }
        }
        System.out.println("for: " + squares);

        //через StreamAPI
        List<Integer> squaresStream = numbers.stream().filter(number -> number % 2 ==0).map(n->n*n).collect(Collectors.toList());
        System.out.println("stream: " + squaresStream);
        System.out.println("\n=======================================================\n");

        //2. Подсчитать строки длиннее 5 символов
        System.out.println("\t2. Подсчитать строки длиннее 5 символов");
        List<String> words = List.of("apple", "banana", "pear", "pineapple");

        //через for
        int countFor = 0;
        for(String word : words){
            if(word.length()>5) countFor++;
            }
            System.out.println("for: " + countFor);

        //через StreamAPI
        long countStream = words.stream().filter(word->word.length()>5).count();
        System.out.println("stream: " + countStream);
        System.out.println("\n=======================================================\n");

        //3. Найти минимальное и максимальное число
        System.out.println("\t3. Найти минимальное и максимальное число");
        List<Integer> nums = List.of(10, 2, 33, 4, 25);

        //через for
        int maxNum = nums.get(0);
        int minNum = nums.get(0);
        for(int n : nums){
            if(n>maxNum) maxNum = n;
            if(n<minNum) minNum = n;
        }
        System.out.println("for: " + "Max: " + maxNum + " Min: " + minNum);

        //через StreamAPI
        int maxStream = nums.stream().max(Integer::compareTo).get();
        int minStream = nums.stream().min(Integer::compareTo).get();
        System.out.println("stream: " + "Max: " + maxStream + " Min: " + minStream);
        System.out.println("\n=======================================================\n");

        //4. Средняя длина строк
        System.out.println("\t4. Средняя длина строк");
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        //через for
        int sum = 0;
        for(String name : names) sum += name.length();
        double average = sum/names.size();
        System.out.println("for: " + "Sum: " + sum + " Average: " + average);

        //через StreamAPI
        double averageStream = names.stream().mapToInt(String::length).average().getAsDouble();
        System.out.println("stream: " + "Sum: " + sum + " Average: " + averageStream);
        System.out.println("\n=======================================================\n");

        //5. Удалить дубликаты и отсортировать по длине
        System.out.println("\t5. Удалить дубликаты и отсортировать по длине");
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");

        //через for
        Set<String> uniqueNames = new HashSet<>(input);
        List<String> sortFor = new ArrayList<>(uniqueNames);
        sortFor.sort(Comparator.comparingInt(String::length));
        System.out.println("for: " + sortFor);

        //через StreamAPI
        List<String> sortStream = input.stream().distinct().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("stream: " + sortStream);
        System.out.println("\n=======================================================\n");

        //6. Преобразовать список в Map (строка → длина)
        System.out.println("\t6. Преобразовать список в Map (строка → длина)");
        List<String> fruits = List.of("apple", "banana", "kiwi");

        //через for
        Map<String, Integer> mapFor =  new HashMap<>();
        for (String fruit : fruits){
            mapFor.put(fruit, fruit.length());
        }
        System.out.println("for: " + mapFor);

        //через StreamAPI
        Map<String, Integer> mapSteam = fruits.stream().collect(Collectors.toMap(fruit -> fruit, fruit -> fruit.length()));
        System.out.println("stream: " + mapSteam);
        System.out.println("\n====================================================\n");

        //7. Сгруппировать имена по первой букве
        System.out.println("\t7. Сгруппировать имена по первой букве");
        List<String> nicknames = List.of("Alice", "Andrew", "Bob", "Charlie", "Catherine");

        //через for
        Map<Character, List<String>> combinedFor =  new HashMap<>();
        for(String name : nicknames){
            char first = name.charAt(0);
            combinedFor.computeIfAbsent(first, k -> new ArrayList<>()).add(name);
        }
        System.out.println("for: " + combinedFor);

        //через stream
        Map<Character, List<String>> groupedStream = nicknames.stream().collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("stream: " + groupedStream);
        System.out.println("\n====================================================\n");

        //8. Собрать список имён в одну строку через запятую
        System.out.println("\t8. Собрать список имён в одну строку через запятую");
        List<String> names2 = List.of("Tom", "Jerry", "Spike");

        //через for
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < names2.size(); i++){
            builder.append(names2.get(i));
            if(i != names2.size()-1) builder.append(", ");
        }
        System.out.println("for: " + builder.toString());

        //через stream
        String joined = names2.stream().collect(Collectors.joining(", "));
        System.out.println("stream: " + joined);
        System.out.println("\n====================================================\n");

        //9. Из списка предложений получить все слова
        System.out.println("\t9. Из списка предложений получить все слова");
        List<String> sentences = List.of("Java is cool", "Streams are powerful");

        //через for
        List<String> wordslist = new ArrayList<>();
        for(String sentence : sentences){
            wordslist.addAll(Arrays.asList(sentence.split(" ")));
        }
        System.out.println("stream: " + wordslist);

        //через stream
        List<String> wordList = wordslist.stream().flatMap(word -> Arrays.stream(word.split(" "))).toList();
        System.out.println("stream: " + wordList);
        System.out.println("\n====================================================\n");

        //10. Найти самый дорогой продукт в каждой категории
        System.out.println("10. Найти самый дорогой продукт в каждой категории");
        record Product(String name, String category, double price) {}
        List<Product> products = List.of(
                new Product("Phone", "Electronics", 1200),
                new Product("TV", "Electronics", 1800),
                new Product("Apple", "Fruits", 2.5),
                new Product("Mango", "Fruits", 4.0));

        //через for
        Map<String, Product> maxFor = new HashMap<>();
        for(Product product : products){
            maxFor.merge(product.category(), product,
                    (oldP, newP) -> oldP.price() > newP.price() ? oldP : newP);

        }
        System.out.println("for: " + maxFor);
        //через stream
        Map<String, Product> maxStream2 = products.stream().collect(Collectors.groupingBy(Product::category, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Product::price)), Optional::get)));
        System.out.println("stream: " + maxStream2);
        System.out.println("\n====================================================\n");

    }
}

