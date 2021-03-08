package test;

import org.junit.jupiter.api.*;
import main.ticket;

public class TestTicket {
	
	static ticket thisTicket;
	
	@BeforeAll
	static void beforeAll() {
		thisTicket = new ticket();		
	}
	
	@BeforeEach
	void beforeEach() {
		
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Change price based on number of seats")
	public void testCalcPriceTotal() {
		int total = thisTicket.calcPriceTotal(50000, 3);
		Assertions.assertEquals(150000, total);
	}
	
	
	
}
