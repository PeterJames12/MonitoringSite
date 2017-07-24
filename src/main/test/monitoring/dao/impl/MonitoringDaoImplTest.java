package monitoring.dao.impl;


import monitoring.dao.MonitoringDao;
import monitoring.dao.factory.DaoFactory;
import monitoring.model.MonitoringURL;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Igor Hnes on 6/27/17.
 */
public class MonitoringDaoImplTest {

    private MonitoringURL url;
    private MonitoringDao monitoringDao;


    @Before
    public void setUp() throws Exception {
        url = new MonitoringURL();
        monitoringDao = DaoFactory.getMonitoringDao();
    }

    @Test
    public void saveUrl() throws Exception {


    }
}