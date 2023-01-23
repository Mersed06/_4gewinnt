package com.currencyconverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        double amount = 0;
        double convertedAmount;
        String firstCurrency;
        String secondCurrency;


        ArrayList<String> currencies = Converter.getCurrencyListLongName(); //ArrayList for long name currencies
        ArrayList<String> currenciesShortName = Converter.getCurrencyListShortName(); //ArrayList for short name currencies
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to our currency converter :)");
        System.out.println("");
        System.out.println("Available currencies: ");
        System.out.println("");
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println(currencies.get(i) + " (" + currenciesShortName.get(i) + ")"); //Print all currencies
        }
        System.out.println("");
        System.out.println("Please use the short name of the currencies!");
        System.out.println("");
        System.out.println("Please choose the first currency: ");
        firstCurrency = sc.next();


        if (Converter.checkIfCurrencyIsInList(firstCurrency) == false) { //Check if the input is valid
            do {
                System.out.println("Please choose a valid currency..."); //If not (false), ask again
                firstCurrency = sc.next();
            } while (Converter.checkIfCurrencyIsInList(firstCurrency) == false);
        }

        System.out.println("Please choose the second currency: ");
        secondCurrency = sc.next();

        do {

            if (secondCurrency.equalsIgnoreCase(firstCurrency)) { //Check if the same currency was chosen again
                System.out.println("The second currency can't be the same as the first currency...");
                secondCurrency = sc.next();
            } else if (Converter.checkIfCurrencyIsInList(secondCurrency) == false) { //Check if the input is valid
                System.out.println("Please choose a valid currency..."); //If not (false), ask again
                secondCurrency = sc.next();
            }
        } while (secondCurrency.equalsIgnoreCase(firstCurrency) || Converter.checkIfCurrencyIsInList(secondCurrency) == false); //Do it as long as the input is "wrong"

        do {

            try {
                System.out.println("How much?");
                amount = sc.nextDouble();
            } catch (InputMismatchException ex) { //If the input is not a number
                System.out.println("You have to enter a valid number...");
            }
            sc.nextLine();

        } while (amount == 0);

        //Convert the amount and print it on the console with the name of the currency and the short name
        convertedAmount = Double.parseDouble(Converter.Convert(String.valueOf(amount), firstCurrency, secondCurrency));
        System.out.println(String.format("%,.2f", amount) + " " + Converter.getLongNameOfCurrency(firstCurrency) + " (" + firstCurrency.toUpperCase() + ")" + " equals to " + String.format("%,.2f", convertedAmount) + " " + Converter.getLongNameOfCurrency(secondCurrency) + " (" + secondCurrency.toUpperCase() + ")" +  ".");


    }
}

    
