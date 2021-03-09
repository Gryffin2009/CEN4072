package test;

import java.sql.SQLException;

import org.junit.jupiter.api.*;
import main.searchCustomer;

public class searchCustomerTest {

	static searchCustomer searchCustomer;
	
	@BeforeAll
	static void beforeAll() {
	}
	
	@BeforeEach
	void beforeEach() {
		searchCustomer = new searchCustomer();		
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}
	
	@Test
	@DisplayName("Validate Customer ID, valid ID")
	void testIdValid() {
		boolean isValid = searchCustomer.validateID("CS002");
		Assertions.assertTrue(isValid);
	}
	
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Incorrect prefix)")
	void testIdInvalidPrefix() {
		boolean isValid = searchCustomer.validateID("CL002");
		Assertions.assertFalse(isValid);		
	}
	
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Not enough digits)")
	void testIdInvalidNotEnoughDigits() {
		boolean isValid = searchCustomer.validateID("CS02");
		Assertions.assertFalse(isValid);		
	}
	
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Too many digits)")
	void testIdInvalidTooManyDigits() {
		boolean isValid = searchCustomer.validateID("CS0102");
		Assertions.assertFalse(isValid);		
	}
	
	@Test
	@DisplayName("Search by Customer ID, valid ID")
	void testSearchByValidID() throws SQLException {
		searchCustomer.searchByID("CS002");
	}
	
	@Test
	@DisplayName("Search by Customer ID, SQLException")
	void testSearchByInvalidISQLExceptiond() {
		
		Exception e = Assertions.assertThrows(SQLException.class, () ->
			searchCustomer.searchByID("CS999"));
		Assertions.assertEquals("Illegal operation on empty result set.", e.getMessage());
	}
}
