package test;

import java.sql.Blob;

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
	@DisplayName("Customer name, numbers in name")
	void testInvalidCustomerName() {
		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John290");
		Assertions.assertEquals(false, isAlphabeticOnly);
	}

	@Test
	@DisplayName("Customer name, only alphabetic characters in name")
	void testValidCustomerName() {
		boolean isAlphabeticOnly = addCustomer.validateCustomerName("John");
		Assertions.assertEquals(true, isAlphabeticOnly);
	}
	
	@Test
	@DisplayName("Add a customer to the database")
	void testCreateCustomer() {
		/*
		  `id` varchar(255) NOT NULL,
		  `firstname` varchar(255) NOT NULL,
		  `lastname` varchar(255) NOT NULL,
		  `nic` varchar(255) NOT NULL,
		  `passport` varchar(255) NOT NULL,
		  `address` text NOT NULL,
		  `dob` varchar(255) NOT NULL,
		  `gender` varchar(255) NOT NULL,
		  `contact` int(11) NOT NULL,
		  `photo` longblob NOT NULL
		/*
		 * 
		 */
		String id = "1234";
		String firstname = "Todd";
		String lastname = "Bauer";
		String nic = "1293874532";
		String passport = "2398732423948";
		String address = "123 S. Washington St.";
		String dob = "1985-03-12";
		String gender = "Male";
		String contact = "2395654";
		
		// Ensure valid input succeeds
		//boolean successWithValidInput = addCustomer.createCustomer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photo);
		//Assertions.assertTrue(successWithValidInput);

		// Ensure invalid input does not succeed
		//boolean successWithInvalidInput = addCustomer.createCustomer(id, firstname, lastname, nic, passport, address, dob, gender, contact, photo);
		//Assertions.assertFalse(successWithInvalidInput);		
	}
	
	
}
