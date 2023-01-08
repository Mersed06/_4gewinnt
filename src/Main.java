import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        String toCurrency = "GBP";
        System.out.println("Exchangerate from EUR to " + toCurrency + " = " + Api.Api(toCurrency));
        System.out.println(Api.Api(toCurrency));
    }
}