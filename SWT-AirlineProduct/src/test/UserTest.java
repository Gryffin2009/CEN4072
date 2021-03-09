package test;

import java.io.IOException;

import org.junit.jupiter.api.*;

import main.AutoIDService;
import main.User;
import main.User.InvalidUserInputException;

public class UserTest {
	static String id;
	
	@BeforeAll
	static void beforeAll() {
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
	@DisplayName("Create user, pairwise for all valid and invalid inputs")
	void testCreateUser() {
		InvalidUserInputException e = Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));
		
		Assertions.assertDoesNotThrow(() -> new User(id, "Todd", "Bauer", "TBFlow", "l33tc0d3r"));

		e = Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "2Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));

		e = Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "2Todd", "Bauer", "TBFlow", "l33tc0d3r"));
	}
}
