package Integration;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  @Mock
  private ResultSet rs;

  private Customer customer;

  @BeforeEach
  public void setUp() throws Exception {

    BufferedImage img = ImageIO.read(new File("src/test/media/customer.jpg"));
    byte[] imgByte = ImageOperations.imageToByteArray(img);

    Address address = new Address("123 S. Washington St.",
        "Fort Myers", "FL", "33904", "USA");

    customer = new Customer("CS001","Firstname", "Lastname",
        "123987321", "123897324", address, "1985-03-12",
        "Male", "012395659376", imgByte);
  }

  @Test
  public void addValidCustomerToDB() throws SQLException {
    CustomerDao cDao = new CustomerDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    cDao.add(customer);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(9)).setString(anyInt(), anyString());
    verify(stmt, times(1)).setBytes(anyInt(), any(byte[].class));
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void updateCustomerInDB() throws SQLException {
    CustomerDao cDao = new CustomerDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    cDao.update(customer);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(9)).setString(anyInt(), anyString());
    verify(stmt, times(1)).setBytes(anyInt(), any(byte[].class));
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  @Test
  public void getCustomerFromDB()
      throws SQLException, InvalidAddressInputException, InvalidCustomerInputException {
    CustomerDao cDao = new CustomerDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeQuery()).thenReturn(rs);
    when(rs.next()).thenReturn(Boolean.TRUE);

    Customer actualCustomer = cDao.get("CS001");
    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(1)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeQuery();
    verify(rs, times(7)).getString(anyString());
    verify(rs, times(1)).getBlob(anyString());
  }
  */

  /*
  @Test
  public void passCustomerToDao() throws SQLException, InvalidCustomerInputException {
    Customer mockCustomer = mock(Customer.class);
    Address mockAddress = mock(Address.class);
    mockCustomer.setAddress(mockAddress);
    CustomerDao cDao = new CustomerDao();
  }
  */
}
