package monitoring.dao.impl;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import monitoring.config.DatabaseConfig;
import monitoring.dao.MonitoringDao;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringDaoImpl implements MonitoringDao {

    private static final String QUERY_INSERT_URL = "INSERT INTO url (NAME ) VALUES (?)";
    private static final String QUERY_INSERT_MONITORING_URL = "INSERT INTO monitoring_info (NAME ) VALUES (?)";
    private static final String QUERY_SELECT = "SELECT * FROM monitoring_info";

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public List<MonitoringURL> getUrlInfo() {
        @Cleanup
        Connection connection = DatabaseConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_SELECT);

        List<MonitoringURL> monitoringURLList = new LinkedList<>();

        while (resultSet.next()) {
            MonitoringURL monitoringURL = new MonitoringURL();
            monitoringURL.setUrl(resultSet.getString("url"));
            monitoringURL.setStatus(resultSet.getString("status"));
            monitoringURL.setStatusCode(resultSet.getInt("status_code"));
            monitoringURL.setExtraInfo(resultSet.getString("extra_info"));
            monitoringURLList.add(monitoringURL);
        }

        return monitoringURLList;
    }

    @SneakyThrows
    @Override
    public void saveUrl(Url url) {
        @Cleanup
        final Connection connection = DatabaseConfig.getConnection();
        val preparedStatement = connection.prepareStatement(QUERY_INSERT_URL);
        preparedStatement.setString(1, url.getUrl());
        preparedStatement.execute();
    }

    @SneakyThrows
    @Override
    public void saveMonitoringInfo(MonitoringURL monitoringURL) {
        @Cleanup
        Connection connection = DatabaseConfig.getConnection();
        Statement statement = connection.prepareStatement(QUERY_INSERT_MONITORING_URL);
    }
}