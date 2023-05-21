package rs.raf.rafnews.respositories.user;

import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.entities.utils.UserStatus;
import rs.raf.rafnews.entities.utils.UserType;
import rs.raf.rafnews.respositories.PostgresAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPostgresRepository extends PostgresAbstractRepository implements UserRepository {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = newConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("username"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                UserType.values()[resultSet.getInt("type")],
                                UserStatus.values()[resultSet.getInt("status")],
                                resultSet.getString("password")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }

        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            UserType.values()[resultSet.getInt("type")],
                            UserStatus.values()[resultSet.getInt("status")],
                            resultSet.getString("password")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public User create(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (email, username, name, surname, type, status, password) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    generatedColumns
            );
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setInt(5, user.getType().ordinal());
            preparedStatement.setInt(6, user.getStatus().ordinal());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public User save(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET " +
                            "email = ?, " +
                            "username  = ?, " +
                            "name  = ?, " +
                            "surname  = ?, " +
                            "type  = ?, " +
                            "status  = ?, " +
                            "password  = ? " +
                            "WHERE id = ?"
            );
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setInt(5, user.getType().ordinal());
            preparedStatement.setInt(6, user.getStatus().ordinal());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
        }

        return user;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
        }
    }
}
