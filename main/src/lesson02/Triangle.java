package lesson02;

public class Triangle {
    public static void main(String[] args){
        int n = 5;

        // Прямой треугольник
        for (int i=0; i<=n;i++){
            for (int j=0; j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n");

        // Обратный треугольник
        for (int i=n; i>=1; i--){
            for (int j=1; j<=i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n");

        // Зеркальный треугольник
        for (int i=1; i<=n;i++){
            for (int j=i; j<n;j++){
                System.out.print(" ");
            }
            for (int j=1; j<=i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n");

        //Обратный треугольник
        for (int i=n; i>=1; i--){
            for (int j=i; j<=n; j++){
                System.out.print(" ");
            }
            for (int j=1; j<=i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
