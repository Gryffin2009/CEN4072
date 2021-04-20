package Integration;

import static org.mockito.Mockito.*;

import Model.Flight;
import Model.Flight.InvalidFlightInputException;
import Model.Ticket.InvalidTicketInputException;
import Service.FlightDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

  @Mock
  private ResultSet rs;

  private Flight flight;

  @BeforeEach
  public void setUp() throws Exception {
    flight = new Flight("FO001", "Delta", "India", "UK",
        "2019-06-14", "8.00AM", "10.00PM", "50000");
  }

  @Test
  public void addValidFlightToDB() throws SQLException {
    FlightDao fDao = new FlightDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    fDao.add(flight);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(8)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void updateFlightInDB() throws SQLException {
    FlightDao fDao = new FlightDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    fDao.update(flight);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(8)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

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
  }

  @Test
  public void getAllFlightsFromDB() throws InvalidFlightInputException, SQLException {
    FlightDao flightDao = new FlightDao();
    Flight[] allFlights = flightDao.getAll();
    Assertions.assertNotNull(allFlights);
  }
}
