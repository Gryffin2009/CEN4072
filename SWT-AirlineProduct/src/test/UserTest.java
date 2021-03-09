package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

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
		
		
		Assertions.assertThrows(InvalidUserInputException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				User user = new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!");
			}
		});
		
		Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));
		
		Assertions.assertDoesNotThrow(() -> new User(id, "Todd", "Bauer", "TBFlow", "l33tc0d3r"));

		Assertions.assertThrows(InvalidUserInputException.class, () ->
			new User(id, "2Todd", "Bauer63", "TB!Flo746", "sdjh834#!!"));

		Assertions.assertThrows(InvalidUserInputException.class, () -> new User(id, "2Todd", "Bauer", "TBFlow", "l33tc0d3r"));
	}
}
