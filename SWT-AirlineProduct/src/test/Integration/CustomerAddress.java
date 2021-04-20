package Integration;

import Model.Address;
import Model.Customer;
import java.io.IOException;

import org.junit.jupiter.api.*;

import Model.Address.InvalidAddressInputException;
import Model.Customer.InvalidCustomerInputException;
import Service.AutoIDService;

public class CustomerAddress {

Customer customer;

  @BeforeEach
  void beforeEach() throws InvalidCustomerInputException, IOException, InvalidAddressInputException {
    // Before each tests run, reset values for the initial Customer object to be created with.
    // This is necessary to test whether changing any individual properties will throw exceptions.
    // This also facilitates input validation, as each setter in Customer contains validation methods.
    String id = AutoIDService.generateAutoID("customer", "CS");
    String firstname = "Todd";
    String lastname = "Bauer";
    String nic = "1293874532";
    String passport = "2398732423948";
    String dob = "1985-03-12";
    String gender = "Male";
    String contact = "1234567";
    String photoPath = "src/test/media/customer.jpg";

    // Create a fresh Customer to work with for every test. This ensures all tests are working from the same
    // initial values, which ensures no test results will affect other tests.
    customer = new Customer(id, firstname, lastname, nic, passport, addressStub(), dob, gender, contact, photoPath);
  }

  Address addressStub() throws InvalidAddressInputException {
    String streetAddress = "123 Main Street";
    String city = "Fort Myers";
    String state = "Florida";
    String zipCode = "33913";
    String country = "United States";
    return new Address(streetAddress, city, state, zipCode, country);
  }

  // Tries to pass a valid address name to the Customer class.
  @Test
  @DisplayName("Address, valid")
  void testAddressValid() {
    Assertions.assertDoesNotThrow(() -> customer.setAddress(addressStub()));
  }
}
