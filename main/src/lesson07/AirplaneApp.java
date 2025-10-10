package lesson07;
import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Seat implements Serializable {
    private int row;
    private char line;
    private String seatClass;
    private String passengerName;
    private String status; // "свободен", "забронирован", "оплачен"
    private LocalDateTime bookingTime;

    public Seat(int row, char line, String seatClass) {
        this.row = row;
        this.line = line;
        this.seatClass = seatClass;
        this.passengerName = null;
        this.status = "свободен";
        this.bookingTime = null;
    }

    public String getSeatCode() {
        return row + String.valueOf(line);
    }

    public String getSeatClass() {
        return seatClass;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getStatus() {
        if ("забронирован".equalsIgnoreCase(status) && bookingTime != null) {
            long minutes = ChronoUnit.MINUTES.between(bookingTime, LocalDateTime.now());
            if (minutes == 24) {
                cancel(false);
            }
        }
        return status;
    }

    public void book(String passengerName) {
        if ("свободен".equalsIgnoreCase(getStatus())) {
            this.passengerName = passengerName;
            this.status = "забронирован";
            this.bookingTime = LocalDateTime.now();
            System.out.println("Место " + getSeatCode() + " забронировано на имя " + passengerName);
        } else {
            System.out.println("Место " + getSeatCode() + " недоступно. Статус: " + getStatus());
        }
    }

    public void pay() {
        if ("забронирован".equalsIgnoreCase(getStatus())) {
            this.status = "оплачен";
            System.out.println("Место " + getSeatCode() + " успешно оплачено!");
        } else {
            System.out.println("Оплата невозможна. Текущий статус: " + getStatus());
        }
    }

    public void cancel(boolean showMsg) {
        this.status = "свободен";
        this.passengerName = null;
        this.bookingTime = null;
        if (showMsg) {
            System.out.println("Бронь на место " + getSeatCode() + " аннулирована.");
        }
    }

    @Override
    public String toString() {
        return String.format("Место %-3s | Класс: %-7s | Статус: %-11s | Пассажир: %s",
                getSeatCode(),
                seatClass,
                getStatus(),
                (passengerName == null ? "-" : passengerName));
    }

    public boolean isBooked() {
        return !"свободен".equalsIgnoreCase(getStatus());
    }
}

class Airline implements Serializable {
    private static final long serialVersionUID = 1L;

    private String flightNumber;
    private LocalDateTime departureDateTime;
    private List<Seat> seatList;
    private static final String FILE_NAME = "seats.txt";

    public Airline(String flightNumber, LocalDateTime departureDateTime) {
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDateTime;
        this.seatList = new ArrayList<>();
        loadSeats();
    }

    private void initializeSeats() {
        // бизнес
        for (int row = 1; row <= 5; row++) {
            for (char line : new char[]{'A', 'B', 'D', 'F'}) {
                seatList.add(new Seat(row, line, "Бизнес"));
            }
        }
        // эконом
        for (int row = 6; row <= 21; row++) {
            for (char line = 'A'; line <= 'F'; line++) {
                seatList.add(new Seat(row, line, "Эконом"));
            }
        }
    }

    private Seat findSeatByCode(String seatCode) {
        for (Seat seat : seatList) {
            if (seat.getSeatCode().equalsIgnoreCase(seatCode)) {
                return seat;
            }
        }
        return null;
    }

    public void showSeats() {
        clearExpiredBookings();
        System.out.println("\n=== Список мест ===");
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
        seat.book(passengerName);
    }

    public void paySeat(String seatCode) {
        Seat seat = findSeatByCode(seatCode);
        if (seat != null) {
            seat.pay();
        } else {
            System.out.println("Место не найдено.");
        }
    }

    public void cancelSeat(String seatCode) {
        Seat seat = findSeatByCode(seatCode);
        if (seat != null && !"свободен".equalsIgnoreCase(seat.getStatus())) {
            seat.cancel(true);
        } else {
            System.out.println("Место не найдено или уже свободно.");
        }
    }

    public void showSeatInfo(String seatCode) {
        Seat seat = findSeatByCode(seatCode);
        if (seat == null) {
            System.out.println("Место " + seatCode + " не найдено!");
        } else {
            System.out.println("\n=== Информация о месте ===");
            System.out.println(seat);
            if ("Бизнес".equalsIgnoreCase(seat.getSeatClass())) {
                System.out.println("Это место находится в бизнес-классе.");
            } else {
                System.out.println("Это место находится в эконом-классе.");
            }
        }
    }

    private void loadSeats() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            initializeSeats();
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            seatList = (List<Seat>) in.readObject();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке. Создаём новые места.");
            initializeSeats();
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(seatList);
            System.out.println("Данные сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    public void clearExpiredBookings() {
        for (Seat seat : seatList) {
            seat.getStatus();
        }
    }

    public void showFlightInfo() {
        System.out.println("\nРейс: " + flightNumber);
        System.out.println("Дата и время вылета: " + departureDateTime);
    }

    public List<Seat> getSeatList() {
        return seatList;
    }
}

public class AirplaneApp {
    public static void main(String[] args) {
        Airline airline = new Airline("HY-2025", LocalDateTime.of(2025, 10, 15, 14, 30));
        airline.clearExpiredBookings();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n\tМЕНЮ");
            System.out.println("1. Информация о рейсе");
            System.out.println("2. Показать все места");
            System.out.println("3. Забронировать место");
            System.out.println("4. Оплатить бронь");
            System.out.println("5. Снять бронь");
            System.out.println("6. Информация о месте");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите номер пункта меню.");
                continue;
            }

            switch (choice) {
                case 1:
                    airline.showFlightInfo();
                    break;
                case 2:
                    airline.showSeats();
                    break;
                case 3:
                    System.out.print("Введите класс (Бизнес / Эконом): ");
                    String bookSeat = sc.nextLine().trim();

                    System.out.print("Введите код места (например 4B): ");
                    String code = sc.nextLine().toUpperCase().trim();
                    System.out.print("Введите имя пассажира: ");
                    String name = sc.nextLine().trim();
                    airline.bookSeat(code, name);
                    break;
                case 4:
                    System.out.print("Введите класс (Бизнес / Эконом): ");
                    String payClass = sc.nextLine().trim();

                    System.out.print("Введите код места: ");
                    String payCode = sc.nextLine().toUpperCase();

                    Seat seatToPay = airline.getSeatList().stream()
                            .filter(s -> s.getSeatCode().equalsIgnoreCase(payCode))
                            .findFirst().orElse(null);

                    if (seatToPay == null) {
                        System.out.println("Место не найдено!");
                    } else if (!seatToPay.getSeatClass().equalsIgnoreCase(payClass)) {
                        System.out.println("Это место находится в " + seatToPay.getSeatClass() + " классе!");
                    } else {
                        seatToPay.pay();
                    }
                    break;
                case 5:
                    System.out.print("Введите класс (Бизнес / Эконом): ");
                    String cancelClass = sc.nextLine().trim();

                    System.out.print("Введите код места (например 4B): ");
                    String cancelCode = sc.nextLine().toUpperCase();

                    Seat seatToCancel = airline.getSeatList().stream()
                            .filter(s -> s.getSeatCode().equalsIgnoreCase(cancelCode))
                            .findFirst().orElse(null);

                    if (seatToCancel == null) {
                        System.out.println("Место не найдено!");
                    } else if (!seatToCancel.getSeatClass().equalsIgnoreCase(cancelClass)) {
                        System.out.println("Ошибка: место " + cancelCode + " находится в " +
                                seatToCancel.getSeatClass() + " классе!");
                    } else if (seatToCancel.getStatus().equals("свободен")) {
                        System.out.println("Место " + cancelCode + " уже свободно!");
                    } else {
                        seatToCancel.cancel(true);
                    }
                    break;
                case 6:
                    System.out.print("Введите класс (Бизнес / Эконом): ");
                    String infoClass = sc.nextLine().trim();

                    System.out.print("Введите код места (например 4A): ");
                    String infoCode = sc.nextLine().toUpperCase();

                    Seat seatInfo = airline.getSeatList().stream()
                            .filter(s -> s.getSeatCode().equalsIgnoreCase(infoCode))
                            .findFirst().orElse(null);

                    if (seatInfo == null) {
                        System.out.println("Место " + infoCode + " не найдено!");
                    } else if (!seatInfo.getSeatClass().equalsIgnoreCase(infoClass)) {
                        System.out.println("Ошибка: место " + infoCode + " находится в " +
                                seatInfo.getSeatClass() + " классе!");
                    } else {
                        System.out.println("\n\tИнформация о месте");
                        System.out.println(seatInfo);
                        if (seatInfo.getSeatClass().equalsIgnoreCase("Бизнес")) {
                            System.out.println("Это место находится в бизнес-классе.");
                        } else {
                            System.out.println("Это место находится в эконом-классе.");
                        }
                    }
                    break;
                case 0:
                    airline.save();
                    running = false;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}

