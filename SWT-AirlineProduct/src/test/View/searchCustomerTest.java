package test.View;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

import main.View.searchCustomer;

public class searchCustomerTest {

	static searchCustomer searchCustomer;
	
	@BeforeAll
	static void beforeAll() {
	}
	
	@BeforeEach
	void beforeEach() {
		searchCustomer = new searchCustomer();		
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
