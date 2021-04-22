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

  /*
  Test Case ID: #DB-11-1
  Requirement ID/Description: DB-11, The software shall store users in a database.
  Purpose: Verify that the UserDao class creates a PreparedStatement that the database will
    accept as a valid new row to the user table.
  Test Setup: Create a stub User object.
  Test Strategy: Pass stub User object to a mock database connection and determine what database
    functions are called via a prepared statement that adds a new user to the database.
  Input: Mock Database Connection, UserDao class, User class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void addValidUserToDB() throws SQLException {
    UserDao uDao = new UserDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    uDao.add(user);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(5)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-12-1
  Requirement ID/Description: DB-12, The software shall update users in a database.
  Purpose: Verify that the UserDao class creates a PreparedStatement that the database will
    accept as a modification to an existing row in the user table.
  Test Setup: Create a stub User object.
  Test Strategy: Pass stub User object to a mock database connection and determine what database
    functions are called via a prepared statement that updates an existing user in the database.
  Input: Mock Database Connection, UserDao class, User class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void updateUserInDB() throws SQLException {
    UserDao uDao = new UserDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    uDao.update(user);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(5)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-10-1
  Requirement ID/Description: DB-10, The software shall read users in a database.
  Purpose: Verify that the UserDao class successfully retrieves user information from a
    database and creates a User object from this information.
  Test Setup: Create UserDao for processing database connection.
  Test Strategy: Query database for a specific user and determine if that user was returned.
  Input: UserDao class, User class. Retrieves user UO001 in the actual database.
  Expected Output: The user UO001 is retrieved from the database and created into
    a User object.
   */
  @Test
  public void getUserFromDB() throws SQLException, InvalidUserInputException {
    UserDao userDao = new UserDao();
    User actualUser = userDao.get("UO001");

    Assertions.assertEquals("UO001", actualUser.getId());
    Assertions.assertEquals("john", actualUser.getFirstName());
    Assertions.assertEquals("peter", actualUser.getLastName());
    Assertions.assertEquals("john", actualUser.getUserName());
    Assertions.assertEquals("Passw0rd", actualUser.getPassword());

    System.out.println("User #" + actualUser.getId() + ", " + actualUser.getFirstName() + " " +
        actualUser.getLastName());
  }

  /*
  Test Case ID: #DB-10-2
  Requirement ID/Description: DB-10, The software shall read users in a database.
  Purpose: Verify that the UserDao class successfully retrieves user information for all
    users from a database and creates an array of User objects from this information.
  Test Setup: Create UserDao for processing database connection.
  Test Strategy: Query database for all users and determine if every user was returned.
  Input: UserDao class, User class. Retrieves an array of all users in the actual database.
  Expected Output: An array of Users containing every user and all their information.
   */
  @Test
  public void getAllUsersFromDB()
      throws SQLException, InvalidUserInputException {
    UserDao userDao = new UserDao();
    User[] allUsers = userDao.getAll();
    Assertions.assertNotNull(allUsers);

    for (User user : allUsers) {
      System.out.println("User #" + user.getId() + ", " + user.getFirstName() + " " +
          user.getLastName());
    }
  }
}
