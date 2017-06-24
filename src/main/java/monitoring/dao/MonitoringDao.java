package monitoring.dao;

import monitoring.model.MonitoringURL;
import monitoring.model.Url;

import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public interface MonitoringDao {

    /**
     * @return list of {@link MonitoringURL}
     */
    List<MonitoringURL> getUrlInfo();

    /**
     * Save new url in database with given url.
     *
     * @param url is given entity with set url.
     */
    void saveUrl(Url url);

    /**
     * Create MonitoringUrl entity.
     *
     * @param monitoringURL is given entity.
     */
    void saveMonitoringInfo(MonitoringURL monitoringURL);
}
