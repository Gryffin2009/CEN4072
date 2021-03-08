package test;

import org.junit.jupiter.api.*;
import main.AutoIDService;
import main.userCreation;

public class AutoIDServiceTest {
	
	// TODO make test table
	private String TEST_TABLE = "test"; 
	
	@BeforeAll
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
	static void afterAll() {
		// TODO DROP TEST_TABLE
	}
	
	@Test
	void testAutoID() {
		
		String first = AutoIDService.generateAutoID(TEST_TABLE, "UO");
		System.out.println(first);
		Assertions.assertEquals(first, "UO001");
		
		String second = AutoIDService.generateAutoID(TEST_TABLE, "UO");
		Assertions.assertEquals(second, "UO002");
		
	}
	
}
