package test.Model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import main.Model.User;
import main.Model.User.InvalidUserInputException;
import main.Service.AutoIDService;

public class UserTest {
	static String id;
	
	@BeforeAll
	// Before all test generate user ID
	static void beforeAll() {
		//pass table name and prefix to generateAutoID method.
		AutoIDService.generateAutoID("user", "UO");
	}
	
	@BeforeEach
	void beforeEach() {
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}
	
	@Test 
	void testUpdateInDatabase() {
		
	}
	
	@Test
	@DisplayName("Create user, pairwise for all valid and invalid inputs")
	// Testing valid and invalid input.
	void testCreateUser() {
		
		// Testing invalid input
		Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));
		
		// Testing valid input
		Assertions.assertDoesNotThrow(() -> new User(id, "Todd", "Bauer", "TBFlow", "l33tc0d3r"));
		
		// Testing invalid input
		Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "2Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));
		
		// Testing invalid input
		Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "2Todd", "Bauer", "TBFlow", "l33tc0d3r"));
	}
}
