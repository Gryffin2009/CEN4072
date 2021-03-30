package Model;

import java.io.IOException;
import org.junit.jupiter.api.*;

import Model.Ticket;
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
		String id = AutoIDService.generateAutoID("AddTicket", "TO");
		String flightid = "";
		String custid = "";
		String flightclass = "business";
		String price = "100";
		String seats = "1";
		String date = "2021-04-22";
		// Create a fresh AddTicket to work with for every test. This ensures all tests
		// are working from the same
		// initial values, which ensures no test results will affect other tests.
		ticket = new Ticket(id, flightid, custid, flightclass, price, seats, date);
	}

	// Tries to pass a valid street address to the Address class.
	@Test
	@DisplayName("Valid seats")
	void testPassportValid() {
		Assertions.assertDoesNotThrow(() -> ticket.setNumSeats(5));
	}

	// Tries to pass an invalid street address to the Address class.
	// No numbers
	@Test
	@DisplayName("Invalid seats, too many")
	void testPassportInvalidNoNumbers() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, 
				() -> ticket.setNumSeats(50));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}

	// Tries to pass an invalid street address to the Address class.
	// Only numbers
	@Test
	@DisplayName("Invalid seats, 0")
	void testPassportInvalidOnlyNumbers() throws InvalidTicketInputException, Exception {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setNumSeats(0));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}
	
}
