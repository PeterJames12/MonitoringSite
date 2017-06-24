package monitoring.config;

import lombok.SneakyThrows;

import java.sql.*;

/**
 * @author Igor Hnes on 6/24/17.
 */
public class DatabaseConfig {

    private static final String URL = "jdbc:postgresql://ec2-23-23-93-255.compute-1.amazonaws.com:5432/d5t06k85ohsutd?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USERNAME = "zqrtvxhfqmdjft";
    private static final String PASS = "5946f5d3a5434dc18a5fd32dc6759c9b62b37cb0e687cb7e1f80b27dbd09c7ba";

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USERNAME, PASS);
    }
}
