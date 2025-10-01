package lesson03;

public class Rectangle {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateSquare(){
        return length * width;
    }

    public double calculatePerimeter(){
        return 2*length + 2*width;
    }

    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(6, 5);
        System.out.println(rectangle.calculateSquare());
        System.out.println(rectangle.calculatePerimeter());
    }
}
