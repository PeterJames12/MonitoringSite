package monitoring.dao.impl;

import lombok.SneakyThrows;
import lombok.val;
import monitoring.config.DatabaseConfig;
import monitoring.dao.MonitoringDao;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;
import monitoring.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringDaoImpl implements MonitoringDao {

    private static final String QUERY = "INSERT INTO url (NAME ) VALUES (?)";

    /**
     * {@inheritDoc}.
     */
    @SneakyThrows
    @Override
    public List<MonitoringURL> getUrlInfo() {
        Connection connection = DatabaseConfig.getConnection();
        return null;
    }

    @SneakyThrows
    @Override
    public void saveUrl(Url url) {
        final Connection connection = DatabaseConfig.getConnection();
        val preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, url.getUrl());
        preparedStatement.execute();
    }

    /**
     * @return hibernate session.
     */
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
