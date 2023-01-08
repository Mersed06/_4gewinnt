import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class ConversionRate {


    public static String ConversionRate(String amount, String inputCurrency, String outputCurrency) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Send a request to the Frankfurter API to get the exchange rate
        String urlString = "https://api.frankfurter.app/latest?amount=" + amount + "&from=" + inputCurrency+ "&to=" + outputCurrency;


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


    public static String currencyName() throws IOException {
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
        // Parse the exchange rate from the response
        String responseString = response.toString();
        int rateIndex = responseString.indexOf("rate") + 14;
        int endIndex = responseString.indexOf('}', rateIndex);
        String rate = responseString.substring(rateIndex, endIndex);

        return rate;
    }
}
