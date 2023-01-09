import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class Converter {

/*
Liefert die gewünschte Umrechnung des eingegebenen Betrages von der inputCurrency in die outputCurrency zurück
 */
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

    static String[] split2;
    static String[] split3;
    static ArrayList<String> countryList = new ArrayList<>();


/*
Fragt Liste von Currencies von API an, wird in einem String gespeichert und gesplitet
Nach Spliten des Stringes werden die gewünschten Werte in eine ArrayList gespeichert
 */
    public static String getCurrencyList() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        String responseString = response.toString();

        //Split by :
        String[] split1 = responseString.split(":", 0);
        String newStringAfterSplit = "";

        for (int i = 0; i < split1.length; i++) {
            //System.out.println("Arr: " + split1 [i]);
            newStringAfterSplit = newStringAfterSplit + split1[i];

        }

        //Split by ","
        split2 = newStringAfterSplit.split(",", 0);
        String newStringAfterSplit2 = "";
        for (int i = 0; i < split2.length; i++) {
            //System.out.println("Arr2: " + split2[i]);
            newStringAfterSplit2 = newStringAfterSplit2 + split2[i];
        }

        //Split by  ""
        split3 = newStringAfterSplit2.split("\"", 0);
        String newStringAfterSplit3 = "";
        for (int i = 0; i < split3.length; i++) {
            //System.out.println("Arr3 an Stelle " + i + " "+ split3[i]);
            split3[i].replaceAll(",", "");
            newStringAfterSplit3 = newStringAfterSplit3 + split3[i];
        }

        //Array List füllen
        for (int i = 0; i < split3.length; i++) {
            countryList.add(split3[i]);
        }
        //Abstände etc entfernen
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).equals("") || countryList.get(i).equals(" ") || countryList.get(i).equals(",") || countryList.get(i).equals(", "))
                countryList.remove(i);
        }

        String currencyString = "";

        System.out.println("Available currencies: ");
        for (int i = 0; i < split2.length; i++) {

            split2[i] = split2[i].replaceFirst("\"", "(");

            split2[i] = split2[i].replaceFirst("\"", ")");

            split2[i] = split2[i].replaceFirst("\"", " ");

            split2[i] = split2[i].replaceFirst("\"", ", ");

            split2[i] = split2[i].replaceFirst("}", "");

            currencyString = currencyString + split2[i];


            System.out.println(split2[i]);

        }
        //System.out.println("List: " + currencyString);
        return "Choose one of the currencies above, please use the short name.";
    }


    /*
    Liefert Kürzel für gewünschte Währung
     */
    public static String getCurrencyNameShort(String currency) {
        String getCurr = "";
        for (int i = 0; i < countryList.size(); i++) {
            if (currency.toUpperCase().equals(countryList.get(i))) {
                getCurr = countryList.get(i);
            }
        }
        return getCurr;
    }


    /*
    Liefert den ausgeschriebenen Namen der Währung zurück
     */
    public static String getCurrencyNameLong(String currency) {
        String getCurr = "";
        for (int i = 0; i < countryList.size(); i++) {
            if (currency.toUpperCase().equals(countryList.get(i))) {
                getCurr = countryList.get(i + 1);
            }
        }
        return getCurr;
    }



    /*
    Methode, welche überprüft ob übergebene Währung in dem Programm verwendet werden kann
    Nachschauen, ob die Währung in der Liste enthalten ist
    Groß- und Kleinschreibung aufgrund des currrency.equalsIgnoreCase - Statements egal
     */
    public static boolean checkCurrency(String currency) {
        for (int i = 0; i < countryList.size(); i++) {

            if (currency.equalsIgnoreCase(countryList.get(i))) {

                return true;

            }
        }
        return false;
    }
}
