package monitoring.dao.impl;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import monitoring.config.DatabaseConfig;
import monitoring.dao.MonitoringDao;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringDaoImpl implements MonitoringDao {

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public List<MonitoringURL> getUrlInfo() {
        Properties property = getProperty();
        @Cleanup
        Connection connection = DatabaseConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(property.getProperty("select.monitoring.url"));

        List<MonitoringURL> monitoringURLList = new LinkedList<>();

        while (resultSet.next()) {
            MonitoringURL monitoringURL = new MonitoringURL();
            monitoringURL.setUrl(resultSet.getString("url"));
            monitoringURL.setStatus(resultSet.getString("status"));
            monitoringURL.setStatusCode(resultSet.getInt("status_code"));
            monitoringURL.setExtraInfo(resultSet.getString("extra_info"));
            monitoringURL.setLocalDate(resultSet.getString("date"));
            monitoringURLList.add(monitoringURL);
        }
        return monitoringURLList;
    }

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public void saveUrl(Url url) {
        Properties property = getProperty();
        @Cleanup
        final Connection connection = DatabaseConfig.getConnection();
        val preparedStatement = connection.prepareStatement(property.getProperty("insert.url"));
        preparedStatement.setString(Integer.parseInt(property.getProperty("first.column")), url.getUrl());
        preparedStatement.execute();
    }

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public void saveMonitoringInfo(MonitoringURL monitoringURL) {
        Properties property = getProperty();
        @Cleanup
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(property.getProperty("insert.monitoring.url"));
        preparedStatement.setString(Integer.parseInt(property.getProperty("first.column")), monitoringURL.getUrl());
        preparedStatement.setString(Integer.parseInt(property.getProperty("second.column")), monitoringURL.getStatus());
        preparedStatement.setInt(Integer.parseInt(property.getProperty("third.column")), monitoringURL.getStatusCode());
        preparedStatement.setString(Integer.parseInt(property.getProperty("fourth.column")), monitoringURL.getExtraInfo());
        preparedStatement.setString(Integer.parseInt(property.getProperty("fifth.column")), monitoringURL.getLocalDate().toString());
        preparedStatement.execute();
    }

    /**
     * @return query properties.
     */
    @SneakyThrows
    private Properties getProperty() {
        final Properties properties = new Properties();
        final String path = "src/main/java/monitoring/res/query.properties";
        final FileInputStream file = new FileInputStream(path);
        properties.load(file);
        return properties;
    }
}