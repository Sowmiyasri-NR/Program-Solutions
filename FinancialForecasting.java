import java.util.Scanner;

public class FinancialForecasting {

    public static double forecastRecursive(double value, double rate, int years) {
        if (years == 0) return value;
        return forecastRecursive(value, rate, years - 1) * (1 + rate);
    }

    public static double forecastIterative(double value, double rate, int years) {
        for (int i = 0; i < years; i++) {
            value *= (1 + rate);
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial investment amount: ");
        double initialValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (in %): ");
        double growthRatePercent = scanner.nextDouble();
        double growthRate = growthRatePercent / 100;

        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();

        double resultRecursive = forecastRecursive(initialValue, growthRate, years);
        double resultIterative = forecastIterative(initialValue, growthRate, years);

        System.out.printf("Forecast after %d years (Recursive): %.2f\n", years, resultRecursive);
        System.out.printf("Forecast after %d years (Iterative): %.2f\n", years, resultIterative);

        scanner.close();
    }
}
