package monitoring.service.impl;

import monitoring.dao.factory.DaoFactory;
import monitoring.model.MonitoringURL;
import monitoring.model.Url;
import monitoring.service.MonitoringService;

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
        final Url urlEntiry = new Url();
        urlEntiry.setUrl(url);
        DaoFactory.getMonitoringDao().saveUrl(urlEntiry);
    }
}
