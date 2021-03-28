package test.View;


import org.junit.jupiter.api.*;

import main.View.userCreation;

class userCreationTest {

	static userCreation userCreation;
	
	@BeforeAll
	// Create user before testing
	static void beforeAll() {
		userCreation = new userCreation();		
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
		boolean successWithValidInput = userCreation.createUser(id, firstname, lastname, validUsername, password);
		Assertions.assertTrue(successWithValidInput);

		// Ensure invalid input does not succeed
		String invalidUsername = "brandonbaker1";
		boolean successWithInvalidInput = userCreation.createUser(id, firstname, lastname, invalidUsername, password);
		Assertions.assertFalse(successWithInvalidInput);
	}
	*/

}
