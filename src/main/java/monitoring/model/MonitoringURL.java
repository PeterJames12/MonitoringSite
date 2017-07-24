package monitoring.model;

import lombok.*;

/**
 * @author Igor Hnes on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringURL {

    private Long id;
    private String url;
    private String status;
    private int statusCode;
    private String extraInfo;
    private String localDate;

}
