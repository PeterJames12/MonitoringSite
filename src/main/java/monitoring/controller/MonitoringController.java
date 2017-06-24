package monitoring.controller;

import lombok.val;
import monitoring.model.MonitoringURL;
import monitoring.service.factory.ServiceFactory;

import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringController {

    /**
     * Save url to database.
     */
    public void saveUrl(String url) {
        val monitoringService = ServiceFactory.getMonitoringService();
        monitoringService.saveUrl(url);
    }

    /**
     * @return list of {@link MonitoringURL}.
     */
    public List<MonitoringURL> getUrlInfo() {
        return ServiceFactory.getMonitoringService().getUrlInfo();
    }
}
