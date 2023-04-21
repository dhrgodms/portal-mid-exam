package kr.ac.jejunu.user;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
            StatementStrategy statementStrategy = new FindByIdStatementStrategy();
        return jdbcContext.jdbcContextForFindById(id, statementStrategy);
    }



    public void insert(User user) throws ClassNotFoundException, SQLException {
            StatementStrategy statementStrategy = new InsertStatementStrategy();
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }




    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy();
        jdbcContext.jdbcContextForUpdate(user, statementStrategy);
    }



    public void delete(Long id) throws ClassNotFoundException, SQLException {
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
        jdbcContext.jdbcContextForDelete(id, statementStrategy);
    }



}
