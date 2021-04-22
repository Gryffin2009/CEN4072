package Integration;

import static org.mockito.Mockito.*;

import Model.Address;
import Model.Address.InvalidAddressInputException;
import Model.Customer;
import Model.Customer.InvalidCustomerInputException;
import Service.CustomerDao;
import Service.ImageOperations;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  private Customer customer;

  /*
    imgByte: A stub picture to be used as a stand-in for an actual customer photo.
    address: A stub Address used in the creation of a Customer.
    customer: A stub Customer for mocking a database insert.
   */
  @BeforeEach
  void setUp() throws Exception {

    BufferedImage img = ImageIO.read(new File("src/test/media/customer.jpg"));
    byte[] imgByte = ImageOperations.imageToByteArray(img);

    // Address stub to simulate a real address.
    Address address = new Address("123 S. Washington St.",
        "Fort Myers", "FL", "33904", "USA");

    // Customer stub to simulate a real customer.
    customer = new Customer("CS001","Firstname", "Lastname",
        "123987321", "123897324", address, "1985-03-12",
        "Male", "012395659376", imgByte);
  }

  /*
  Test Case ID: #DB-2-1
  Requirement ID/Description: DB-2, The software shall store customers in a database.
  Purpose: Verify that the CustomerDao class creates a PreparedStatement that the database will
    accept as a valid new row to the customer table.
  Test Setup: Create a stub Address which is passed to a stub Customer object.
  Test Strategy: Pass stub Customer object to a mock database connection and determine what database
    functions are called via a prepared statement that adds a new customer to the database.
  Input: Mock Database Connection, CustomerDao class, Customer class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  void addValidCustomerToDB() throws SQLException {
    CustomerDao cDao = new CustomerDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    cDao.add(customer);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(9)).setString(anyInt(), anyString());
    verify(stmt, times(1)).setBytes(anyInt(), any(byte[].class));
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-3-1
  Requirement ID/Description: DB-3, The software shall update customers in a database.
  Purpose: Verify that the CustomerDao class creates a PreparedStatement that the database will
    accept as a modification to an existing row in the customer table.
  Test Setup: Create a stub Address which is passed to a stub Customer object.
  Test Strategy: Pass stub Customer object to a mock database connection and determine what database
    functions are called via a prepared statement that updates an existing customer in the database.
  Input: Mock Database Connection, CustomerDao class, Customer class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  void updateCustomerInDB() throws SQLException {
    CustomerDao cDao = new CustomerDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    cDao.update(customer);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(9)).setString(anyInt(), anyString());
    verify(stmt, times(1)).setBytes(anyInt(), any(byte[].class));
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-1-1
  Requirement ID/Description: DB-1, The software shall read customers in a database.
  Purpose: Verify that the CustomerDao class successfully retrieves customer information from a
    database and creates a Customer object from this information.
  Test Setup: Create CustomerDao for processing database connection.
  Test Strategy: Query database for a specific customer and determine if that customer was returned.
  Input: CustomerDao class, Customer class. Retrieves customer CS001 in the actual database.
  Expected Output: The customer “john Alex” is retrieved from the database and created into a
    Customer object.
   */
  @Test
  void getCustomerFromDB()
      throws SQLException, InvalidAddressInputException, InvalidCustomerInputException {
    CustomerDao custDao = new CustomerDao();
    Customer actualCustomer = custDao.get("CS001");

    Assertions.assertEquals("john", actualCustomer.getFirstname());
    Assertions.assertEquals("Alex", actualCustomer.getLastname());
    Assertions.assertEquals("34232222", actualCustomer.getNic());
    Assertions.assertEquals("3443", actualCustomer.getPassport());
    Assertions.assertEquals("1996-06-01", actualCustomer.getDob());
    Assertions.assertEquals("Male", actualCustomer.getGender());
    Assertions.assertEquals("34324234", actualCustomer.getPhoneNumber());

    System.out.println("Customer retrieved from database: " + actualCustomer.getFirstname() + " "
    + actualCustomer.getLastname());
  }

  /*
  Test Case ID: #DB-1-2
  Requirement ID/Description: DB-1, The software shall read customers in a database.
  Purpose: Verify that the CustomerDao class successfully retrieves customer information for all
    customers from a database and creates an array of Customer objects from this information.
  Test Setup: Create CustomerDao for processing database connection.
  Test Strategy: Query database for all customers and determine if every customer was returned.
  Input: CustomerDao class, Customer class. Retrieves an array of all customers in the actual
    database.
  Expected Output: An array of Customers containing every customer and all their information.
   */
  @Test
  void getAllCustomersFromDB()
      throws InvalidAddressInputException, SQLException, InvalidCustomerInputException {
    CustomerDao custDao = new CustomerDao();
    Customer[] allCustomers = custDao.getAll();
    Assertions.assertNotNull(allCustomers);
    for (Customer cust : allCustomers) {
      System.out.println("Customer " + cust.getId() + ", name: " + cust.getFirstname() + " " +
          cust.getLastname() + ".");
    }
  }
}
