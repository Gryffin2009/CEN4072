package Unit.Model;

import Model.Flight;
import org.junit.jupiter.api.*;

import Model.Flight.InvalidFlightInputException;
import Service.AutoIDService;

public class FlightTest {
    Flight flight;

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

    /**
     * Test Case ID: F-8-1
     * Requirement: F-8 Flights shall be stored in the database and the system shall be able to update them.
     * Purpose: Tries to update a flight in the database.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: testUpdateInDatabase is called and should not throw an exception.
     * Input: a valid flight object
     * Expected output: the method should not throw an exception
     **/
    @Test
    @DisplayName("Update in database")
    void testUpdateInDatabase() throws Exception {
        Assertions.assertDoesNotThrow(() -> flight.updateInDatabase());
    }

    /**
     * Test Case ID: F-7-1
     * Requirement: F-7 A Flight must have a valid charge. A valid charge shall be a numeric only string.
     * Purpose: The method attempts to set an invalid charge on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setCharge("100") is called and should not throw an exception.
     * Input: "100"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("charge, valid (numeric)")
    void testValidCharge() throws InvalidFlightInputException {
        Assertions.assertDoesNotThrow(() -> flight.setCharge("100"));
    }

    /**
     * Test Case ID: F-7-2
     * Requirement: F-7 A Flight must have a valid charge. A valid charge shall be a numeric only string.
     * Purpose: The method attempts to set an invalid charge on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setCharge("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("charge, invalid (alphabetic)")
    void testInvalidCharge() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setCharge("HELLO"));
        Assertions.assertEquals("Flight charge must be numeric characters only.", e.getMessage());
    }

    /**
     * Test Case ID: F-6-1
     * Requirement: F-6 A Flight must have a valid arrival time. A valid departure time shall be in the format #.##AM or #.##PM.
     * Purpose: The method attempts to set an invalid departure time on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setArrTime("8.00AM") is called and should not throw an exception.
     * Input: "8.00AM"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("arrival time, valid (proper format)")
    void testArrTimeValidFormat() {
        Assertions.assertDoesNotThrow(() -> flight.setArrTime("8.00AM"));
    }
    /**
     * Test Case ID: F-6-2
     * Requirement: F-6 A Flight must have a valid arrival time. A valid departure time shall be in the format #.##AM or #.##PM.
     * Purpose: The method attempts to set an invalid departure time on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setArrTime("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("arrival time, invalid (improper format)")
    void testArrTimeInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setArrTime("HELLO"));
        Assertions.assertEquals("Flight arrival time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM..", e.getMessage());
    }


    /**
     * Test Case ID: F-5-1
     * Requirement: F-5 A Flight must have a valid departure time. A valid departure time shall be in the format #.##AM or #.##PM.
     * Purpose: The method attempts to set an invalid departure time on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDepTime("8.00AM") is called and should not throw an exception.
     * Input: "8.00AM"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("departure time, valid (proper format)")
    void testDepTimeValidFormat() {
        Assertions.assertDoesNotThrow(() -> flight.setDepTime("8.00AM"));
    }

    /**
     * Test Case ID: F-5-1
     * Requirement: F-5 A Flight must have a valid departure time. A valid departure time shall be in the format #.##AM or #.##PM.
     * Purpose: The method attempts to set an invalid departure time on a flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDepTime("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("departure time, invalid (improper format)")
    void testDepTimeInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDepTime("HELLO"));
        Assertions.assertEquals("Flight departure time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM.", e.getMessage());
    }

    /**
     * Test Case ID: F-4-1
     * Requirement: F-4 A Flight must have a valid date of flight. A valid date shall be in the format YYYY-MM-DD.
     * Purpose: The method attempts to set an invalid date for the flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDate("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("date, valid (proper format)")
    void testDateValidFormat() {
        Assertions.assertDoesNotThrow(() -> flight.setDate("2021-04-22"));
    }

    /**
     * Test Case ID: F-4-2
     * Requirement: F-4 A Flight must have a valid date of flight. A valid date shall be in the format YYYY-MM-DD.
     * Purpose: The method attempts to set an invalid date for the flight.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDate("2021-04-22") is called and should not throw an exception.
     * Input: "2021-04-22"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("date, invalid (improper format)")
    void testDateInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDate("HELLO"));
        Assertions.assertEquals("Flight date must be in the format \"YYYY-MM-DD\".", e.getMessage());
    }

    /**
     * Test Case ID: F-3-1
     * Requirement: F-3 A Flight must have a valid departure location. A valid source location is only [India, Srilanka, UK, USA, Canada, or China.]
     * Purpose: The method attempts to pass an invalid departure location to setDepart.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDepart("India") is called and should not throw an exception.
     * Input: "India"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("departure location, valid option")
    void testDepartValidFormat() {
        Assertions.assertDoesNotThrow(() -> flight.setDepart("India"));
    }

    /**
     * Test Case ID: F-3-2
     * Requirement: F-3 A Flight must have a valid departure location. A valid source location is only [India, Srilanka, UK, USA, Canada, or China.]
     * Purpose: The method attempts to pass an invalid departure location to setDepart.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setDepart("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("departure location, invalid option")
    void testDepartInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setDepart("HELLO"));
        Assertions.assertEquals("Flight departure country must be: India, Srilanka, Uk, USA, Canada, or China.", e.getMessage());
    }

    /**
     * Test Case ID: F-2-2
     * Requirement: F-2 A Flight must have a valid source location. A valid source location is only [India, Srilanka, UK, USA, Canada, or China.]
     * Purpose: The method attempts to pass an invalid departure location to setSource.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setSource("HELLO") is called and should throw an exception.
     * Input: "HELLO"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("source location, invalid option")
    void testSourceInvalidFormat() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setSource("HELLO"));
        Assertions.assertEquals("Flight source country must be: India, Srilanka, Uk, USA, Canada, or China.", e.getMessage());
    }

    /**
     * Test Case ID: F-2-1
     * Requirement: A Flight must have a valid source location. A valid source location is only [India, Srilanka, UK, USA, Canada, or China.]
     * Purpose: The method attempts to pass an invalid departure location to setSource.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setSource("India") is called and should not throw an exception.
     * Input: "India"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("source location, valid option")
    void testSourceValidFormat() {
        Assertions.assertDoesNotThrow(() -> flight.setSource("India"));
    }

    /**
     * Test Case ID: F-1-1
     * Requirement: A Flight must have a valid name. A valid name consists of only letters.
     * Purpose: The method calls setName and passes an invalid name.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setName("HELLO") is called and should not throw an exception.
     * Input: "HELLO"
     * Expected output: the method should not throw an exception.
     **/
    @Test
    @DisplayName("name, valid (alphabetic)")
    void testNameValid() {
        Assertions.assertDoesNotThrow(() -> flight.setName("HELLO"));
    }

    /**
     * Test Case ID: F-1-2
     * Requirement: A Flight must have a valid name. A valid name consists of only letters.
     * Purpose: The method calls setName and passes an invalid name.
     * Test setup: A flight object is created beforehand.
     * Test Strategy: flight.setName("H123123123") is called and should throw an exception.
     * Input: "H123123123"
     * Expected output: the method should throw 'InvalidFlightInputException'
     **/
    @Test
    @DisplayName("name, invalid (alphanumeric)")
    void testNameInvalidNumbers() throws InvalidFlightInputException {
        InvalidFlightInputException e = Assertions.assertThrows(InvalidFlightInputException.class, () ->
                flight.setName("H123123123"));
        Assertions.assertEquals("Flight name must contain alphabetic characters only.", e.getMessage());
    }

}
