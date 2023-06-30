package rs.raf.rafnews.connectors;

import java.sql.*;
import java.util.Optional;

public class PostgresConnector {

    private final String JDCB_URL = "jdbc:postgresql";
    private final String HOST = "localhost";
    private final int PORT = 5432;
    private final String DATABASE = "postgres";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    public PostgresConnector() {
        try {
            // Register the Postgres JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                JDCB_URL + "://" + HOST + ":" + PORT + "/" + DATABASE,
                USERNAME,
                PASSWORD
        );
    }

    public void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
