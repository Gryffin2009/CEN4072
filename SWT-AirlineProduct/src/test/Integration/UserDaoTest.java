package Integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Model.User;
import Model.User.InvalidUserInputException;
import Service.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  private User user;

  @BeforeEach
  public void setUp() throws Exception {
    user = new User("UO001", "Firstname", "Lastname",
        "username", "Passw0rd");
  }

  @Test
  public void addValidUserToDB() throws SQLException {
    UserDao uDao = new UserDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    uDao.add(user);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(5)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void updateUserInDB() throws SQLException {
    UserDao uDao = new UserDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    uDao.update(user);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(5)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void getUserFromDB() throws SQLException, InvalidUserInputException {
    UserDao userDao = new UserDao();
    User actualUser = userDao.get("UO001");

    Assertions.assertEquals("UO001", actualUser.getId());
    Assertions.assertEquals("john", actualUser.getFirstName());
    Assertions.assertEquals("peter", actualUser.getLastName());
    Assertions.assertEquals("john", actualUser.getUserName());
    Assertions.assertEquals("Passw0rd", actualUser.getPassword());
  }

  @Test
  public void getAllUsersFromDB()
      throws SQLException, InvalidUserInputException {
    UserDao userDao = new UserDao();
    User[] allUsers = userDao.getAll();
    Assertions.assertNotNull(allUsers);
  }
}
