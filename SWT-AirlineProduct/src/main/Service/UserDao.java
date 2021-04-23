package Service;

import Model.User;
import Model.User.InvalidUserInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
  Connection con;

  public UserDao(Connection con) {
    this.con = con;
  }
  public UserDao() {
    con = NetworkService.getInstance().getConnection();
  }

  /**
   * Adds a new user to the database from a User object.
   * @param user
   * @throws SQLException
   */
  public void add(User user) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO user"
            + "(id,firstname,lastname,username,password)"
            + "values(?,?,?,?,?)");
    AddUserFromPreparedStatement(pst, user);
  }

  /**
   * Updates an existing user in the database from a User object.
   * @param user
   * @throws SQLException
   */
  public void update(User user) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "UPDATE user SET" + "id=?," + "firstname=?," + "lastname=?," + "username=?,"
            + "password=?,");
    AddUserFromPreparedStatement(pst, user);
  }

  /**
   * Companion method that takes a prepared statement and processes it with the database.
   * @param pst
   * @param user
   * @throws SQLException
   */
  public void AddUserFromPreparedStatement(PreparedStatement pst, User user)
      throws SQLException {

    pst.setString(1, user.getId());
    pst.setString(2, user.getFirstName());
    pst.setString(3, user.getLastName());
    pst.setString(4, user.getUserName());
    pst.setString(5, user.getPassword());
    pst.executeUpdate();
  }

  /**
   * Retrieves user information from the database using a user ID.
   * @param id
   * @return
   * @throws SQLException
   * @throws InvalidUserInputException
   */
  public User get(String id) throws SQLException, InvalidUserInputException {

    PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE id = ?");
    pst.setString(1, id);
    ResultSet rs = pst.executeQuery();
    rs.next();

    return GetUserFromResultSet(rs);
  }

  /**
   * Retrieves every user from the database and returns it as an array of User objects.
   * @return an array of User objects.
   * @throws SQLException
   * @throws InvalidUserInputException
   */
  public User[] getAll() throws SQLException, InvalidUserInputException {
    PreparedStatement pst = con.prepareStatement("SELECT count(*) AS rowCount FROM user");
    ResultSet rs = pst.executeQuery();
    rs.next();
    int userCount = rs.getInt("rowCount");
    rs.close();
    User[] users = new User[userCount];

    pst = con.prepareStatement("SELECT * from user");
    rs = pst.executeQuery();

    int i = 0;
    while (rs.next()) {
      users[i] = GetUserFromResultSet(rs);
      i++;
    }
    return users;
  }

  /**
   * A companion module that processes a ResultSet of user information and returns it as a
   * User object.
   * @param rs
   * @return
   * @throws SQLException
   * @throws InvalidUserInputException
   */
  public User GetUserFromResultSet(ResultSet rs) throws SQLException, InvalidUserInputException {

    String id = rs.getString("id");
    String firstName = rs.getString("firstname");
    String lastName = rs.getString("lastname");
    String username = rs.getString("username");
    String password = rs.getString("password");

    User user = new User(id, firstName, lastName, username, password);
    return user;
  }
}
