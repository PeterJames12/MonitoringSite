package monitoring.service;

import monitoring.model.MonitoringURL;

import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public interface MonitoringService {

    /**
     * @return list of {@link MonitoringURL} entity.
     */
    List<MonitoringURL> getUrlInfo();

    /**
     * Save new url in database with given url.
     *
     * @param url is given url.
     */
    void saveUrl(String url);

    /**
     * Delete urk with given id.
     */
    void deleteUrl(int id);
}
