package monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

/**
 * @author Igor Hnes on 6/24/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    private Long id;
    private String url;
}
