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
import java.time.LocalDate;
import java.util.Collections;
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
        List<MonitoringURL> urlInfo = DaoFactory.getMonitoringDao().getUrlInfo();
        Collections.reverse(urlInfo);
        return urlInfo;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void saveUrl(String url) {
        final Url urlEntity = new Url();
        urlEntity.setUrl(url);
        DaoFactory.getMonitoringDao().saveUrl(urlEntity);
        saveCheckUrl(url);
    }

    /**
     * Check url and save in database parameters.
     */
    @SneakyThrows
    private void saveCheckUrl(String urlName) {
        Properties property = getProperty();
        MonitoringURL monitoringURL = new MonitoringURL();
        if (!urlName.startsWith("https://")) {
            urlName = "https://" + urlName;
        }
        URL url = new URL(urlName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod(property.getProperty("request.method"));
            connection.setConnectTimeout(Integer.parseInt(property.getProperty("connect.timeout")));
            connection.connect();
            int responseCode = connection.getResponseCode();
            monitoringURL.setUrl(url.toString());
            monitoringURL.setStatusCode(responseCode);
            monitoringURL.setStatus(setStatus(responseCode));
            monitoringURL.setExtraInfo(String.valueOf(connection.usingProxy()));
            monitoringURL.setLocalDate(LocalDate.now().toString());
            connection.disconnect();
            DaoFactory.getMonitoringDao().saveMonitoringInfo(monitoringURL);
        } catch (Exception e) {
            connection.disconnect();
        }
    }

    /**
     * Sent status by given status code.
     */
    private String setStatus(int statusCode) {
        if (statusCode >= StatusUrl.CODE_OK && statusCode < StatusUrl.CODE_WARNING) {
            return StatusUrl.OK;
        } else if (statusCode >= StatusUrl.CODE_WARNING && statusCode < StatusUrl.CODE_CRITICAL) {
            return StatusUrl.WARNING;
        } else if (statusCode >= StatusUrl.CODE_CRITICAL) {
            return StatusUrl.CRITICAL;
        }
        return "UNKNOWN";
    }

    /**
     * @return url properties.
     */
    @SneakyThrows
    private Properties getProperty() {
        final Properties properties = new Properties();
        final String path = "src/main/java/monitoring/res/url.properties";
        final FileInputStream file = new FileInputStream(path);
        properties.load(file);
        return properties;
    }
}
