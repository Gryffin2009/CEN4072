package test;

import java.io.IOException;

import org.junit.jupiter.api.*;

import main.AutoIDService;
import main.Customer;
import main.Customer.InvalidCustomerInputException;

public class CustomerTest {
	
	static String id;
	static String firstname;
	static String lastname;
	static String nic;
	static String passport;
	static String address;
	static String dob;
	static String gender;
	static String contact;
	static String photoPath;
	
	Customer customer;
	
	@BeforeAll
	static void beforeAll() {
		id = AutoIDService.generateAutoID("customer", "CS");
		firstname = "Todd";
		lastname = "Bauer";
		nic = "1293874532";
		passport = "2398732423948";
		address = "123 S. Washington St.";
		dob = "1985-03-12";
		gender = "Male";
		contact = "1234567";
		photoPath = "src/test/media/CustomerPicture.png";
	}
	
	@BeforeEach
	void beforeEach() throws InvalidCustomerInputException, IOException {
		customer = new Customer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photoPath);
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Id, valid")
	void testIdValid() {
		Assertions.assertDoesNotThrow(() -> customer.setId("CS003"));
	}

	@Test
	@DisplayName("Id, invalid (Improper prefix)")
	void testIdInvalidPrefix() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CA003"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	@Test
	@DisplayName("Id, invalid (Too many digits)")
	void testIdInvalidTooManyDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CS0003"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	@Test
	@DisplayName("Id, invalid (Not enough digits)")
	void testIdInvalidNotEnoughDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setId("CS03"));
	Assertions.assertEquals("ID must be in the format \"CS###\".", e.getMessage());		
	}

	@Test
	@DisplayName("First name, valid")
	void testFirstnameValid() {
		Assertions.assertDoesNotThrow(() -> customer.setFirstname("Todd"));
	}

	@Test
	@DisplayName("First name, invalid (Numbers in name)")
	void testFirstnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setFirstname("Todd920"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	@Test
	@DisplayName("Last name, valid")
	void testLastnameValid() {
		Assertions.assertDoesNotThrow(() -> customer.setLastname("Bauer"));
	}

	@Test
	@DisplayName("Last name, invalid (Numbers in name)")
	void testLastnameInvalidNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setLastname("120Bauer"));
		Assertions.assertEquals("Customer name must contain alphabetic characters only.", e.getMessage());
	}

	@Test
	@DisplayName("NIC, valid")
	void testNicValid() {
		Assertions.assertDoesNotThrow(() -> customer.setNic("A0983427C"));
	}

	@Test
	@DisplayName("NIC, invalid (Non-alphanumeric characters)")
	void testNicInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setNic("A983324&93-2C"));
		Assertions.assertEquals("Customer NIC must contain alphanumeric characters only.", e.getMessage());
	}

	@Test
	@DisplayName("Passport, valid")
	void testPassportValid() {
		Assertions.assertDoesNotThrow(() -> customer.setPassport("324987324"));
	}

	@Test
	@DisplayName("Passport, invalid (Non-alphanumeric characters)")
	void testPassportInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setPassport("32489-4329!938"));
		Assertions.assertEquals("Customer Passport must contain alphanumeric characters only.", e.getMessage());
	}

	@Test
	@DisplayName("Date of birth, valid")
	void testDobValid() {
		Assertions.assertDoesNotThrow(() -> customer.setDob("1985-03-12"));
	}

	@Test
	@DisplayName("Date of birth, invalid (Illegal format)")
	void testDobInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		customer.setDob("03-12-1985"));
		Assertions.assertEquals("Customer date of birth must be in the following format: YYYY-MM-DD.", e.getMessage());
	}

	@Test
	@DisplayName("Gender, valid (Male)")
	void testGenderValidMale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Male"));
	}

	@Test
	@DisplayName("Gender, valid (Female)")
	void testGenderValidFemale() {
		Assertions.assertDoesNotThrow(() -> customer.setGender("Female"));
	}

	@Test
	@DisplayName("Gender, invalid (Not Male or Female)")
	void testGenderInvalidNonalphanumeric() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setGender("Badger"));
		Assertions.assertEquals("Customer gender must be either Male or Female.", e.getMessage());
	}

	@Test
	@DisplayName("Contact number, valid")
	void testContactValid() throws InvalidCustomerInputException, Exception {
		Assertions.assertDoesNotThrow(() -> customer.setContact("1234567"));
	}

	@Test
	@DisplayName("Contact number, invalid (too many numbers)")
	void testContactInvalidTooManyNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setContact("12345678"));
		Assertions.assertEquals("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.", e.getMessage());
	}

	@Test
	@DisplayName("Contact number, invalid (not enough numbers)")
	void testContactInvalidNotEnoughNumbers() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			customer.setContact("123456"));
		Assertions.assertEquals("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.", e.getMessage());
	}
}
