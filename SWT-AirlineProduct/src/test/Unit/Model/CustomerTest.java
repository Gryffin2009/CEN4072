package Unit.Model;

import Model.Address;
import Model.Customer;
import java.io.IOException;

import org.junit.jupiter.api.*;

import Model.Address.InvalidAddressInputException;
import Model.Customer.InvalidCustomerInputException;
import Service.AutoIDService;

public class CustomerTest {
	
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

	// Tries to pass a valid NIC to the Customer class.
	@Test
	@DisplayName("NIC, valid")
	void testNicValid() {
		String nic = "A0983427C";
		Assertions.assertDoesNotThrow(() -> customer.setNic(nic));
		Assertions.assertEquals(nic, customer.getNic());
	}

	// Tries to pass an invalid NIC by using a value that contains symbols.
	@Test
	@DisplayName("NIC, invalid (Non-alphanumeric characters)")
	void testNicInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setNic("A983324&93-2C"));
		Assertions.assertEquals("Customer NIC must contain alphanumeric characters only.", e.getMessage());
	}
	
	// Tries to pass an invalid phone number to the Customer class.
	@Test
	@DisplayName("Phone number, numerical input over 14 characters")
	void testPhoneNumberMaximum() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setPhoneNumber("239940423466543234"));
		Assertions.assertEquals("Invalid Phone Number", e.getMessage());
	}

	// Tries to pass a valid phone number to the Customer class.
	@Test
	@DisplayName("Phone number, numerical input under 14 characters")
	void testValidPhoneNumber() {
		Assertions.assertDoesNotThrow(() -> customer.setPhoneNumber("2399443234"));
	}

	// Tries to pass an invalid phone number to the Customer class.
	@Test
	@DisplayName("Phone number, numerical input under 7 characters")
	void testPhoneNumberMinimum() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setPhoneNumber("2394"));
		Assertions.assertEquals("Invalid Phone Number", e.getMessage());
	}

	// Tries to pass a valid passport number to the Customer class.
	@Test
	@DisplayName("Passport, valid")
	void testPassportValid() {
		Assertions.assertDoesNotThrow(() -> customer.setPassport("324987324"));
	}

	// Tries to pass an invalid passport number by using a value that contains symbols.
	@Test
	@DisplayName("Passport, invalid (Non-alphanumeric characters)")
	void testPassportInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setPassport("32489-4329!938"));
		Assertions.assertEquals("Customer Passport must contain alphanumeric characters only.", e.getMessage());
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

	// Tries to pass a invalid address to the Customer class.
	@Test
	@DisplayName("Address, invalid")
	void testAddressInvalid() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setAddress(null));
		Assertions.assertEquals("Invalid address.", e.getMessage());
	}

	// Tries to pass a valid first name to the Customer class.
	@Test
	@DisplayName("First name, valid")
	void testFirstnameValid() {
		String name = "Brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes a dash.
	@Test
	@DisplayName("First name, valid with dash")
	void testFirstnameValidDashes() {
		String name = "la-brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes an apostrophe.
	@Test
	@DisplayName("First name, valid with apostrophe")
	void testFirstnameValidApostrophes() {
		String name = "o'brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes an apostrophe.
	@Test
	@DisplayName("First name, valid with dash and apostrophe")
	void testFirstnameValidApostrophesAndDashes() {
		String name = "o'brand-on";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass an invalid first name by using a name that contains numbers.
	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, 
				() -> customer.setFirstname("brandon1"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass an invalid first name by using a name that contains only numbers.
	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidOnlyNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setFirstname("121231"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass an invalid first name by using a name that contains symbols.
	@Test
	@DisplayName("First name, invalid (Symbols in name)")
	void testFirstnameInvalidSymbols() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setFirstname("brandon$"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid last name to the Customer class.
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValid() {
		String name = "Bauer";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithDashes() {
		String name = "Bau-er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithApostrophes() {
		String name = "Bau'er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithApostrophesAndDashes() {
		String name = "Ba-u'er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a invalid last name to the Customer class.
	@Test
	@DisplayName("Last name, invalid numerical")
	void testLastnameInvalidNumerical() {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		customer.setLastname("120123131"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}
	
	// Tries to pass an invalid last name by using a name that contains numbers.
	@Test
	@DisplayName("Last name, invalid (Numbers in name)")
	void testLastnameInvalidAlphaNumerical() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setLastname("120Bauer"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid date of birth to the Customer class.
	@Test
	@DisplayName("Date of birth, valid")
	void testDobValid() {
		Assertions.assertDoesNotThrow(() -> customer.setDob("1985-03-12"));
	}

	// Tries to pass an invalid date of birth by using the format MM-DD-YYYY.
	@Test
	@DisplayName("Date of birth, invalid (Illegal format)")
	void testDobInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		customer.setDob("03-12-1985"));
		Assertions.assertEquals("Customer date of birth must be in the following format: YYYY-MM-DD.", e.getMessage());
	}

	// Tries to pass a valid gender to the Customer class.
	/*
	 * Test Case ID: TC-C-15
	 * Requirement ID/Description: C-8 Customers shall have a valid gender. A valid gender shall
	 * 			be either male or female.
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: positive
	 * Input: "Male"
	 * Expected Output: Gender value is accepted.
	 */
	@Test
	@DisplayName("Gender, valid (Male)")
	void testGenderValidMale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Male"));
	}

	// Tries to pass a valid gender to the Customer class.
	/*
	 * Test Case ID: TC-C-16
	 * Requirement ID/Description: C-8 Customers shall have a valid gender. A valid gender shall
	 * 			be either male or female.
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: positive
	 * Input: "Female"
	 * Expected Output: Gender value is accepted.
	 */
	@Test
	@DisplayName("Gender, valid (Female)")
	void testGenderValidFemale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Female"));
	}

	// Tries to pass an invalid gender by using a gender that is not Male or Female.
	/*
	 * Test Case ID: TC-C-17
	 * Requirement ID/Description: C-8 Customers shall have a valid gender. A valid gender shall
	 * 			be either male or female.
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: negative
	 * Input: "Badger"
	 * Expected Output: Gender value is not accepted.
	 */
	@Test
	@DisplayName("Gender, invalid (Not Male or Female)")
	void testGenderInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setGender("Badger"));
		Assertions.assertEquals("Customer gender must be either Male or Female.", e.getMessage());
	}

}
