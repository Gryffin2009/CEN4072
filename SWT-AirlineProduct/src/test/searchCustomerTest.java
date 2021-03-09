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

	// Tries to pass a valid Customer Id to function that validates customer Ids.
	@Test
	@DisplayName("Validate Customer ID, valid ID")
	void testIdValid() {
		boolean isValid = searchCustomer.validateID("CS002");
		Assertions.assertTrue(isValid);
	}

	// Tries to pass an invalid customer Id to function that validates
	// customer Ids by using an Id with an incorrect prefix.
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Incorrect prefix)")
	void testIdInvalidPrefix() {
		boolean isValid = searchCustomer.validateID("CL002");
		Assertions.assertFalse(isValid);		
	}

	// Tries to pass an invalid customer Id to function that validates
	// customer Ids by using an Id with too many trailing digits.
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Not enough digits)")
	void testIdInvalidNotEnoughDigits() {
		boolean isValid = searchCustomer.validateID("CS02");
		Assertions.assertFalse(isValid);		
	}

	// Tries to pass an invalid customer Id to function that validates
	// customer Ids by using an Id with too few trailing digits.
	@Test
	@DisplayName("Validate Customer ID, invalid ID (Too many digits)")
	void testIdInvalidTooManyDigits() {
		boolean isValid = searchCustomer.validateID("CS0102");
		Assertions.assertFalse(isValid);		
	}
	
	// Tries to pass a valid customer Id to the customer search function.
	// This ensures that a valid customer Id can retrieve customer information
	// from the database.
	@Test
	@DisplayName("Search by Customer ID, valid ID")
	void testSearchByValidID() throws SQLException {
		searchCustomer.searchByID("CS002");
	}

	// Tries to pass a valid customer Id to the customer search function.
	// This ensures that an invalid customer Id will not be found in the
	// database, which will throw a SQL exception.
	@Test
	@DisplayName("Search by Customer ID, SQLException")
	void testSearchByInvalidISQLExceptiond() {
		
		Exception e = Assertions.assertThrows(SQLException.class, () ->
			searchCustomer.searchByID("CS999"));
		Assertions.assertEquals("Illegal operation on empty result set.", e.getMessage());
	}
}
