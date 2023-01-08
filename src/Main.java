import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        double amount;

        double convertedAmount;
        String firstCurrency;
        String secondCurrency;
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome");
        System.out.println("Here is the list of available currencies that can be converted: ");
        System.out.println(ConversionRate.currencyName());
        System.out.println("Euro (EUR)\n" + "US Dollar (USD)\n" + "Australian Dollar (AUD)\n");
        System.out.println("Choose first currency");
        firstCurrency = sc.next();
        System.out.println("How much bro?");
        amount = sc.nextDouble();
        System.out.println("Choose second currency");
        secondCurrency = sc.next();

        convertedAmount = Double.parseDouble(ConversionRate.ConversionRate(String.valueOf(amount), firstCurrency, secondCurrency));
        System.out.println(convertedAmount);

    }
}

    
