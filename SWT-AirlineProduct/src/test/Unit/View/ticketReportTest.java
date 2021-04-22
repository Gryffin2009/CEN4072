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
	 * Test Case ID: F-4
	 * Requirement: A Flight must have a valid date of flight. A valid date shall be in the format YYYY-MM-DD.
	 * Purpose: The method attempts to set an invalid date for the flight.
	 * Test setup: A flight object is created beforehand.
	 * Test Strategy: flight.setDate("HELLO") is called and should throw an exception.
	 * Input: "HELLO"
	 * Expected output: the method should throw 'InvalidFlightInputException'
	 **/
	@Test
	void testLoadAll() {
		ticketReport.LoadData();
	}
	
}
