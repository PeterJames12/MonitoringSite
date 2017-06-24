package monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

/**
 * @author Igor Hnes on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.DIRTY, dynamicUpdate = true)
@Table(name = "monitoring_url")
public class MonitoringURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "status")
    private String status;
    @Column(name = "status_code")
    private int statusCode;
    @Column(name = "extra_info")
    private String extraInfo;
}
