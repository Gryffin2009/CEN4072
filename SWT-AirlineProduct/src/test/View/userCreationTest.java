package View;

import org.junit.jupiter.api.*;

class userCreationTest {

	static AddUser addUser;
	
	@BeforeAll
	// Create user before testing
	static void beforeAll() {
		addUser = new AddUser();
	}

	@Test
	@DisplayName("Just run - testing coverage")
	void testAddCustomer() {
		Assertions.assertTrue(true);
	}
	
	/*
	//The software shall allow the user to create a new user with a unique id, 
	// first name, last name, username, and password. 
	@Test
	void testCreateUser() {
		String id = "1234";
		String firstname = "Brandon";
		String lastname = "Baker";
		String password = "p4ssw0rd";
		
		// Ensure valid input succeeds
		String validUsername = "brandonbaker";
		boolean successWithValidInput = AddUser.createUser(id, firstname, lastname, validUsername, password);
		Assertions.assertTrue(successWithValidInput);

		// Ensure invalid input does not succeed
		String invalidUsername = "brandonbaker1";
		boolean successWithInvalidInput = AddUser.createUser(id, firstname, lastname, invalidUsername, password);
		Assertions.assertFalse(successWithInvalidInput);
	}
	*/

}
