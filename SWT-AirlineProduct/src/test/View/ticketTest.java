package test.View;

import java.util.Vector;

import org.junit.jupiter.api.*;

import main.View.ticket;

public class ticketTest {
	
	static ticket ticket;
	
	@BeforeAll
	static void beforeAll() {	
	}
	
	@BeforeEach
	// Before each test, create a new ticket
	void beforeEach() {
		ticket = new ticket();	
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Change price based on number of seats")
	// Testing calculation to ensure output matches the expected output. 
	void testCalcPriceTotal() {
		int total = ticket.calcPriceTotal(50000, 3);
		// Checks actual output (total) against expected output (150000)
		Assertions.assertEquals(150000, total, "The price of the seats should be 150,000");
	}
	
	@Test
	@DisplayName("User can only buy between 1 and 10 seats inclusive")
	// Testing the boundaries of the equivalence classes.
	void testNumSeatsBounds() {
		
		// Testing below lower bound.
		boolean result = ticket.validateNumSeats(-1);
		Assertions.assertFalse(result);
		
		// Testing the lower bound
		result = ticket.validateNumSeats(0);
		Assertions.assertFalse(result);
		
		// Testing above the lower bound
		result = ticket.validateNumSeats(1);
		Assertions.assertTrue(result);
		
		result = ticket.validateNumSeats(9);
		Assertions.assertTrue(result);
		
		// Testing the upper bound
		result = ticket.validateNumSeats(10);
		Assertions.assertFalse(result);
		
		//Testing above the upper bound
		result = ticket.validateNumSeats(11);
		Assertions.assertFalse(result);
	}
	
	@Test
	@DisplayName("Retrieve list of flights from India to Uk")
	// Ensures the program properly fetches the list of flights from the database.
	void testRetrieveFlights() {
		Vector<Vector<String>> flights = ticket.createFlightList("India", "Uk");
		
		Assertions.assertNotEquals(null, flights);
	}
}
