package monitoring.service.impl;

import lombok.SneakyThrows;
import monitoring.dao.factory.DaoFactory;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;
import monitoring.service.MonitoringService;
import monitoring.status.StatusUrl;

import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

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

    /**
     * Check url and save in database parameters.
     */
    @SneakyThrows
    private void checkUrl(String urlName) {
        final Properties properties = new Properties();
        final String path = "src/main/java/monitoring/res/url.properties";
        final FileInputStream file = new FileInputStream(path);
        properties.load(file);
        MonitoringURL monitoringURL = new MonitoringURL();
        if (!urlName.startsWith("https://")) {
            urlName = "https://" + urlName;
        }
        URL url = new URL(urlName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod(properties.getProperty("request.method"));
            connection.setConnectTimeout(Integer.parseInt(properties.getProperty("connect.timeout")));
            connection.connect();
            int responseCode = connection.getResponseCode();
            monitoringURL.setUrl(url.toString());
            monitoringURL.setStatusCode(responseCode);
            monitoringURL.setStatus(replacer(responseCode));
            monitoringURL.setExtraInfo(String.valueOf(connection.usingProxy()));
            connection.disconnect();
            System.out.println(responseCode);
            DaoFactory.getMonitoringDao().saveMonitoringInfo(monitoringURL);
        } catch (Exception e) {
            connection.disconnect();
        }
    }

    private String replacer(int statusCode) {

        if (statusCode >= 200 && statusCode < 300) {
            return StatusUrl.OK;
        } else if (statusCode >= 300 && statusCode < 400) {
            return StatusUrl.WARNING;
        } else if (statusCode >= 400) {
            return StatusUrl.CRITICAL;
        }
        return "UNKNOWN";
    }
}
