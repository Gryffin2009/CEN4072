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
	void testSeatsInvalidNoNumbers() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, 
				() -> ticket.setNumSeats(50));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}

	// Tries to pass an invalid street address to the Address class.
	// Only numbers
	@Test
	@DisplayName("Invalid seats, 0")
	void testPassportInvalidOnlyNumbers() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setNumSeats(0));
		Assertions.assertEquals("Invalid Number of Seats", e.getMessage());
	}

	@Test
	@DisplayName("Valid Date, proper format")
	void testValidDate() throws InvalidTicketInputException {
		Assertions.assertDoesNotThrow(() -> ticket.setDate("2021-04-22"));
	}

	@Test
	@DisplayName("Invalid Date, improper format")
	void testInvalidDate() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setDate("HELLO"));
		Assertions.assertEquals("Invalid date. Improper format.", e.getMessage());
	}

	@Test
	@DisplayName("Valid price, numeric")
	void testValidPrice() throws InvalidTicketInputException {
		Assertions.assertDoesNotThrow(() -> ticket.setPrice("100"));
	}

	@Test
	@DisplayName("Invalid price, alphanumeric")
	void testInvalidPrice() throws InvalidTicketInputException {
		Exception e = Assertions.assertThrows(InvalidTicketInputException.class, () -> ticket.setPrice("H3LL0"));
		Assertions.assertEquals("Invalid price. Numeric input only.", e.getMessage());
	}




}
