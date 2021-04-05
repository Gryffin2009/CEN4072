package Integration;

import static org.mockito.Mockito.*;

import Model.Flight;
import Service.FlightDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    flight = new Flight("FO001", "Delta", "India", "Uk",
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
}
