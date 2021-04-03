package Unit.View;

import View.SearchCustomer;
import java.sql.SQLException;

import org.junit.jupiter.api.*;

public class searchCustomerTest {

	private static SearchCustomer searchCustomer;
	
	@BeforeAll
	static void beforeAll() {
	}
	
	@BeforeEach
	void beforeEach() {
		searchCustomer = new SearchCustomer();
	}
	
	// Tries to pass a valid customer Id to the customer search function.
	// This ensures that a valid customer Id can retrieve customer information
	// from the database.
	@Test
	@DisplayName("Search by Customer ID, valid ID")
	void testSearchByValidID() throws SQLException {
		// TODO Fix test according to new customer search
		//searchCustomer.searchByID("CS002");
	}

	// Tries to pass a valid customer Id to the customer search function.
	// This ensures that an invalid customer Id will not be found in the
	// database, which will throw a SQL exception.
	@Test
	@DisplayName("Search by Customer ID, SQLException")
	void testSearchByInvalidISQLExceptiond() {

		// TODO Fix test according to new customer search
		//Exception e = Assertions.assertThrows(SQLException.class, () ->
		//	searchCustomer.searchByID("CS999"));
		//Assertions.assertEquals("Illegal operation on empty result set.", e.getMessage());
	}
}
