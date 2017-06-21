package entity;

import java.time.LocalDate;

/**
 * @author Igor Hnes on 6/21/17.
 */
public class MonitoringURL {

    private Long id;
    private String url;
    private String status;
    private int statusCode;
    private String somethingElse;

    public MonitoringURL() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getSomethingElse() {
        return somethingElse;
    }

    public void setSomethingElse(String somethingElse) {
        this.somethingElse = somethingElse;
    }
}
