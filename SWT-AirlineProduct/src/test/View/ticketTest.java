package View;

import java.util.Vector;

import org.junit.jupiter.api.*;

import View.ticket;

public class ticketTest {
	
	static ticket ticket;
	
	@BeforeEach
	// Before each test, create a new ticket
	void beforeEach() {
		ticket = new ticket();	
	}
	
	@Test
	@DisplayName("Retrieve list of flights from India to Uk")
	// Ensures the program properly fetches the list of flights from the database.
	void testRetrieveFlights() {
		Vector<Vector<String>> flights = ticket.createFlightList("India", "Uk");
		Assertions.assertNotEquals(null, flights);
	}
}
