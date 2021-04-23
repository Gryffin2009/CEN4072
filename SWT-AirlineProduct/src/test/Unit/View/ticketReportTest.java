package Unit.View;

import View.TicketReport;
import org.junit.jupiter.api.*;

public class ticketReportTest {
	
	static TicketReport ticketReport;

	@BeforeEach
	void beforeEach() {
		ticketReport = new TicketReport();
	}

	/**
	 * Test Case ID: DB-7
	 * Requirement: The software shall read booked tickets from a database.
	 * Purpose: The method attempts to read tickets from the database.
	 * Test setup: a ticketReport object is created.
	 * Test Strategy: ticketReport.LoadData() is called
	 * Input: None.
	 * Expected output: the method should not throw an exception.
	 **/
	@Test
	void testLoadAll() {
		ticketReport.LoadData();
	}
	
}
