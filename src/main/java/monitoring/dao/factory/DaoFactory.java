package monitoring.dao.factory;

import monitoring.dao.MonitoringDao;
import monitoring.dao.impl.MonitoringDaoImpl;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class DaoFactory {

    /**
     * @return instance of {@link MonitoringDao}.
     */
    public static MonitoringDao getMonitoringDao() {
        return new MonitoringDaoImpl();
    }
}
