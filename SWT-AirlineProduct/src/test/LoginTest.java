package test;

import org.junit.jupiter.api.*;
import main.Login;

class LoginTest {
	
	static Login login;

	@BeforeAll
	static void beforeAll() {
		login = new Login();		
	}
	
	@Test
	void test() {
		String username = "brandon";
		
		// Ensure valid password succeeds
		String validPassword = "p4ssw0rd";
		boolean successWithValidPassword = login.login(username, validPassword);
		Assertions.assertTrue(successWithValidPassword);
		
		// Ensure invalid password fails
		String invalidPassword = "p4ssw0rd";
		boolean successWithInvalidPassword = login.login(username, invalidPassword);
		Assertions.assertFalse(successWithInvalidPassword);
	}

}
