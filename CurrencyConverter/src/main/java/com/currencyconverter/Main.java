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
        ArrayList<String> currencies = Converter.getCurrencyListLongName();
        ArrayList<String> currenciesShortName = Converter.getShortNameOfCurrency();
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to our currency converter");
        System.out.println("Available currencies: ");
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println(currencies.get(i) + " (" + currenciesShortName.get(i) + ")");
        }
        System.out.println("Please use the short name of the currencies.");
        System.out.println("Please choose the first currency: ");
        firstCurrency = sc.next();


        if (Converter.checkIfCurrencyIsInList(firstCurrency) == false) {
            do {
                System.out.println("Wrong currency bro... Choose another one...");
                firstCurrency = sc.next();
            } while (Converter.checkIfCurrencyIsInList(firstCurrency) == false);
        }

        System.out.println("Please choose the second currency: ");
        secondCurrency = sc.next();

        do {

            if (secondCurrency.equalsIgnoreCase(firstCurrency)) {
                System.out.println("The second currency can't be the same as the first currency...");
                secondCurrency = sc.next();
            } else if (Converter.checkIfCurrencyIsInList(secondCurrency) == false) {
                System.out.println("Wrong currency bro... Choose another one...");
                secondCurrency = sc.next();
            }
        } while (secondCurrency.equalsIgnoreCase(firstCurrency) || Converter.checkIfCurrencyIsInList(secondCurrency) == false);

        do {

            try {
                System.out.println("How much bro?");
                amount = sc.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Bro you have to enter a number...");
            }
            sc.nextLine();

        } while (amount == 0);


        convertedAmount = Double.parseDouble(Converter.ConversionRate(String.valueOf(amount), firstCurrency, secondCurrency));
        System.out.println(String.format("%,.2f", amount) + " " + Converter.getLongNameOfCurrency(firstCurrency) + " (" + firstCurrency.toUpperCase() + ")" + " equals to " + String.format("%,.2f", convertedAmount) + " " + Converter.getLongNameOfCurrency(secondCurrency) + " (" + secondCurrency.toUpperCase() + ")" +  ".");


    }
}

    
