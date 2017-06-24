package monitoring.service.factory;

import monitoring.service.MonitoringService;
import monitoring.service.impl.MonitoringServiceImpl;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class ServiceFactory {

    /**
     * @return instance of {@link MonitoringService}.
     */
    public static MonitoringService getMonitoringService() {
        return new MonitoringServiceImpl();
    }
}
