package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User jdbcContextForFindById(Long id, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(id, connection);

            resultSet = preparedStatement.executeQuery();

            user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }

    public void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(user, connection);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            try {
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }



    public void jdbcContextForUpdate(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(user, connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void jdbcContextForDelete(Long id, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(id, connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
