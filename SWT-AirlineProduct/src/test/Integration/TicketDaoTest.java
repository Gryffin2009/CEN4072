package Integration;

import static org.mockito.Mockito.*;

import Model.Ticket;
import Service.TicketDao;
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
}
