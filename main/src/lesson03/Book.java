package lesson03;

import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    private int ISBN;
    private String name;
    private String author;
    private int year;
    private String status;

    public Book(int ISBN, String name, String author, int year, String status) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.year = year;
        this.status = "Доступна";
    }

    //BOOK INFO
    public String getBookInfo(){
        return ISBN + "-" + name + "-" + author + "-" + year + "-" + status;
    }

    //GET BOOK
    public static void getBook(ArrayList<Book> books) {
       if (books.isEmpty()){
           System.out.println("Библиотека пустая.");
       }
       else{
           for (Book book : books){
               System.out.println(book.getBookInfo());
           }
       }
    }

    //ADDING NEW BOOKS
    public static void addNewBook(ArrayList<Book> books, Book newBook) {
        books.add(newBook);
        System.out.println("Добавлено: " + newBook.getBookInfo());
    }

    //reserving
    public void reserveBook() {
        if (status.equals("Доступно")) {
            status = "Зарезервирован";
            System.out.println(name + "Успешно Зарезервирован!");
        } else {
            System.out.println("Попробуйте еще раз");
        }
    }

    //finding books for reserving
    public static Book findBookISBN(ArrayList<Book> books, int ISBN) {
        for (Book b : books) {
            if (b.ISBN == ISBN) {
                return b;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> library = new ArrayList<>();
        int choice;
        do {
            System.out.println("\n\tДобро пожаловать в мир книг");
            System.out.println("1. Информация о книге");
            System.out.println("2. Список книг");
            System.out.println("3. Добавить новую книгу");
            System.out.println("4. Зарезервировать книгу");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Введите ISBN книги: ");
                    int searchIsbn = sc.nextInt();
                    sc.nextLine();

                    Book foundBook = Book.findBookISBN(library, searchIsbn);
                    if (foundBook != null) {
                        System.out.println("Информация о книге:");
                        System.out.println(foundBook.getBookInfo());
                    } else {
                        System.out.println("Книга с таким ISBN не найдена.");
                    }
                    break;
                case 2:
                    System.out.println("\nВсе книги в библиотеке:");
                    Book.getBook(library);
                    break;
                case 3:
                    System.out.println("Добавление новой книги: ");
                    System.out.println("Введите ISBN:");
                    int isbn = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Введите название:");
                    String name = sc.nextLine();

                    System.out.println("Введите ФИО автора:");
                    String author = sc.nextLine();

                    System.out.println("Введите год публикации:");
                    int year = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Введите статус:");
                    String status = sc.nextLine();

                    // создаём новую книгу и добавляем в список
                    Book.addNewBook(library, new Book(isbn, name, author, year, status));

                    // выводим список после добавления
                    Book.getBook(library);
                    break;
                case 4:
                    System.out.println("Резервирование книги по ISBN: ");
                    int scanner = sc.nextInt();
                    sc.nextLine();

                    Book reserveBook = Book.findBookISBN(library, scanner);
                    if (reserveBook != null) {
                        System.out.println(reserveBook.getBookInfo());
                    }
                    else{
                        System.out.println("Книга не найдена ");
                    }
                    break;
                default:
                    System.out.println("Неправильный ввод! Попробуйте ещё раз");
            }
        } while (choice != 0);
    }
}