package rs.raf.rafnews.respositories.user;

import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepositoryImpl extends PostgresGenericRepository<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM " + tableName + " WHERE email = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(preparedStatement);
            connector.closeResultSet(resultSet);
        }
        return null;
    }
}
