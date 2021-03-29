package View;

import org.junit.jupiter.api.*;

import View.ticketreport;

public class ticketReportTest {
	
	static ticketreport ticketReport;
	
	@BeforeAll
	static void beforeAll() {	
	}
	
	@BeforeEach
	void beforeEach() {
		ticketReport = new ticketreport();	
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Change price based on number of seats")
	void testUndefined() {
	}
	
	
}
