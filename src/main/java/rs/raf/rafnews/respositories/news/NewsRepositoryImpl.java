package rs.raf.rafnews.respositories.news;

import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.respositories.PostgresGenericRepository;
import rs.raf.rafnews.respositories.enums.SortDirection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl extends PostgresGenericRepository<News> implements NewsRepository {
    public NewsRepositoryImpl() {
        super(News.class);
    }

    @Override
    public List<News> getSortedByDate(SortDirection direction) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY datetime " + direction.toString();
        List<News> entities = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(statement);
            connector.closeResultSet(resultSet);
        }

        return entities;
    }

    @Override
    public List<News> getSortedByVisits(SortDirection direction) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY visits " + direction.toString();
        List<News> entities = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(statement);
            connector.closeResultSet(resultSet);
        }

        return entities;
    }

    @Override
    public List<News> getByCategory(int categoryId) {
        String sql = "SELECT * FROM " + tableName + " WHERE category_id = ?";

        List<News> entities = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(preparedStatement);
            connector.closeResultSet(resultSet);
        }
        return entities;
    }

    @Override
    public News getByTitle(String title) {
        String sql = "SELECT * FROM " + tableName + " WHERE title = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);

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
