package rs.raf.rafnews.respositories;

import java.sql.*;
import java.util.Optional;

abstract public class PostgresAbstractRepository {

    private final String JDCB_URL = "jdbc:postgresql";
    private final String HOST = "localhost";
    private final int PORT = 5432;
    private final String DATABASE = "raf_news";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    public PostgresAbstractRepository() {
        try {
            // Register the Postgres JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
            JDCB_URL + "://" + HOST + ":" + PORT + "/" + DATABASE,
                USERNAME,
                PASSWORD
        );
    }

    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
