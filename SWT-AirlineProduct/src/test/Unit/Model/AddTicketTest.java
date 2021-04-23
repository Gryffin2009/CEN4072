package Unit.Model;

import Model.Ticket;
import java.io.IOException;
import org.junit.jupiter.api.*;
import Model.Ticket.InvalidTicketInputException;
import Service.AutoIDService;

public class AddTicketTest {

	Ticket ticket;
	
	@BeforeEach
	void beforeEach() throws IOException, InvalidTicketInputException {

		// Before each tests run, reset values for the initial AddTicket object to be
		// created with.
		// This is necessary to test whether changing any individual properties will
		// throw exceptions.
		// This also facilitates input validation, as each setter in AddTicket contains
		// validation methods.
		String id = AutoIDService.generateAutoID("ticket", "TO");
		String flightid = "";
		String custid = "";
		String flightclass = "Business";
		String price = "100";
		String seats = "1";
		String date = "2021-04-22";
		// Create a fresh AddTicket to work with for every test. This ensures all tests
		// are working from the same
		// initial values, which ensures no test results will affect other tests.
		ticket = new Ticket(id, flightid, custid, flightclass, price, seats, date);
	}

	/*
	 * Test Case ID: TC-T-8
	 * Requirement ID/Description: T-5 Tickets shall include a valid number of seats. A valid number
	 * 		of seats is between 0 and 10, exclusively.
	 * Purpose: To test a valid seat value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: equivalence partitioning: ivalid <=0, valid 1-9, invalid >9
	 * Input: int: 5
	 * Expected Output: accepted seat value.
	 */
	@Test
	@DisplayName("Valid seats")
	void testValidSeat() {
		Assertions.assertDoesNotThrow(() -> ticket.setNumSeats(5));
	}

	/*
	 * Test Case ID: TC-T-9
	 * Requirement ID/Description: T-5 Tickets shall include a valid number of seats. A valid number
	 * 		of seats is between 0 and 10, exclusively.
	 * Purpose: To test a invalid seat value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: equivalence partitioning: invalid <=0, valid 1-9, invalid >9
	 * Input: int: 50
	 * Expected Output: Seat value not accepted.
	 */
	@Test
	@DisplayName("Invalid seats, too many")
	void testSeatsInvalidNoNumbers() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, 
				() -> ticket.setNumSeats(50));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}

	// Tries to pass an invalid street address to the Address class.
	// Only numbers
	/*
	 * Test Case ID: TC-T-10
	 * Requirement ID/Description: T-5 Tickets shall include a valid number of seats. A valid number
	 * 		of seats is between 0 and 10, exclusively.
	 * Purpose: To test a invalid seat value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: equivalence partitioning: invalid <=0, valid 1-9, invalid >9
	 * Input: int:  0
	 * Expected Output: Seat value not accepted.
	 */
	@Test
	@DisplayName("Invalid seats, 0")
	void testInvalidSeat0() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setNumSeats(0));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}

	/*
	 * Test Case ID: TC-T-13
	 * Requirement ID/Description: T-3 Tickets shall have a price. The price shall consist of numerical input.
	 * Purpose: To test a valid date value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: positive testing
	 * Input:  2021-04-22
	 * Expected Output: accepted date value.
	 */
	@Test
	@DisplayName("Valid Date, proper format")
	void testValidDate() throws InvalidTicketInputException {
		Assertions.assertDoesNotThrow(() -> ticket.setDate("2021-04-22"));
	}

	/*
	 * Test Case ID: TC-T-14
	 * Requirement ID/Description: T-3 Tickets shall have a price. The price shall consist of numerical input.
	 * Purpose: To test a invalid date value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: positive testing
	 * Input: "HELLO"
	 * Expected Output: Date value not accepted.
	 */
	@Test
	@DisplayName("Invalid Date, improper format")
	void testInvalidDate() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setDate("HELLO"));
		Assertions.assertEquals("Invalid date. Improper format.", e.getMessage());
	}

	/*
	 * Test Case ID: TC-T-5
	 * Requirement ID/Description: T-3 Tickets shall have a price. The price shall consist of numerical input.
	 * Purpose: To test a valid price value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: positive testing
	 * Input: int:  100
	 * Expected Output: Seat value not accepted.
	 */
	@Test
	@DisplayName("Valid price, numeric")
	void testValidPrice() throws InvalidTicketInputException {
		Assertions.assertDoesNotThrow(() -> ticket.setPrice("100"));
	}

	/*
	 * Test Case ID: TC-T-6
	 * Requirement ID/Description: T-3 Tickets shall have a price. The price shall consist of numerical input.
	 * Purpose: To test a valid price value.
	 * Test setup: Ticket object with the following values:
	 * 		String id = AutoIDService.generateAutoID("ticket", "TO");
	 *		String flightid = "";
	 *		String custid = "";
	 * 		String flightclass = "Business";
	 * 		String price = "100";
	 *		String seats = "1";
	 *		String date = "2021-04-22";
	 * Test Strategy: positive testing
	 * Input: int:  100
	 * Expected Output: Seat value not accepted.
	 */
	@Test
	@DisplayName("Invalid price, alphanumeric")
	void testInvalidPrice() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setPrice("H3LL0"));
		Assertions.assertEquals("Invalid price. Numeric input only.", e.getMessage());
	}




}
