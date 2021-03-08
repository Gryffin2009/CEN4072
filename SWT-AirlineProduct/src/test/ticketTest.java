package test;

import java.util.Vector;

import org.junit.jupiter.api.*;
import main.ticket;

public class ticketTest {
	
	static ticket ticket;
	
	@BeforeAll
	static void beforeAll() {	
	}
	
	@BeforeEach
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
	void testCalcPriceTotal() {
		int total = ticket.calcPriceTotal(50000, 3);
		Assertions.assertEquals(150000, total, "The price of the seats should be 150,000");
	}
	
	@Test
	@DisplayName("Retrieve list of flights from India to Uk")
	void testRetrieveFlights() {
		Vector<Vector<String>> flights = ticket.createFlightList("India", "Uk");
		
		Assertions.assertNotEquals(null, flights);
	}
}
