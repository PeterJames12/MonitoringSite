package monitoring;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;

/**
 * @author Igor Hnes on 6/20/17.
 */
public class Service {

    public static void main(String[] args) throws IOException {

        boolean available;

        String url = "http://stackoverflow.com/";

        try {
            final URLConnection connection = new URL(url).openConnection();
            connection.connect();
            System.out.println("monitoring.Service " + url + " available, yeah!");
            available = true;
        } catch (final MalformedURLException e) {
            throw new IllegalStateException("Bad URL: " + url, e);
        } catch (final IOException e) {
            System.out.println("monitoring.Service " + url + " unavailable, oh no!");
            available = false;
        }

        System.out.println(available);

//        pingHost();
    }

    public  void pingHost() {
        try {

           URL url = new URL("https://github.com/PeterJames121212123"); // create url object for the given string
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(url.toString().startsWith("https")){
                connection = (HttpsURLConnection) url.openConnection();
            }

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(50000); //set the timeout
            connection.connect(); //connect
            String responseMessage = connection.getResponseMessage(); //here you get the response message
            int responseCode = connection.getResponseCode(); //this is http response code
            System.out.println(url + " is up. Response Code: " + responseMessage);
            System.out.println(responseCode);
            connection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
