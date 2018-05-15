package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnector {

    private String url;
    private String driver;
    private Properties properties;
    private Connection connection;

    public JdbcConnector() {

        try {
            Properties readedProperties = new Properties();
//        FileInputStream in = getClass().getResourceAsStream("/db.properties");
            ClassLoader loader = getClass().getClassLoader();

            FileInputStream in = new FileInputStream(loader.getResource("db.properties").getFile());
            readedProperties.load(in);
            in.close();

            properties = new Properties();
            url = readedProperties.getProperty("jdbc.url");
            driver = readedProperties.getProperty("jdbc.driver");
            properties.setProperty("user", readedProperties.getProperty("jdbc.username"));
            properties.setProperty("password", readedProperties.getProperty("jdbc.password"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {

        try {
            if (url != null && properties != null)
                Class.forName(driver);
                connection = DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null)
            connection.close();
        super.finalize();
    }
}
