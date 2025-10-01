package lesson03;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Hello my name is " + name + " and age is " + age);
    }

    public static void main(String[] args) {
        Person person = new Person("Jack", 21);
        person.introduce();
    }
}

