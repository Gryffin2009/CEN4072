package Service;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
  Connection con;

  public UserDao() {
    con = NetworkService.getInstance().getConnection();
  }

  public void add(User user) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO user"
            + "(id,firstname,lastname,username,password)"
            + "values(?,?,?,?,?)");
    pst.setString(1, user.getId());
    pst.setString(2, user.getFirstName());
    pst.setString(3, user.getLastName());
    pst.setString(4, user.getUserName());
    pst.setString(5, user.getPassword());
    pst.executeUpdate();
  }
}
