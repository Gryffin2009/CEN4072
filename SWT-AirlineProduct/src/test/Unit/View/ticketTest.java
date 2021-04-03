package Unit.View;

import View.AddTicket;
import java.util.Vector;

import org.junit.jupiter.api.*;

public class ticketTest {
	
	static AddTicket addTicket;
	
	@BeforeEach
	// Before each test, create a new AddTicket
	void beforeEach() {
		addTicket = new AddTicket();
	}
	
	@Test
	@DisplayName("Retrieve list of flights from India to Uk")
	// Ensures the program properly fetches the list of flights from the database.
	void testRetrieveFlights() {
		Vector<Vector<String>> flights = addTicket.createFlightList("India", "Uk");
		Assertions.assertNotEquals(null, flights);
	}
}
