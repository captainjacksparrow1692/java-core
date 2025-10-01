package lesson03;

public class Car {
    private String country;
    private String model;
    private int yearOfManufacture;

    public Car(String country, String model, int yearOfManufacture) {
        this.country = country;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }


    public void printCarInfo() {
        System.out.println("Country: "+ country);
        System.out.println("Model: " + model);
        System.out.println("Year Of Manufacture: " + yearOfManufacture);
    }

    public static void main(String[] args) {
        Car car = new Car("Japan","Nissan",1980);
        car.printCarInfo();
    }
}