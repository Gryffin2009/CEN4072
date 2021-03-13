package test.View;

import org.junit.jupiter.api.*;

import main.View.addCustomer;

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
	
//	@Test
//	@DisplayName("Image to byte array")
//	void testImageToByteArray() {
//		String photoPath = "src/test/media/CustomerPicture.png";
//		
//		Assertions.assertDoesNotThrow(() -> addCustomer.imageToByteArray(photoPath));
//	}
//
//	@Test
//	@DisplayName("Customer name, numbers in name")
//	// Testing customer name to see if name contains numerical characters.
//	void testCustomerNameValid() {
//		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John290");
//		Assertions.assertFalse(isAlphabeticOnly);
//	}
//
//	@Test
//	@DisplayName("Customer name, only alphabetic characters in name")
//	// Testing customer name to see if it contains only alphabetical characters.
//	void testCustomerNameInvalid() {
//		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John");
//		Assertions.assertTrue(isAlphabeticOnly);
//	}
//	
//	@Test
//	@DisplayName("Customer contact, correct format (7 characters, all numbers)")
//	// Testing contact information for valid input (7 digits & all numerical input)
//	void testCustomerContactValid() {
//		boolean isFormatted = addCustomer.validateContact("1234567");
//		Assertions.assertTrue(isFormatted);
//	}
//	
//	@Test
//	@DisplayName("Customer contact, incorrect format (10 characters, all numbers")
//	// Testing contact information for invalid input (not enough digits)
//	void testCustomerContactNotEnoughDigits() {
//		boolean isFormatted = addCustomer.validateContact("123456");
//		Assertions.assertFalse(isFormatted);
//	}
//	
//	@Test
//	@DisplayName("Customer contact, incorrect format (12 characters, all numbers")
//	// Testing contact information for invalid input (to many digits)
//	void testCustomerContactTooManyDigits() {
//		boolean isFormatted = addCustomer.validateContact("12345678");
//		Assertions.assertFalse(isFormatted);
//	}
//	
//	@Test
//	@DisplayName("Customer contact, incorrect format (12 characters, all numbers")
//	// Testing contact information for invalid input (to many digits and incorrect format)
//	void testCustomerContactNotAllNumbers() {
//		boolean isFormatted = addCustomer.validateContact("1239j657465");
//		Assertions.assertFalse(isFormatted);
//	}
	
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
