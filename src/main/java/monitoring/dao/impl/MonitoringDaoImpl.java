package monitoring.dao.impl;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import monitoring.config.DatabaseConfig;
import monitoring.dao.MonitoringDao;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringDaoImpl implements MonitoringDao {

    private static final String QUERY_INSERT_URL = "INSERT INTO url (NAME ) VALUES (?)";
    private static final String QUERY_INSERT_MONITORING_URL = "INSERT INTO monitoring_info (url,status,status_code,extra_info) VALUES (?,?,?,?)";
    private static final String QUERY_SELECT = "SELECT * FROM monitoring_info";
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;

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

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public void saveUrl(Url url) {
        @Cleanup
        final Connection connection = DatabaseConfig.getConnection();
        val preparedStatement = connection.prepareStatement(QUERY_INSERT_URL);
        preparedStatement.setString(FIRST, url.getUrl());
        preparedStatement.execute();
    }

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public void saveMonitoringInfo(MonitoringURL monitoringURL) {
        @Cleanup
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_MONITORING_URL);
        preparedStatement.setString(FIRST, monitoringURL.getUrl());
        preparedStatement.setString(SECOND, monitoringURL.getStatus());
        preparedStatement.setInt(THIRD, monitoringURL.getStatusCode());
        preparedStatement.setString(FOURTH, monitoringURL.getExtraInfo());
        preparedStatement.execute();
    }
}