package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "haeeun";
        String password = "1234";
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.findById(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(),is(password));

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "haeeun";
        String password = "1234";

        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), greaterThan(1l));
        assertThat(insertedUser.getPassword(), is(password));
        assertThat(insertedUser.getName(), is(name));
    }

    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "haeeun";
        String password = "1234";

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.findById(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(),is(password));

    }

    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        String name = "haeeun";
        String password = "1234";

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), greaterThan(1l));
        assertThat(insertedUser.getPassword(), is(password));
        assertThat(insertedUser.getName(), is(name));
    }
}
