package test.Model;

import java.io.IOException;

import org.junit.jupiter.api.*;

import main.Model.Customer;
import main.Model.Customer.InvalidCustomerInputException;
import main.Model.Ticket;
import main.Service.AutoIDService;

public class TicketTest {

	Ticket ticket;

	@BeforeAll
	static void beforeAll() {

	}

	@BeforeEach
	void beforeEach() throws InvalidCustomerInputException, IOException {

		// Before each tests run, reset values for the initial Customer object to be
		// created with.
		// This is necessary to test whether changing any individual properties will
		// throw exceptions.
		// This also facilitates input validation, as each setter in Customer contains
		// validation methods.
		String id = AutoIDService.generateAutoID("customer", "CS");
		String firstname = "Todd";
		String lastname = "Bauer";
		String nic = "1293874532";
		String passport = "2398732423948";
		String address = "123 S. Washington St.";
		String dob = "1985-03-12";
		String gender = "Male";
		String contact = "1234567";
		String photoPath = "src/test/media/CustomerPicture.png";
		// Create a fresh Customer to work with for every test. This ensures all tests
		// are working from the same
		// initial values, which ensures no test results will affect other tests.
		ticket = new Ticket(ticketid, flightid, custid, flightclass, price, seats, date);
	}

	@AfterEach
	void afterEach() {

	}

	@AfterAll
	static void afterAll() {

	}
}
