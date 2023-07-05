package rs.raf.rafnews.respositories.category;

import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryRepositoryImpl extends PostgresGenericRepository<Category> implements CategoryRepository {
    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Category getByName(String name) {
        String sql = "SELECT * FROM " + tableName + " WHERE name = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

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
