package com.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Converter {


    //Takes both currencies and the amount as input and returns the exchanged amount
    public static String Convert(String amount, String inputCurrency, String outputCurrency) throws IOException {

        // Send a request to the Frankfurter API to get the exchange rate
        String urlString = "https://api.frankfurter.app/latest?amount=" + amount + "&from=" + inputCurrency + "&to=" + outputCurrency;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // Read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // Parse the exchange rate from the response
        String responseString = response.toString();
        int rateIndex = responseString.indexOf("rate") + 14;
        int endIndex = responseString.indexOf('}', rateIndex);
        String rate = responseString.substring(rateIndex, endIndex);

        //Returns the exchanged amount
        return rate;
    }


    //Makes an API request to get the names of all available currencies
    public static String apiRequestGetCurrencies() throws IOException {

        // Send a request to the Frankfurter API to get the exchange rate
        String urlString = "https://api.frankfurter.app/currencies";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // Read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        response.delete(0, 1);

        // Parse the exchange rate from the response
        String responseString = response.toString();

        //Retuns the String with the currencies
        return responseString;
    }


    //Makes API Request to get Currencies and returns it as an ArrayList after removing unwanted characters (short and long names)
    public static ArrayList<String> getCurrencyArrayList() throws IOException {
        String responseString = apiRequestGetCurrencies();

        ArrayList<String> arrayList = new ArrayList<>();

        //Split the String by ","
        String[] arrayResponseString = responseString.split(",");

        //Make new ArrayList after split
        String newString = "";
        for (int i = 0; i < arrayResponseString.length; i++) {
            newString = newString + arrayResponseString[i];
        }

        //String split by ""
        String[] newArrString = newString.split("\"");


        //Fill ArrayList with Array
        for (int i = 0; i < newArrString.length; i++) {
            arrayList.add(newArrString[i]);
        }

        //Remove unwanted characters
        for (int i = 0; i < arrayList.size(); i++) {

            if (arrayList.get(i).contains(":") || arrayList.get(i).contains("")) {
                arrayList.remove(i);
            }
        }

        return arrayList;
    }


    //Returns ArrayList with short names of the currencies
    public static ArrayList<String> getCurrencyListShortName() throws IOException {

        ArrayList<String> arrayList = Converter.getCurrencyArrayList();

        for (int i = 1; i <= arrayList.size(); i++) {
            arrayList.remove(i);
        }

        return arrayList;

    }

    //Returns ArrayList with long names of the currencies
    public static ArrayList<String> getCurrencyListLongName() throws IOException {

        ArrayList<String> arrayList = Converter.getCurrencyArrayList();

        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.remove(i);
        }

        return arrayList;

    }


    //Takes a currency as input and returns the short name
    public static String getShortNameOfCurrency(String currency) throws IOException {
        String returnCurrency = "";
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();

        for (int i = 0; i < currencyArrayList.size(); i++) {
            if (currency.equalsIgnoreCase(currencyArrayList.get(i))) {
                returnCurrency = currencyArrayList.get(i - 1);
            }
        }
        return returnCurrency;

    }

    //Takes a currency as input and returns the long name
    public static String getLongNameOfCurrency(String currency) throws IOException {

        String returnCurrency = "";
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();

        for (int i = 0; i < currencyArrayList.size(); i++) {
            if (currency.equalsIgnoreCase(currencyArrayList.get(i))) {
                returnCurrency = currencyArrayList.get(i+1);
            }
        }
        return returnCurrency;

    }

    //Checks if the currency is in the list and returns true if it is and false if it is not
    public static boolean checkIfCurrencyIsInList(String currency) throws IOException {
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();
        for (int i = 0; i < currencyArrayList.size(); i++) {

            if (currency.equalsIgnoreCase(currencyArrayList.get(i))) {

                return true;

            }

        }
        return false;

    }


}
