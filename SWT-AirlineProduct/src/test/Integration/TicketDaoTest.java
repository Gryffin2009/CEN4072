package Integration;

import static org.mockito.Mockito.*;

import Model.Ticket;
import Model.Ticket.InvalidTicketInputException;
import Service.TicketDao;
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
public class TicketDaoTest {

  @Mock
  private Connection con;

  @Mock
  private PreparedStatement stmt;

  private Ticket ticket;

  /*
  ticket: A stub Ticket object.
   */
  @BeforeEach
  public void setUp() throws Exception {
    ticket = new Ticket("TO001", "FO001", "CS001",
        "Economy", "9000", "1", "2019-06-15");
  }

  /*
  Test Case ID: #DB-8-1
  Requirement ID/Description: DB-8, The software shall store tickets in a database.
  Purpose: Verify that the TicketDao class creates a PreparedStatement that the database will
    accept as a valid new row to the ticket table.
  Test Setup: Create a stub Ticket object.
  Test Strategy: Pass stub Ticket object to a mock database connection and determine what database
    functions are called via a prepared statement that adds a new ticket to the database.
  Input: Mock Database Connection, TicketDao class, Ticket class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void addValidTicketToDB() throws SQLException {
    TicketDao tDao = new TicketDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    tDao.add(ticket);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(7)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-9-1
  Requirement ID/Description: DB-9, The software shall update tickets in a database.
  Purpose: Verify that the TicketDao class creates a PreparedStatement that the database will
    accept as a modification to an existing row in the ticket table.
  Test Setup: Create a stub Ticket object.
  Test Strategy: Pass stub Ticket object to a mock database connection and determine what database
    functions are called via a prepared statement that updates an existing ticket in the database.
  Input: Mock Database Connection, TicketDao class, Ticket class.
  Expected Output: The PreparedStatement is processed by the mock connection without throwing
    an Exception.
   */
  @Test
  public void updateTicketInDB() throws SQLException {
    TicketDao tDao = new TicketDao(con);
    when(con.prepareStatement(any(String.class))).thenReturn(stmt);
    tDao.update(ticket);

    verify(con, times(1)).prepareStatement(anyString());
    verify(stmt, times(7)).setString(anyInt(), anyString());
    verify(stmt, times(1)).executeUpdate();
  }

  /*
  Test Case ID: #DB-7-1
  Requirement ID/Description: DB-7, The software shall read tickets in a database.
  Purpose: Verify that the TicketDao class successfully retrieves ticket information from a
    database and creates a Ticket object from this information.
  Test Setup: Create TicketDao for processing database connection.
  Test Strategy: Query database for a specific ticket and determine if that ticket was returned.
  Input: TicketDao class, Ticket class. Retrieves ticket TO001 in the actual database.
  Expected Output: The ticket #TO001 is retrieved from the database and created into
    a Ticket object.
   */
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

    System.out.println("Ticket " + actualTicket.getTicketId() + ", " + actualTicket.getFlightId());
  }

  /*
  Test Case ID: #DB-7-2
  Requirement ID/Description: DB-7, The software shall read tickets in a database.
  Purpose: Verify that the TicketDao class successfully retrieves ticket information for all
    tickets from a database and creates an array of Ticket objects from this information.
  Test Setup: Create TicketDao for processing database connection.
  Test Strategy: Query database for all tickets and determine if every ticket was returned.
  Input: TicketDao class, Ticket class. Retrieves an array of all tickets in the actual database.
  Expected Output: An array of Tickets containing every ticket and all their information.
   */
  @Test
  public void getAllTicketsFromDB() throws SQLException, InvalidTicketInputException {
    TicketDao ticketDao = new TicketDao();
    Ticket[] allTickets = ticketDao.getAll();
    Assertions.assertNotNull(allTickets);

    for (Ticket ticket : allTickets) {
      System.out.println("Ticket " + ticket.getTicketId() + ", associated flight: " + ticket.getFlightId() + ".");
    }
  }
}
