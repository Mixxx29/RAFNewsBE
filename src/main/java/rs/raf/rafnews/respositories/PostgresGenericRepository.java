package rs.raf.rafnews.respositories;

import rs.raf.rafnews.annotations.*;
import rs.raf.rafnews.connectors.PostgresConnector;
import rs.raf.rafnews.respositories.util.Pageable;
import rs.raf.rafnews.utils.EntityManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresGenericRepository<T> implements GenericRepository<T> {

    protected final Class<T> entityClass;
    protected final String tableName;
    protected PostgresConnector connector;

    public PostgresGenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.connector = new PostgresConnector();
        this.tableName = entityClass.getAnnotation(Entity.class).value();
    }

    @Override
    public List<T> getAll() {
        String sql = "SELECT * FROM " + tableName + " ORDER BY id";
        List<T> entities = new ArrayList<>();

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
    public List<T> getAll(Pageable pageable) {
        final int start = (pageable.getPageIndex() - 1) * pageable.getPageSize();
        final int size = pageable.getPageSize();
        String sql = "SELECT * FROM " + tableName + " ORDER BY id LIMIT " + size + " OFFSET " + start + ";";

        List<T> entities = new ArrayList<>();

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
    public int pageCount(final int pageSize) {
        String sql = "SELECT CEIL(COUNT(*)::FLOAT / " + pageSize + ") AS total_pages FROM " + tableName + ";";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(statement);
            connector.closeResultSet(resultSet);
        }
        return 0;
    }

    @Override
    public T getById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

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

    @Override
    public T create(T entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connector.newConnection();

            String sql = generateInsertQuery();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement(sql, generatedColumns);
            setStatementParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                setID(resultSet.getInt(1), entity);
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(preparedStatement);
            connector.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public T save(T entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connector.newConnection();

            String sql = generateUpdateQuery();
            preparedStatement = connection.prepareStatement(sql);
            setStatementParameters(preparedStatement, entity);
            preparedStatement.setInt(entityClass.getDeclaredFields().length, getID(entity));
            int saved = preparedStatement.executeUpdate();

            if (saved == 1) {
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connector.newConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
            connector.closeStatement(preparedStatement);
        }
    }

    protected T mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();

            Field[] fields = entityClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String columnName =
                        field.isAnnotationPresent(Column.class) ?
                                field.getAnnotation(Column.class).value() : field.getName();

                Object value;
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    GenericRepository<?> repository = EntityManager.getRepository(field.getType());
                    value = repository.getById(getID(entity));
                    System.out.println(value);
                } else if (field.isAnnotationPresent(OneToMany.class)) {
                    continue;
                } else if (field.isAnnotationPresent(ManyToMany.class)) {
                    continue;
                } else {
                    value = resultSet.getObject(columnName);
                    if (field.getType().isEnum()) {
                        Enum<?>[] enumConstants = (Enum<?>[]) field.getType().getEnumConstants();
                        for (Enum<?> enumValue : enumConstants) {
                            if (enumValue.name().equals(value)) {
                                value = enumValue;
                                break;
                            }
                        }
                    }
                }

                field.set(entity, value);
            }

            return entity;
        } catch (Exception e) {
            throw new SQLException("Mapping to map " + entityClass, e);
        }
    }

    protected String generateInsertQuery() {
        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        valuesBuilder.append("VALUES (");

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class)) continue;
            String columnName =
                    field.isAnnotationPresent(Column.class) ?
                            field.getAnnotation(Column.class).value() : field.getName();
            sqlBuilder.append(columnName).append(",");
            valuesBuilder.append("?,");
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1).append(")");
        valuesBuilder.deleteCharAt(valuesBuilder.length() - 1).append(")");
        return sqlBuilder.toString() + valuesBuilder.toString();
    }

    protected String generateUpdateQuery() {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("UPDATE ").append(tableName).append(" SET ");

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class)) continue;
            String columnName =
                    field.isAnnotationPresent(Column.class) ?
                            field.getAnnotation(Column.class).value() : field.getName();
            sqlBuilder.append(columnName).append(" = ?,");
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1).append(" WHERE id = ?");
        return sqlBuilder.toString();
    }

    protected void setStatementParameters(PreparedStatement preparedStatement, T entity) throws SQLException {
        Field[] fields = entityClass.getDeclaredFields();
        int parameterIndex = 1;

        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class)) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    value = getID(value);
                } else if (field.getType().isEnum()) {
                    value = ((Enum<?>) value).name();
                }
                preparedStatement.setObject(parameterIndex++, value);
            } catch (IllegalAccessException e) {
                throw new SQLException("Failed to set statements parameters: " + e);
            }
        }
    }

    protected void setID(int id, Object entity) throws SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ID.class)) continue;
            try {
                field.setAccessible(true);
                field.set(entity, id);
            } catch (IllegalAccessException e) {
                throw new SQLException("Failed to set ID: " + e);
            }
        }
    }

    protected int getID(Object entity) throws SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ID.class)) continue;
            try {
                field.setAccessible(true);
                return (int) field.get(entity);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new SQLException("Failed to get ID: " + e);
            }
        }
        return -1;
    }
}
