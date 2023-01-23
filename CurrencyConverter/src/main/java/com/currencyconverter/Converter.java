package com.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Converter {


    public static String ConversionRate(String amount, String inputCurrency, String outputCurrency) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

        return rate;
    }


    public static String apiRequestGetCurrencies() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        return responseString;
    }

    //Makes API Request to get Currencies and returns it as an ArrayList
    public static ArrayList<String> getCurrencyArrayList() throws IOException {
        ArrayList<String> currencyArrayList = Converter.makeArrayList(Converter.apiRequestGetCurrencies());
        return currencyArrayList;
    }


    public static ArrayList<String> getShortNameOfCurrency() throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> array = Converter.makeArrayList(Converter.apiRequestGetCurrencies());

        for (int i = 0; i < array.size(); i++) {
            arrayList.add(i, array.get(i));
        }

        for (int i = 1; i <= arrayList.size(); i++) {
            arrayList.remove(i);
        }

        return arrayList;

    }

    //Gets ArrayList with currencies and deletes the short names
    public static ArrayList<String> getCurrencyListLongName() throws IOException { //Returns currency name


        ArrayList<String> arrayList = Converter.getCurrencyArrayList();


        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.remove(i);
        }

        return arrayList;

    }

    //Gets ArrayList with currencies and deletes the long names (Not used in program)
   /* public static ArrayList<String> getCurrencyListShortName() throws IOException { //Returns currency abbreviation


        ArrayList<String> arrayList = Converter.getCurrencyArrayList();


        for (int i = 1; i < arrayList.size(); i++) {
            arrayList.remove(i);
        }

        return arrayList;

    }*/



    public static String getShortNameOfCurrency(String currency) throws IOException { //Takes a currency as input and retuns short name
        String currencyToBeChecked = currency;
        String currencyToBeReturned = "";
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();

        for (int i = 0; i < currencyArrayList.size(); i++) {
            if (currencyToBeChecked.toUpperCase().equals(currencyArrayList.get(i).toUpperCase())) {
                currencyToBeReturned = currencyArrayList.get(i - 1);
            }
        }
        return currencyToBeReturned;

    }

    public static String getLongNameOfCurrency(String currency) throws IOException { //Takes a currency as input and retuns long name
        String currencyToBeChecked = currency;
        String currencyToBeReturned = "";
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();

        for (int i = 0; i < currencyArrayList.size(); i++) {
            if (currencyToBeChecked.toUpperCase().equals(currencyArrayList.get(i).toUpperCase())) {
                currencyToBeReturned = currencyArrayList.get(i+1);
            }
        }
        return currencyToBeReturned;

    }

    public static boolean checkIfCurrencyIsInList(String currency) throws IOException {
        ArrayList<String> currencyArrayList = Converter.getCurrencyArrayList();
        for (int i = 0; i < currencyArrayList.size(); i++) {

            if (currency.equalsIgnoreCase(currencyArrayList.get(i))) {

                return true;

            }

        }
        return false;

    }




    public static ArrayList<String> makeArrayList(String responseString) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayListShortNames = new ArrayList<>();


        String[] arrOfString = responseString.split(",");


        //Neuen Array nach Split erstellen
        String newString = "";
        for (int i = 0; i < arrOfString.length; i++) {
            newString = newString + arrOfString[i];
        }

        //String split by :
        String[] newArrString = newString.split(":");


        //String split by ""
        String[] newArrString2 = newString.split("\"");


        //ArrayList nach Split in ArrayList füllen
        for (int i = 0; i < newArrString2.length; i++) {
            arrayList.add(newArrString2[i]);
        }

        //ArrayList einträge entfernen, die man nicht braucht
        for (int i = 0; i < arrayList.size(); i++) {

            if (arrayList.get(i).contains(":") || arrayList.get(i).contains("")) {
                arrayList.remove(i);
            }
        }

        //2. Array List mit Werten der ersten befüllen
        for (int i = 0; i < arrayList.size(); i++) {
            arrayListShortNames.add(i, arrayList.get(i));
        }

        return arrayList;
    }


}
