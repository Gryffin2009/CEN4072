package Service;

import Model.Address;
import Model.Address.InvalidAddressInputException;
import Model.Customer;
import Model.Customer.InvalidCustomerInputException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
  private Connection con;

  public CustomerDao() {
    con = NetworkService.getInstance().getConnection();
  }
  public CustomerDao(Connection con) {
    this.con = con;
  }

  public void add(Customer customer) throws SQLException {
      PreparedStatement pst = con.prepareStatement(
          "INSERT INTO customer"
              + "(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)"
              + "VALUES(?,?,?,?,?,?,?,?,?,?)");
      pst.setString(1, customer.getId());
      pst.setString(2, customer.getFirstname());
      pst.setString(3, customer.getLastname());
      pst.setString(4, customer.getNic());
      pst.setString(5, customer.getPassport());
      pst.setString(6, customer.getAddress().toString());
      pst.setString(7, customer.getDob());
      pst.setString(8, customer.getGender());
      pst.setString(9, customer.getPhoneNumber());
      pst.setBytes(10, customer.getPhoto());
      pst.executeUpdate();
  }

  public void update(Customer customer) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "UPDATE customer SET"
            + "id=?,"
            + "firstname=?,"
            + "lastname=?,"
            + "nic=?,"
            + "passport=?,"
            + "address=?,"
            + "dob=?,"
            + "gender=?,"
            + "contact=?, "
            + "photo=?"
    );
    pst.setString(1, customer.getId());
    pst.setString(2, customer.getFirstname());
    pst.setString(3, customer.getLastname());
    pst.setString(4, customer.getNic());
    pst.setString(5, customer.getPassport());
    pst.setString(6, customer.getAddress().toString());
    pst.setString(7, customer.getDob());
    pst.setString(8, customer.getGender());
    pst.setString(9, customer.getPhoneNumber());
    pst.setBytes(10, customer.getPhoto());
    pst.executeUpdate();
  }

  public Customer get(String id) throws SQLException, InvalidAddressInputException, InvalidCustomerInputException {
    PreparedStatement pst = con.prepareStatement("select * from customer where id = ?");
    pst.setString(1, id);
    ResultSet rs = pst.executeQuery();
    rs.next();

    String fname = rs.getString("firstname");
    String lname = rs.getString("lastname");
    String nic = rs.getString("nic");
    String passport = rs.getString("passport");

    // TODO convert address from database to Address object
    Address address = new Address("123 Test", "Test", "Test", "Test", "Test");

    //String address = rs.getString("address");
    String dob = rs.getString("dob");
    String gender = rs.getString("gender");
    String contact = rs.getString("contact");
    Blob blob = rs.getBlob("photo");
    byte[] _imagebytes = blob.getBytes(1, (int) blob.length());

    Customer customer = new Customer(id, fname, lname, nic, passport, address, dob, gender, contact, _imagebytes);
    return customer;
  }

  public Customer[] getAll()
      throws SQLException, InvalidAddressInputException, InvalidCustomerInputException {
    PreparedStatement pst = con.prepareStatement("SELECT count(*) AS rowCount FROM customer");
    ResultSet rs = pst.executeQuery();
    rs.next();
    int ticketCount = rs.getInt("rowCount");
    rs.close();
    Customer[] customers = new Customer[ticketCount];

    pst = con.prepareStatement("SELECT * from customer");
    rs = pst.executeQuery();

    int i = 0;
    while (rs.next()) {
      String id = rs.getString("id");
      String fname = rs.getString("firstname");
      String lname = rs.getString("lastname");
      String nic = rs.getString("nic");
      String passport = rs.getString("passport");

      // TODO convert address from database to Address object
      Address address = new Address("123 Test", "Test", "Test", "Test", "Test");

      //String address = rs.getString("address");
      String dob = rs.getString("dob");
      String gender = rs.getString("gender");
      String contact = rs.getString("contact");
      Blob blob = rs.getBlob("photo");
      byte[] _imagebytes = blob.getBytes(1, (int) blob.length());

      customers[i] = new Customer(id, fname, lname, nic, passport, address, dob, gender, contact, _imagebytes);
      i++;
    }
    return customers;
  }
}
