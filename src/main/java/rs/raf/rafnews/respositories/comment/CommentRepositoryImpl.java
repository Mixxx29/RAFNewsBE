package rs.raf.rafnews.respositories.comment;

import rs.raf.rafnews.entities.Comment;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl extends PostgresGenericRepository<Comment> implements CommentRepository {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getByNews(int newsId) {
        String sql = "SELECT * FROM " + tableName + " WHERE news_id = ?";

        List<Comment> entities = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newsId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
}
