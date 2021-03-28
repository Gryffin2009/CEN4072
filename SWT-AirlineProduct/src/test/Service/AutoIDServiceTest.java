package test.Service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.Service.AutoIDService;

public class AutoIDServiceTest {
	
	// TODO make test table
	private String TEST_TABLE = "flight"; 
	
	@ParameterizedTest
	@ValueSource(strings = {"UO", "TO", "AT"})
	void testAutoID(String prefix) {
		String id = AutoIDService.generateAutoID(TEST_TABLE, prefix);
		Assertions.assertTrue(id.contains(prefix));
	}
	
	@Test
	void testAutoIDInvalid() {
		String id = AutoIDService.generateAutoID(TEST_TABLE, "");
		Assertions.assertEquals(id, null);
	}
	
}