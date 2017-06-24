package monitoring.config;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class DatabaseConfig {

    private static final String URL = "jdbc:postgresql://ec2-54-75-229-201.eu-west-1.compute.amazonaws.com:5432/d8h0drjb9eoidt?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USERNAME = "cmienhftbssytm";
    private static final String PASS = "3d33063c06d2d1a20bec8b1c099836b8245bbbdb9f9e2587cacb3802e699046e";

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USERNAME, PASS);
    }
}
