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
        String source = "UK";
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
    void testInvalidIdTooManyDigits() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setId("FO0003"));
        Assertions.assertEquals("Flight ID must be in the format \"FO###\".", e.getMessage());
    }

    // Tries to pass an invalid Id to the flight class by using an Id with too few trailing digits.
    @Test
    @DisplayName("Id, invalid (Not enough digits)")
    void testInvalidIdNotEnoughDigits() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setId("FO03"));
        Assertions.assertEquals("Flight ID must be in the format \"FO###\".", e.getMessage());
    }

    // Tries to update a flight in the database.
    @Test
    @DisplayName("Update in database")
    void testUpdateInDatabase() throws Exception {
        Assertions.assertDoesNotThrow(() -> flight.updateInDatabase());
    }

    @Test
    @DisplayName("charge, invalid (alphabetic)")
    void testInvalidCharge() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setCharge("HELLO"));
        Assertions.assertEquals("Flight charge must be numeric characters only.", e.getMessage());
    }

    @Test
    @DisplayName("arrival time, invalid (improper format)")
    void testArrTimeInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setArrTime("HELLO"));
        Assertions.assertEquals("Flight arrival time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM..", e.getMessage());
    }

    @Test
    @DisplayName("departure time, invalid (improper format)")
    void testDepTimeInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDepTime("HELLO"));
        Assertions.assertEquals("Flight departure time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM.", e.getMessage());
    }


    @Test
    @DisplayName("date, invalid (improper format)")
    void testDateInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDate("HELLO"));
        Assertions.assertEquals("Flight date must be in the format \"YYYY-MM-DD\".", e.getMessage());
    }


    @Test
    @DisplayName("departure location, invalid option")
    void testDepartInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDepart("HELLO"));
        Assertions.assertEquals("Flight departure country must be: India, Srilanka, Uk, USA, Canada, or China.", e.getMessage());
    }


    @Test
    @DisplayName("source location, invalid option")
    void testSourceInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setSource("HELLO"));
        Assertions.assertEquals("Flight source country must be: India, Srilanka, Uk, USA, Canada, or China.", e.getMessage());
    }

    @Test
    @DisplayName("name, invalid (alphanumeric)")
    void testNameInvalidNumbers() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setName("H123123123"));
        Assertions.assertEquals("Flight name must contain alphabetic characters only.", e.getMessage());
    }

}
