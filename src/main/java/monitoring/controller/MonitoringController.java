package monitoring.controller;

import lombok.val;
import monitoring.model.MonitoringURL;
import monitoring.service.MonitoringService;
import monitoring.service.factory.ServiceFactory;

import java.util.List;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class MonitoringController {


    public void saveUrl(String url) {
        if ("".equals(url)) {
            return;
        }
        val monitoringService = ServiceFactory.getMonitoringService();
        monitoringService.saveUrl(url);
    }

    public List<MonitoringURL> getUrlInfo() {
        return ServiceFactory.getMonitoringService().getUrlInfo();
    }
}
