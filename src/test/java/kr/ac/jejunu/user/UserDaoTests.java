package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private static UserDao userDao;
    String name = "haeeun";
    String password = "1234";

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;

        User user = userDao.findById(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(),is(password));

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {

        User user = insertedUser();

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getPassword(), is(password));
        assertThat(insertedUser.getName(), is(name));
    }


    @Test
    public void update() throws SQLException, ClassNotFoundException {


        User user = insertedUser();


        String updatedName = "updatedhaeeun";
        String updatedPassword = "updated1234";
        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getPassword(), is(updatedPassword));
        assertThat(updatedUser.getName(), is(updatedName));
    }



    @Test
    public void delete() throws SQLException, ClassNotFoundException {

        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }

    private static User insertedUser() throws ClassNotFoundException, SQLException {
        String name = "haeeun";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        return user;
    }
}
