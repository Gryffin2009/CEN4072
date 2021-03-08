package test;

import java.io.IOException;

import org.junit.jupiter.api.*;

import main.AutoIDService;
import main.Flight;
import main.Customer.InvalidCustomerInputException;
import main.Flight.InvalidFlightInputException;

public class FlightTest {
	
	static String id;
	static String name;
	static String source;
	static String depart;
	static String date;
	static String depTime;
	static String arrTime;
	static String charge;
	
	Flight flight;
	
	@BeforeAll
	static void beforeAll() {
		id = AutoIDService.generateAutoID("customer", "CS");
		name = "Delta";
		source = "Uk";
		depart = "India";
		date = "2021-03-12";
		depTime = "8.00AM";
		arrTime = "5.00PM";
		charge = "25000";
	}
	
	@BeforeEach
	void beforeEach() throws InvalidFlightInputException {
		flight = new Flight(id, name, source, depart, date, depTime, arrTime, charge);
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Id, valid")
	void testIdValid() {
		Assertions.assertDoesNotThrow(() -> flight.setId("FO003"));
	}

	@Test
	@DisplayName("Id, invalid (Improper prefix)")
	void testIdInvalidPrefix() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
			flight.setId("FY003"));
	Assertions.assertEquals("ID must be in the format \"FO###\".", e.getMessage());		
	}

	@Test
	@DisplayName("Id, invalid (Too many digits)")
	void testIdInvalidTooManyDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		flight.setId("FO0003"));
	Assertions.assertEquals("ID must be in the format \"FO###\".", e.getMessage());		
	}

	@Test
	@DisplayName("Id, invalid (Not enough digits)")
	void testIdInvalidNotEnoughDigits() throws InvalidCustomerInputException, Exception {
		InvalidCustomerInputException e = Assertions.assertThrows(InvalidCustomerInputException.class, () ->
		flight.setId("FO03"));
	Assertions.assertEquals("ID must be in the format \"FO###\".", e.getMessage());		
	}

}
