package Unit.Model;

import Model.Flight;
import org.junit.jupiter.api.*;

import Model.Flight.InvalidFlightInputException;
import Service.AutoIDService;

public class FlightTest {


    Flight flight;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() throws InvalidFlightInputException {
        // Before any tests run, create values for the initial Flight object to be created with.
        // This is necessary to test whether changing any individual properties will throw exceptions.
        // This also facilitates input validation, as each setter in Customer contains validation methods.
        String id = AutoIDService.generateAutoID("flight", "FO");
        String name = "Delta";
        String source = "Uk";
        String depart = "India";
        String date = "2021-03-12";
        String depTime = "8.00AM";
        String arrTime = "5.00PM";
        String charge = "25000";

        // Create a fresh Flight to work with for every test. This ensures all tests are working from the same
        // initial values, which ensures no test results will affect other tests.
        flight = new Flight(id, name, source, depart, date, depTime, arrTime, charge);
    }

    @AfterEach
    void afterEach() {

    }

    @AfterAll
    static void afterAll() {

    }

    // Tries to pass a valid Flight Id to the Flight class.
    @Test
    @DisplayName("Id, valid")
    void testIdValid() {
        Assertions.assertDoesNotThrow(() -> flight.setId("FO003"));
    }

    // Tries to pass an invalid Id to the flight class by using an Id with an incorrect prefix.
    @Test
    @DisplayName("Id, invalid (Improper prefix)")
    void testIdInvalidPrefix() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setId("FY003"));
        Assertions.assertEquals("Flight ID must be in the format \"FO###\".", e.getMessage());
    }

    // Tries to pass an invalid Id to the flight class by using an Id with too many trailing digits.
    @Test
    @DisplayName("Id, invalid (Too many digits)")
    void testIdInvalidTooManyDigits() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setId("FO0003"));
        Assertions.assertEquals("Flight ID must be in the format \"FO###\".", e.getMessage());
    }

    // Tries to pass an invalid Id to the flight class by using an Id with too few trailing digits.
    @Test
    @DisplayName("Id, invalid (Not enough digits)")
    void testIdInvalidNotEnoughDigits() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setId("FO03"));
        Assertions.assertEquals("Flight ID must be in the format \"FO###\".", e.getMessage());
    }

}
