package Unit.View;

import View.TicketReport;
import org.junit.jupiter.api.*;

public class ticketReportTest {
	
	static TicketReport ticketReport;
	
	@BeforeAll
	static void beforeAll() {	
	}
	
	@BeforeEach
	void beforeEach() {
		ticketReport = new TicketReport();
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	void testLoadAll() {
		ticketReport.LoadData();
	}
	
	
}
