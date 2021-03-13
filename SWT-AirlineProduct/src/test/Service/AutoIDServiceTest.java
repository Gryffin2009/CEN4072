package test.Service;

import org.junit.jupiter.api.*;

import main.Service.AutoIDService;
import main.View.userCreation;

public class AutoIDServiceTest {
	
	// TODO make test table
	private String TEST_TABLE = "flight"; 
	
	@BeforeAll
	// Delete table prior to testing.
	static void beforeAll() {
		// TODO DROP TEST_TABLE
	}
	
	@BeforeEach
	void beforeEach() {
		
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	// Delete table after all testing is done.
	static void afterAll() {
		// TODO DROP TEST_TABLE
	}
	
	@Test
	void testAutoID() {
		// Testing AutoIDService, generated ID should be UO001
		String first = AutoIDService.generateAutoID(TEST_TABLE, "UO");
		Assertions.assertEquals(first, "UO001");
	
		// Testing AutoIDService, generated ID should be UO002
		String second = AutoIDService.generateAutoID(TEST_TABLE, "UO");
		Assertions.assertEquals(second, "UO002");
		
	}
	
}
