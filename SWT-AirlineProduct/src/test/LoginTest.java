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
		String username = "john";
		
		// Ensure valid password succeeds
		String validPassword = "123";
		boolean successWithValidPassword = login.login(username, validPassword);
		Assertions.assertTrue(successWithValidPassword);
		
		// Ensure invalid password fails
		String invalidPassword = "321";
		boolean successWithInvalidPassword = login.login(username, invalidPassword);
		Assertions.assertFalse(successWithInvalidPassword);
	}

}
