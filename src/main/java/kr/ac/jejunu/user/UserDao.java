package kr.ac.jejunu.user;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new FindByIdStatementStrategy();
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


    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new InsertStatementStrategy();
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


    public void update(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

        StatementStrategy statementStrategy = new UpdateStatementStrategy();
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

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy();

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
