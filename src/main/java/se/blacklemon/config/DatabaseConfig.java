package se.blacklemon.config;


import java.io.IOException;
import java.sql.*;

public class DatabaseConfig {
    private static final String PROPERTIES_FILE = "application.properties";
    private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static final ResultSet resultSet = null;
    private static final PropertiesConfig properties = new PropertiesConfig();



    static {
        try {

            String databaseName = properties.getProperty("databasename", PROPERTIES_FILE);
            String rootUrl = properties.getProperty("db-url", PROPERTIES_FILE);
            String dbPassword = properties.getProperty("db-password", PROPERTIES_FILE);
            String dbUser = properties.getProperty("db-user", PROPERTIES_FILE);
            
            String url = String.format("%s databaseName=%s;user=%s;password=%s", rootUrl, databaseName, dbUser, dbPassword);

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",dbUser,dbPassword);
            System.out.printf("Connected to %s database!%n", databaseName);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAutoCommit(boolean state) throws SQLException {
        connection.setAutoCommit(state);

    }

    public static void commit() throws SQLException {
        connection.commit();
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }

    public static PreparedStatement prepareStatement(String statement) throws SQLException {
        prepareStatement = connection.prepareStatement(statement);
        return prepareStatement;
    }

    public static ResultSet executeQuery() throws SQLException {
        return prepareStatement.executeQuery();
    }

    public static void executeUpdate() throws SQLException {
        prepareStatement.executeUpdate();
    }
}
