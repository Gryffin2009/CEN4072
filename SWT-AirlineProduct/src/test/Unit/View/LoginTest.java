package Unit.View;

import View.Login;
import org.junit.jupiter.api.*;

class LoginTest {
	
	static Login login;

	@BeforeAll
	static void beforeAll() {
		login = new Login();		
	}
	
	// Logs in using valid credentials, then invalid credentials.
	@Test
	void test() {
		String username = "john";
		String validPassword = "Passw0rd";
		boolean successWithValidPassword = login.login(username, validPassword);
		Assertions.assertTrue(successWithValidPassword);
	}

	@Test
	void testEmptyLogin() {
		Assertions.assertFalse(login.login("", ""));
	}

	// Ensure invalid password fails
	@Test
	void testInvalidLogin() {
		String username = "john";
		String invalidPassword = "321";
		boolean successWithInvalidPassword = login.login(username, invalidPassword);
		Assertions.assertFalse(successWithInvalidPassword);
	}

}
