package monitoring.service.impl;

import monitoring.dao.MonitoringDao;
import monitoring.dao.factory.DaoFactory;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;
import monitoring.service.MonitoringService;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringServiceImpl implements MonitoringService {

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<MonitoringURL> getUrlInfo() {
        return DaoFactory.getMonitoringDao().getUrlInfo();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void saveUrl(String url) {
        final Url urlEntity = new Url();
        urlEntity.setUrl(url);
        DaoFactory.getMonitoringDao().saveUrl(urlEntity);
        checkUrl(url);
    }


    public static void main(String[] args) {
        new MonitoringServiceImpl().checkUrl("github.com");

    }

    private void checkUrl(String urlName) {
//        boolean available;
//        try {
//            final URLConnection connection = new URL("http://" + url).openConnection();
//            connection.connect();
//            System.out.println("monitoring.Service " + url + " available, yeah!");
//            available = true;
//        } catch (final MalformedURLException e) {
//            throw new IllegalStateException("Bad URL: " + url, e);
//        } catch (final IOException e) {
//            System.out.println("monitoring.Service " + url + " unavailable, oh no!");
//            available = false;
//        }
//        System.out.println(available);

        MonitoringURL monitoringURL = new MonitoringURL();

        try {
            URL url = new URL("https://" + urlName); // create url object for the given string
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(50000); //set the timeout
            connection.connect(); //connect
            String responseMessage = connection.getResponseMessage(); //here you get the response message
            int responseCode = connection.getResponseCode(); //this is http response code

//            System.out.println(url + " is up. Response Code: " + responseMessage);
            monitoringURL.setUrl(url.toString());
            monitoringURL.setStatusCode(responseCode);
            monitoringURL.setStatus(responseMessage);
            monitoringURL.setExtraInfo(String.valueOf(connection.usingProxy()));
//            System.out.println(responseCode);
            connection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        MonitoringDao monitoringDao = DaoFactory.getMonitoringDao();
        monitoringDao.saveMonitoringInfo(monitoringURL);
    }
}
