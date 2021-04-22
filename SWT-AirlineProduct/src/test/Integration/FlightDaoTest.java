package Integration;

import static org.mockito.Mockito.*;

import Model.Flight;
import Model.Flight.InvalidFlightInputException;
import Model.Ticket.InvalidTicketInputException;
import Service.FlightDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FlightDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  private Flight flight;

  /*
  flight: A stub Flight object.
   */
  @BeforeEach
  public void setUp() throws Exception {
    flight = new Flight("FO001", "Delta", "India", "UK",
        "2019-06-14", "8.00AM", "10.00PM", "50000");
  }

  /*
  Test Case ID: #DB-5-1
  Requirement ID/Description: DB-5, The software shall store flights in a database.
  Purpose: Verify that the FlightDao class creates a PreparedStatement that the database will
    accept as a valid new row to the flight table.
  Test Setup: Create a stub Flight object.
  Test Strategy: Pass stub Flight object to a mock database connection and determine what database
    functions are called via a prepared statement that adds a new flight to the database.
  Input: Mock Database Connection, FlightDao class, Flight class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void addValidFlightToDB() throws SQLException {
    FlightDao fDao = new FlightDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    fDao.add(flight);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(8)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }


  /*
  Test Case ID: #DB-6-1
  Requirement ID/Description: DB-6, The software shall update flights in a database.
  Purpose: Verify that the FlightDao class creates a PreparedStatement that the database will
    accept as a modification to an existing row in the flight table.
  Test Setup: Create a stub Flight object.
  Test Strategy: Pass stub Flight object to a mock database connection and determine what database
    functions are called via a prepared statement that updates an existing flight in the database.
  Input: Mock Database Connection, FlightDao class, Flight class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void updateFlightInDB() throws SQLException {
    FlightDao fDao = new FlightDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    fDao.update(flight);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(8)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-4-1
  Requirement ID/Description: DB-4, The software shall read flights in a database.
  Purpose: Verify that the FlightDao class successfully retrieves flight information from a
    database and creates a Flight object from this information.
  Test Setup: Create FlightDao for processing database connection.
  Test Strategy: Query database for a specific flight and determine if that flight was returned.
  Input: FlightDao class, Flight class. Retrieves flight FO001 in the actual database.
  Expected Output: The flight #FO001 “JetBlue” is retrieved from the database and created into
    a Flight object.
   */
  @Test
  public void getFlightFromDB()
      throws SQLException, InvalidFlightInputException, InvalidTicketInputException {


    FlightDao flightDao = new FlightDao();
    Flight actualFlight = flightDao.get("FO001");

    Assertions.assertEquals("JetBlue", actualFlight.getName());
    Assertions.assertEquals("India", actualFlight.getSource());
    Assertions.assertEquals("Uk", actualFlight.getDepart());
    Assertions.assertEquals("2019-06-14", actualFlight.getDate());
    Assertions.assertEquals("8.00AM", actualFlight.getDepTime());
    Assertions.assertEquals("10.00PM", actualFlight.getArrTime());
    Assertions.assertEquals("50000", actualFlight.getCharge());

    System.out.println("Flight #" + actualFlight.getId() + ", " + actualFlight.getName());
  }

  /*
  Test Case ID: #DB-4-2
  Requirement ID/Description: DB-4, The software shall read flights in a database.
  Purpose: Verify that the FlightDao class successfully retrieves flight information for all
    flights from a database and creates an array of Flight objects from this information.
  Test Setup: Create FlightDao for processing database connection.
  Test Strategy: Query database for all flights and determine if every flight was returned.
  Input: FlightDao class, Flight class. Retrieves an array of all flights in the actual database.
  Expected Output: An array of Flights containing every flight and all their information.
   */
  @Test
  public void getAllFlightsFromDB() throws InvalidFlightInputException, SQLException {
    FlightDao flightDao = new FlightDao();
    Flight[] allFlights = flightDao.getAll();
    Assertions.assertNotNull(allFlights);
    for (Flight flight : allFlights) {
      System.out.println("Flight " + flight.getId() + ", name: " + flight.getName() + ".");
    }
  }
}
