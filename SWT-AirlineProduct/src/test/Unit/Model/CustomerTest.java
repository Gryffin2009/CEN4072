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
	/**
	 * Test Case ID: NIC-1-1
	 * Requirement: NIC-1 Customers shall have a NIC. A valid Customer NIC shall contain only alphanumeric characters.
	 * Purpose: Tries to pass a valid NIC to the Customer class
	 * Test setup: A NIC with the following value: A0983427C
	 * Test Strategy: testNicValid is called and should not throw an exception.
	 * Input: a valid NIC object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("NIC, valid")
	void testNicValid() {
		String nic = "A0983427C";
		Assertions.assertDoesNotThrow(() -> customer.setNic(nic));
		Assertions.assertEquals(nic, customer.getNic());
	}

	// Tries to pass an invalid NIC by using a value that contains symbols.
	/**
	 * Test Case ID: NIC-1-2
	 * Requirement: NIC-1 Customers shall have a NIC. A valid Customer NIC shall contain only alphanumeric characters.
	 * Purpose: Tries to pass an invalid NIC to the Customer class
	 * Test setup: A NIC with the following value: A983324&93-2C
	 * Test Strategy: testNicInvalidNonalphanumeric is called and should throw an exception.
	 * Input: an invalid NIC object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("NIC, invalid (Non-alphanumeric characters)")
	void testNicInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setNic("A983324&93-2C"));
		Assertions.assertEquals("Customer NIC must contain alphanumeric characters only.", e.getMessage());
	}
	
	// Tries to pass an invalid phone number to the Customer class.
	/**
	 * Test Case ID: C-2-1
	 * Requirement: C-2 Customers shall have a valid phone number. A valid phone number shall be less than 14 digits long and contain only numeric values, e.g. 0012399893647.
	 * Purpose: Tries to pass an invalid phone number to the Customer class
	 * Test setup: A phone number with the following value: 239940423466543234
	 * Test Strategy: testPhoneNumberMaximum is called and should throw an exception.
	 * Input: an invalid phone number object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("Phone number, numerical input over 14 characters")
	void testPhoneNumberMaximum() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setPhoneNumber("239940423466543234"));
		Assertions.assertEquals("Invalid Phone Number", e.getMessage());
	}

	// Tries to pass a valid phone number to the Customer class.
	/**
	 * Test Case ID: C-2-2
	 * Requirement: C-2 Customers shall have a valid phone number. A valid phone number shall be less than 14 digits long and contain only numeric values, e.g. 0012399893647.
	 * Purpose: Tries to pass a valid phone number to the Customer class
	 * Test setup: A phone number with the following value: 2399443234
	 * Test Strategy: testValidPhoneNumber is called and should not throw an exception.
	 * Input: a valid phone number object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Phone number, numerical input under 14 characters")
	void testValidPhoneNumber() {
		Assertions.assertDoesNotThrow(() -> customer.setPhoneNumber("2399443234"));
	}

	// Tries to pass an invalid phone number to the Customer class.
	/**
	 * Test Case ID: C-2-3
	 * Requirement: C-2 Customers shall have a valid phone number. A valid phone number shall be less than 14 digits long and contain only numeric values, e.g. 0012399893647.
	 * Purpose: Tries to pass an invalid phone number to the Customer class
	 * Test setup: A phone number with the following value: 2394
	 * Test Strategy: testPhoneNumberMinimum is called and should not throw an exception.
	 * Input: an invalid phone number object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("Phone number, numerical input under 7 characters")
	void testPhoneNumberMinimum() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setPhoneNumber("2394"));
		Assertions.assertEquals("Invalid Phone Number", e.getMessage());
	}

	// Tries to pass a valid passport number to the Customer class.
	/**
	 * Test Case ID: C-6-1
	 * Requirement: C-6 Customers shall have a valid passport ID. A valid passport ID shall contain only numbers.
	 * Purpose: Tries to pass a valid passport number to the Customer class
	 * Test setup: A phone number with the following value: 324987324
	 * Test Strategy: testPassportValid is called and should not throw an exception.
	 * Input: a valid passport number object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Passport, valid")
	void testPassportValid() {
		Assertions.assertDoesNotThrow(() -> customer.setPassport("324987324"));
	}

	// Tries to pass an invalid passport number by using a value that contains symbols.
	/**
	 * Test Case ID: C-6-2
	 * Requirement: C-6 Customers shall have a valid passport ID. A valid passport ID shall contain only numbers.
	 * Purpose: Tries to pass an invalid passport number to the Customer class
	 * Test setup: A phone number with the following value: 32489-4329!938
	 * Test Strategy: testPassportInvalidNonalphanumeric is called and should  throw an exception.
	 * Input: an invalid passport number object
	 * Expected output: the method should throw an exception
	 **/
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
	/**
	 * Test Case ID: C-3-1
	 * Requirement: C-3 Customers shall have a valid Address. A valid address must adhere to the Address model for validation requirements.
	 * Purpose: Tries to pass a valid address to the Customer class
	 * Test setup: An address with the following values:
	 *      String streetAddress = "123 Main Street";
	 * 		String city = "Fort Myers";
	 * 		String state = "Florida";
	 * 		String zipCode = "33913";
	 * 		String country = "United States";
	 * Test Strategy: testAddressValid is called and should not throw an exception.
	 * Input: a valid address object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Address, valid")
	void testAddressValid() {
		Assertions.assertDoesNotThrow(() -> customer.setAddress(addressStub()));
	}

	// Tries to pass a invalid address to the Customer class.
	/**
	 * Test Case ID: C-3-2
	 * Requirement: C-3 Customers shall have a valid Address. A valid address must adhere to the Address model for validation requirements.
	 * Purpose: Tries to pass a valid address to the Customer class
	 * Test setup: An address with the following value: NULL
	 * Test Strategy: testAddressInvalid is called and should throw an exception.
	 * Input: an invalid address object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("Address, invalid")
	void testAddressInvalid() {
		Exception e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setAddress(null));
		Assertions.assertEquals("Invalid address.", e.getMessage());
	}

	// Tries to pass a valid first name to the Customer class.
	/**
	 * Test Case ID: C-4-1
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid first name to the Customer class
	 * Test setup: A first name with the following value: "Brandon"
	 * Test Strategy: testFirstnameValid is called and should not throw an exception.
	 * Input: a valid first name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("First name, valid")
	void testFirstnameValid() {
		String name = "Brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes a dash.
	/**
	 * Test Case ID: C-4-4
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid first name to the Customer class that contains a dash.
	 * Test setup: A first name with the following value: "la-brandon"
	 * Test Strategy: testFirstnameValidDashes is called and should not throw an exception.
	 * Input: a valid first name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("First name, valid with dash")
	void testFirstnameValidDashes() {
		String name = "la-brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes an apostrophe.
	/**
	 * Test Case ID: C-4-5
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid first name to the Customer class that contains an apostrophe.
	 * Test setup: A first name with the following value: "o'brandon"
	 * Test Strategy: testFirstnameValidApostrophes is called and should not throw an exception.
	 * Input: a valid first name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("First name, valid with apostrophe")
	void testFirstnameValidApostrophes() {
		String name = "o'brandon";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass a valid first name that includes an apostrophe.
	/**
	 * Test Case ID: C-4-6
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid first name to the Customer class that contains an apostrophe and a dash.
	 * Test setup: A first name with the following value: "o'brand-on""
	 * Test Strategy: testFirstnameValidApostrophesAndDashes is called and should not throw an exception.
	 * Input: a valid first name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("First name, valid with dash and apostrophe")
	void testFirstnameValidApostrophesAndDashes() {
		String name = "o'brand-on";
		Assertions.assertDoesNotThrow(() -> customer.setFirstname(name));
		Assertions.assertEquals(name, customer.getFirstname());
	}

	// Tries to pass an invalid first name by using a name that contains numbers.
	/**
	 * Test Case ID: C-4-2
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a invalid first name to the Customer class
	 * Test setup: A first name with the following value: "brandon1"
	 * Test Strategy: testFirstnameInvalidNumbers is called and should throw an exception.
	 * Input: an invalid first name object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, 
				() -> customer.setFirstname("brandon1"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass an invalid first name by using a name that contains only numbers.
	/**
	 * Test Case ID: C-4-3
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a invalid first name to the Customer class that contains only numbers.
	 * Test setup: A first name with the following value: "121231"
	 * Test Strategy: testFirstnameInvalidOnlyNumbers is called and should throw an exception.
	 * Input: an invalid first name object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidOnlyNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setFirstname("121231"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass an invalid first name by using a name that contains symbols.
	/**
	 * Test Case ID: C-4-8
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a invalid first name to the Customer class that contains symbols.
	 * Test setup: A first name with the following value: "brandon$"
	 * Test Strategy: testFirstnameInvalidSymbols is called and should throw an exception.
	 * Input: an invalid first name object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("First name, invalid (Symbols in name)")
	void testFirstnameInvalidSymbols() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setFirstname("brandon$"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid last name to the Customer class.
	/**
	 * Test Case ID: C-4-7
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid last name to the Customer class.
	 * Test setup: A last name with the following value: "Bauer"
	 * Test Strategy: testLastnameValid is called and should not throw an exception.
	 * Input: a valid last name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValid() {
		String name = "Bauer";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	/**
	 * Test Case ID: C-4-9
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid last name to the Customer class.
	 * Test setup: A last name with the following value: "Bau-er"
	 * Test Strategy: testLastnameValidWithDashes is called and should not throw an exception.
	 * Input: a valid last name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithDashes() {
		String name = "Bau-er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	/**
	 * Test Case ID: C-4-10
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid last name to the Customer class.
	 * Test setup: A last name with the following value: "Bau'er"
	 * Test Strategy: testLastnameValidWithApostrophes is called and should not throw an exception.
	 * Input: a valid last name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithApostrophes() {
		String name = "Bau'er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a valid last name to the Customer class.
	/**
	 * Test Case ID: C-4-11
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass a valid last name to the Customer class.
	 * Test setup: A last name with the following value: "Ba-u'er"
	 * Test Strategy: testLastnameValidWithApostrophesAndDashes is called and should not throw an exception.
	 * Input: a valid last name object
	 * Expected output: the method should not throw an exception
	 **/
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValidWithApostrophesAndDashes() {
		String name = "Ba-u'er";
		Assertions.assertDoesNotThrow(() -> customer.setLastname(name));
		Assertions.assertEquals(name, customer.getLastname());
	}
	

	// Tries to pass a invalid last name to the Customer class.
	/**
	 * Test Case ID: C-4-12
	 * Requirement: C-4 Customers shall have a valid first name. A valid Customer first name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: Tries to pass an invalid last name to the Customer class.
	 * Test setup: A last name with the following value: "120123131"
	 * Test Strategy: testLastnameInvalidNumerical is called and should throw an exception.
	 * Input: an invalid last name object
	 * Expected output: the method should throw an exception
	 **/
	@Test
	@DisplayName("Last name, invalid numerical")
	void testLastnameInvalidNumerical() {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		customer.setLastname("120123131"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}
	
	// Tries to pass an invalid last name by using a name that contains numbers.
	/*
	 * Test Case ID: TC-16
	 * Requirement ID/Description: C-4 Customers shall have a valid last name. A valid Customer last
	 * 		name shall contain only alphabetic characters, apostrophes, and dashes.
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: equivalence testing: block alphanumerical input (invalid)
	 * Input: 120Bauer
	 * Expected Output: Last name value is not accepted.
	 */
	@Test
	@DisplayName("Last name, invalid (Numbers in name)")
	void testLastnameInvalidAlphaNumerical() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () -> customer.setLastname("120Bauer"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid date of birth to the Customer class.
	/*
	 * Test Case ID: TC-21
	 * Requirement ID/Description: C-6 Customers shall have a valid date of birth. Customer date of]
	 *		 birth shall be in the following format: YYYY-MM-DD
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: equivalence testing: block valid date
	 * Input: 1985-03-12
	 * Expected Output: Date of birth value is accepted.
	 */
	@Test
	@DisplayName("Date of birth, valid")
	void testDobValid() {
		Assertions.assertDoesNotThrow(() -> customer.setDob("1985-03-12"));
	}

	// Tries to pass an invalid date of birth by using the format MM-DD-YYYY.
	/*
	 * Test Case ID: TC-22
	 * Requirement ID/Description: C-6 Customers shall have a valid date of birth. Customer date of]
	 *		 birth shall be in the following format: YYYY-MM-DD
	 * Purpose: To ensure only valid values are accepted.
	 * Test setup: A customer object is created for the setup.
	 * Test Strategy: equivalence testing: block invalid date
	 * Input: 03-12-1985
	 * Expected Output: Date of birth value is not accepted.
	 */
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
	 * Test Strategy: equivalence testing: block valid gender
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
	 * Test Strategy: equivalence testing: block valid gender
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
	 * Test Strategy: equivalence testing: block invalid gender
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
