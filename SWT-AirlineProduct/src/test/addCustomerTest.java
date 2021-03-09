package test;

import org.junit.jupiter.api.*;
import main.addCustomer;

public class addCustomerTest {

	static addCustomer addCustomer;
	
	@BeforeAll
	static void beforeAll() {
	}
	
	@BeforeEach
	void beforeEach() {
		addCustomer = new addCustomer();
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}
	
	@Test
	@DisplayName("Image to byte array")
	void testImageToByteArray() {
		String photoPath = "src/test/media/CustomerPicture.png";
		
		Assertions.assertDoesNotThrow(() -> addCustomer.imageToByteArray(photoPath));
	}

	@Test
	@DisplayName("Customer name, numbers in name")
	void testCustomerNameValid() {
		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John290");
		Assertions.assertFalse(isAlphabeticOnly);
	}

	@Test
	@DisplayName("Customer name, only alphabetic characters in name")
	void testCustomerNameInvalid() {
		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John");
		Assertions.assertTrue(isAlphabeticOnly);
	}
	
	@Test
	@DisplayName("Customer contact, correct format (11 characters, all numbers)")
	void testCustomerContactValid() {
		boolean isFormatted = addCustomer.validateContact("12395657465");
		Assertions.assertTrue(isFormatted);
	}
	
	@Test
	@DisplayName("Customer contact, incorrect format (10 characters, all numbers")
	void testCustomerContactNotEnoughDigits() {
		boolean isFormatted = addCustomer.validateContact("1239565746");
		Assertions.assertFalse(isFormatted);
	}
	
	@Test
	@DisplayName("Customer contact, incorrect format (12 characters, all numbers")
	void testCustomerContactTooManyDigits() {
		boolean isFormatted = addCustomer.validateContact("123956574659");
		Assertions.assertFalse(isFormatted);
	}
	
	@Test
	@DisplayName("Customer contact, incorrect format (12 characters, all numbers")
	void testCustomerContactNotAllNumbers() {
		boolean isFormatted = addCustomer.validateContact("1239j657465");
		Assertions.assertFalse(isFormatted);
	}
	
	/*
	@Test
	@DisplayName("Add a customer to the database successfully")
	void testCreateCustomerValid() {
		String id = "1234";
		String firstname = "Todd";
		String lastname = "Bauer";
		String nic = "1293874532";
		String passport = "2398732423948";
		String address = "123 S. Washington St.";
		String dob = "1985-03-12";
		String gender = "Male";
		String contact = "2395654";
		String path = "src/test/media/CustomerPicture.png";
		byte[] photo = addCustomer.imageToByteArray(path);
		
		// Ensure valid input succeeds
		boolean successWithValidInput = addCustomer.createCustomer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photo);
		Assertions.assertTrue(successWithValidInput);
	}
	
	@Test
	@DisplayName("Add a customer to the database unsuccessfully")
	void testCreateCustomerInvalid() {
		String id = "1234";
		String firstname = "Todd";
		String lastname = "Bauer";
		String nic = "1293874532";
		String passport = "2398732423948";
		String address = "123 S. Washington St.";
		String dob = "1985-03-12";
		String gender = "Male";
		String contact = "2395654";
		String path = "src/test/media/CustomerPicture.png";
		byte[] photo = addCustomer.imageToByteArray(path);
		
		// Ensure valid input succeeds
		boolean successWithValidInput = addCustomer.createCustomer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photo);
		Assertions.assertFalse(successWithValidInput);
	}
	*/	
	
}
