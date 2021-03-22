package test.Model;

import java.io.IOException;

import org.junit.jupiter.api.*;

import main.Model.Customer;
import main.Model.Customer.InvalidCustomerInputException;
import main.Service.AutoIDService;

public class CustomerTest {
	
	Customer customer;
	
	@BeforeAll
	static void beforeAll() {

	}
	
	@BeforeEach
	void beforeEach() throws InvalidCustomerInputException, IOException {
		
		// Before each tests run, reset values for the initial Customer object to be created with.
		// This is necessary to test whether changing any individual properties will throw exceptions.
		// This also facilitates input validation, as each setter in Customer contains validation methods.
		String id = AutoIDService.generateAutoID("customer", "CS");
		String firstname = "Todd";
		String lastname = "Bauer";
		String nic = "1293874532";
		String passport = "2398732423948";
		String address = "123 S. Washington St.";
		String dob = "1985-03-12";
		String gender = "Male";
		String contact = "1234567";
		String photoPath = "src/test/media/CustomerPicture.png";
		// Create a fresh Customer to work with for every test. This ensures all tests are working from the same
		// initial values, which ensures no test results will affect other tests.
		customer = new Customer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photoPath);
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	// Tries to pass a valid Customer Id to the Customer class.
	@Test
	@DisplayName("Id, valid")
	void testIdValid() {
		Assertions.assertDoesNotThrow(() -> customer.setId("CS003"));
	}

	// Tries to pass an invalid Id to the customer class by using an Id with an incorrect prefix.
	@Test
	@DisplayName("Id, invalid (Improper prefix)")
	void testIdInvalidPrefix() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CA003"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	// Tries to pass an invalid Id to the customer class by using an Id with too many trailing digits.
	@Test
	@DisplayName("Id, invalid (Too many digits)")
	void testIdInvalidTooManyDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CS0003"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	// Tries to pass an invalid Id to the customer class by using an Id with too few trailing digits.
	@Test
	@DisplayName("Id, invalid (Not enough digits)")
	void testIdInvalidNotEnoughDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CS03"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	// Tries to pass a valid first name to the Customer class.
	@Test
	@DisplayName("First name, valid")
	void testFirstnameValid() {
		Assertions.assertDoesNotThrow(() -> customer.setFirstname("brandon"));
	}

	// Tries to pass a valid first name that includes a dash.
	@Test
	@DisplayName("First name, valid with dash")
	void testFirstnameValidDashes() {
		Assertions.assertDoesNotThrow(() -> customer.setFirstname("la-brandon"));
	}

	// Tries to pass a valid first name that includes an apostrophe.
	@Test
	@DisplayName("First name, valid with apostrophe")
	void testFirstnameValidApostrophes() {
		Assertions.assertDoesNotThrow(() -> customer.setFirstname("o'brandon"));
	}

	// Tries to pass an invalid first name by using a name that contains numbers.
	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setFirstname("brandon1"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass an invalid first name by using a name that contains symbols.
	@Test
	@DisplayName("First name, invalid (Symbols in name)")
	void testFirstnameInvalidSymbols() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setFirstname("brandon$"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid last name to the Customer class.
	@Test
	@DisplayName("Last name, valid")
	void testLastnameValid() {
		Assertions.assertDoesNotThrow(() -> customer.setLastname("Bauer"));
	}

	// Tries to pass an invalid last name by using a name that contains numbers.
	@Test
	@DisplayName("Last name, invalid (Numbers in name)")
	void testLastnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setLastname("120Bauer"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	// Tries to pass a valid NIC to the Customer class.
	@Test
	@DisplayName("NIC, valid")
	void testNicValid() {
		Assertions.assertDoesNotThrow(() -> customer.setNic("A0983427C"));
	}

	// Tries to pass an invalid NIC by using a value that contains symbols.
	@Test
	@DisplayName("NIC, invalid (Non-alphanumeric characters)")
	void testNicInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setNic("A983324&93-2C"));
		Assertions.assertEquals("Customer NIC must contain alphanumeric characters only.", e.getMessage());
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
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setPassport("32489-4329!938"));
		Assertions.assertEquals("Customer Passport must contain alphanumeric characters only.", e.getMessage());
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
	@Test
	@DisplayName("Gender, valid (Male)")
	void testGenderValidMale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Male"));
	}

	// Tries to pass a valid gender to the Customer class.
	@Test
	@DisplayName("Gender, valid (Female)")
	void testGenderValidFemale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Female"));
	}

	// Tries to pass an invalid gender by using a gender that is not Male or Female.
	@Test
	@DisplayName("Gender, invalid (Not Male or Female)")
	void testGenderInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setGender("Badger"));
		Assertions.assertEquals("Customer gender must be either Male or Female.", e.getMessage());
	}

	// Tries to pass a valid phone number to the Customer class.
	@Test
	@DisplayName("Contact number, valid")
	void testContactValid() throws InvalidCustomerInputException, Exception {
		Assertions.assertDoesNotThrow(() -> customer.setContact("1234567"));
	}

	// Tries to pass an invalid phone number by using a value that has too many numbers.
	@Test
	@DisplayName("Contact number, invalid (too many numbers)")
	void testContactInvalidTooManyNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setContact("12345678"));
		Assertions.assertEquals("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.", e.getMessage());
	}

	// Tries to pass an invalid phone number by using a value that has too few numbers.
	@Test
	@DisplayName("Contact number, invalid (not enough numbers)")
	void testContactInvalidNotEnoughNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setContact("123456"));
		Assertions.assertEquals("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.", e.getMessage());
	}
}
