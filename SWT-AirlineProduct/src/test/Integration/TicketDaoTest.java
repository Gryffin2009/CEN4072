package Integration;

import static org.mockito.Mockito.*;

import Model.Ticket;
import Model.Ticket.InvalidTicketInputException;
import Service.TicketDao;
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
public class TicketDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  @Mock
  private ResultSet rs;

  private Ticket ticket;

  @BeforeEach
  public void setUp() throws Exception {
    ticket = new Ticket("TO001", "FO001", "CS001",
        "Economy", "9000", "1", "2019-06-15");
  }

  @Test
  public void addValidTicketToDB() throws SQLException {
    TicketDao tDao = new TicketDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    tDao.add(ticket);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(7)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void updateTicketInDB() throws SQLException {
    TicketDao tDao = new TicketDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    tDao.update(ticket);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(7)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void getTicketFromDB() throws SQLException, InvalidTicketInputException {
    TicketDao ticketDao = new TicketDao();
    Ticket actualTicket = ticketDao.get("TO001");

    Assertions.assertEquals("FO003", actualTicket.getFlightId());
    Assertions.assertEquals("CS001", actualTicket.getCustId());
    Assertions.assertEquals("Economy", actualTicket.getFlightClass());
    Assertions.assertEquals("9000", actualTicket.getPrice());
    Assertions.assertEquals("1", actualTicket.getSeats());
    Assertions.assertEquals("2019-06-15", actualTicket.getDate());
  }

  @Test
  public void getAllTicketsFromDB() throws SQLException, InvalidTicketInputException {
    TicketDao ticketDao = new TicketDao();
    Ticket[] allFlights = ticketDao.getAll();
    Assertions.assertNotNull(allFlights);
  }
}
