import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Model.Address;
import Model.Customer;
import Service.CustomerDao;
import Service.ImageOperations;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.DataSource;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

public class IntegrationsTest {

  @Mock
  private DataSource ds;

  @Mock
  private Connection c;

  @Mock
  private PreparedStatement stmt;

  @Mock
  private ResultSet rs;

  private Customer customer;

  @BeforeAll
  public void setup() throws Exception {
    assertNotNull(ds);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(ds.getConnection()).thenReturn(c);

    BufferedImage img = ImageIO.read(new File("/resources/customer.jpg"));
    byte[] imgByte = ImageOperations.imageToByteArray(img);

    Address address = new Address("123 S. Washington St.",
        "Fort Myers", "FL", "33904", "USA");

    customer = new Customer("CS001","Firstname", "Lastname",
        "123987321", "123897324", address, "03-12-1985",
        "Male", "012395659376", imgByte);

    when(rs.first()).thenReturn(true);
    when(rs.getString(1)).thenReturn(customer.getId());
    when(rs.getString(2)).thenReturn(customer.getFirstname());
    when(rs.getString(3)).thenReturn(customer.getLastname());
    when(rs.getString(4)).thenReturn(customer.getNic());
    when(rs.getString(5)).thenReturn(customer.getPassport());
    when(rs.getString(7)).thenReturn(customer.getAddressAsString());
    when(rs.getString(8)).thenReturn(customer.getDob());
    when(rs.getString(9)).thenReturn(customer.getGender());
    when(rs.getString(10)).thenReturn(customer.getPhoneNumber());
    when(rs.getBytes(6)).thenReturn(customer.getPhoto());
    when(stmt.executeQuery()).thenReturn(rs);
  }

  @Test
  public void CustomerDaoTest() throws SQLException {
    new CustomerDao().add(customer);
  }
}
