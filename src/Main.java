import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        double amount = 0;
        double convertedAmount;
        String firstCurrency;
        String secondCurrency;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to our currency converter");
        System.out.println(Converter.getCurrencyList());
        System.out.println("Please choose the first currency: ");
        firstCurrency = sc.next();

        if (Converter.checkCurrency(firstCurrency) == false) {
            do {
                System.out.println("Wrong currency used... Choose another one...");
                firstCurrency = sc.next();
            } while (Converter.checkCurrency(firstCurrency) == false);
        }


        System.out.println("Please choose the second currency: ");
        secondCurrency = sc.next();


        if (secondCurrency.equalsIgnoreCase(firstCurrency) || Converter.checkCurrency(secondCurrency) == false) {
            do {
                System.out.println("Choose another one...");
                secondCurrency = sc.next();
            } while (secondCurrency.equalsIgnoreCase(firstCurrency) || Converter.checkCurrency(secondCurrency) == false);
        }

        do {

            try {
                System.out.println("How much money do you want to exchange?");
                amount = sc.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("You have to enter a valid number...");
            }
            sc.nextLine();

        } while (amount == 0);


        convertedAmount = Double.parseDouble(Converter.ConversionRate(String.valueOf(amount), firstCurrency, secondCurrency));
        System.out.println(String.format("%,.2f", amount) + " " + Converter.getCurrencyNameLong(firstCurrency) + " (" + Converter.getCurrencyNameShort(firstCurrency) + ")" + " equals to " + String.format("%,.2f", convertedAmount) + " " + Converter.getCurrencyNameLong(secondCurrency) + " (" + Converter.getCurrencyNameShort(secondCurrency) + ")" + ".");


    }
}

