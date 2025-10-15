package lesson06;

import java.util.*;  // пакет утилит
import java.io.*;    // пакет для ввода и вывода

class Seat implements Serializable {
    private int row;
    private char line;
    private String seatClass;
    private String passengerName;
    public boolean isBooked;

    public Seat(int row, char line, String seatClass) {
        this.row = row;
        this.line = line;
        this.seatClass = seatClass;
        this.passengerName = null;
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public char getLine() {
        return line;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public boolean getIsBook() {
        return isBooked;
    }

    public String getSeatCode() {
        return row + String.valueOf(line);
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void book(String passengerName) {
        this.isBooked = true;
        this.passengerName = passengerName;
    }

    public void cancel() {
        this.isBooked = false;
        this.passengerName = null;
    }

    public String toString() {
        return String.format("Место %-3s | Класс: %-7s | Статус: %-10s | Пассажир: %s",
                getSeatCode(),
                seatClass,
                (isBooked ? "ЗАНЯТО" : "СВОБОДНО"),
                (passengerName == null ? "-" : passengerName));
    }

    public boolean isBooked() {
        return isBooked;
    }
}

class Airline {
    private List<Seat> seatList = new ArrayList<>();
    private static final String FILE_NAME = "seats.txt";

    public Airline() {
        loadSeats();
    }

    private void initializeSeats() {
        //Бизнес-класс
        for (int row = 1; row <= 5; row++) {
            for (char line : new char[]{'A', 'B', 'D', 'F'}) {
                seatList.add(new Seat(row, line, "Бизнес"));
            }
        }

        //Эконом-класс
        for (int row = 6; row <= 21; row++) {
            for (char line = 'A'; line <= 'F'; line++) {
                seatList.add(new Seat(row, line, "Эконом"));
            }
        }
    }

    private Seat findSeatByCode(String seatCode) {
        for (Seat s : seatList) {
            if (s.getSeatCode().equalsIgnoreCase(seatCode)) {
                return s;
            }
        }
        return null;
    }

    public void showSeats() {
        for (Seat seat : seatList) {
            System.out.println(seat);
        }
    }

    public void bookSeat(String seatCode, String passengerName) {
        Seat seat = findSeatByCode(seatCode);
        if (seat == null) {
            System.out.println("Такого места нет.");
            return;
        }
        if (seat.getIsBook()) {
            System.out.println("Место уже занято.");
        } else {
            seat.book(passengerName);
            System.out.println("Бронирование успешно!");
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(seatList);
            System.out.println("Статус мест сохранён ✅");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }


    public void showSeatInfo(String seatCode) {
        Seat seat = findSeatByCode(seatCode);
        if (seat == null) {
            System.out.println("Место " + seatCode + " не найдено!");
        } else {
            System.out.println("=== Информация о месте ===");
            System.out.println("Ряд: " + seat.getRow());
            System.out.println("Линия: " + seat.getLine());
            System.out.println("Класс: " + seat.getSeatClass());
            System.out.println("Статус: " + (seat.isBooked() ? "ЗАНЯТО" : "СВОБОДНО"));
            System.out.println("Пассажир: " + (seat.getPassengerName() == null ? "-" : seat.getPassengerName()));
        }
    }

    @SuppressWarnings("unchecked")
    private void loadSeats() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            initializeSeats();
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            seatList = (List<Seat>) in.readObject();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке, создаём новые места.");
            initializeSeats();
        }
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void cancelBooking(String cancelCode) {
        Seat seat = findSeatByCode(cancelCode);
        if (seat == null) {
            System.out.println("Такого места нет.");
        } else if (!seat.isBooked()) {
            System.out.println("Это место и так свободно.");
        } else {
            seat.cancel();
            System.out.println("Бронь на место " + cancelCode + " успешно снята!");
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Airline airline = new Airline();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n\t=== МЕНЮ ===");
            System.out.println("1. Показать все места");
            System.out.println("2. Показать схему самолета");
            System.out.println("3. Забронировать место");
            System.out.println("4. Снять бронь");
            System.out.println("5. Информация по месту");
            System.out.println("0. Выход");

            System.out.print("Ваш выбор: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    airline.showSeats();
                    break;

                case 2:
                    airline.getSeatList(); // пока просто возвращает список
                    break;

                case 3:
                    System.out.print("Введите код места (например 4B): ");
                    String code = sc.next().toUpperCase();
                    sc.nextLine(); // очистка буфера

                    System.out.print("Введите имя пассажира: ");
                    String name = sc.nextLine();

                    System.out.print("Введите класс (Бизнес / Эконом): ");
                    String chosenClass = sc.nextLine().trim();

                    Seat seatToBook = null;
                    for (Seat s : airline.getSeatList()) {
                        if (s.getSeatCode().equalsIgnoreCase(code)) {
                            seatToBook = s;
                            break;
                        }
                    }

                    if (seatToBook == null) {
                        System.out.println("Такого места нет!");
                    } else if (!seatToBook.getSeatClass().equalsIgnoreCase(chosenClass)) {
                        System.out.println("Ошибка! Это место относится к классу " + seatToBook.getSeatClass());
                    } else if (seatToBook.isBooked()) {
                        System.out.println("Место уже занято!");
                    } else {
                        airline.bookSeat(code, name);
                    }
                    break;

                case 4:
                    System.out.println("Введите код места (например 4B): ");
                    String cancelCode = sc.nextLine().toUpperCase();
                    airline.cancelBooking(cancelCode);
                    break;
                case 5:
                    System.out.print("Введите код места (например 4A): ");
                    String infoCode = sc.nextLine().toUpperCase();
                    airline.showSeatInfo(infoCode);
                    break;
                case 0:
                    airline.save();
                    running = false;
                    System.out.println("Данные сохранены. До свидания!. Хорошего полета!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}