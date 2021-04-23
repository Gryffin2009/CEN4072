package Service;

import Model.Ticket;
import Model.Ticket.InvalidTicketInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDao {
  Connection con;

  public TicketDao(Connection con) {
    this.con = con;
  }
  public TicketDao() {
    con = NetworkService.getInstance().getConnection();
  }

  /**
   * Adds a new ticket to the database from a Ticket object.
   * @param ticket
   * @throws SQLException
   */
  public void add(Ticket ticket) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO ticket"
            + "(id,flightid,custid,class,price,seats,date)"
            + "values(?,?,?,?,?,?,?)");
    AddTicketFromPreparedStatement(pst, ticket);
  }

  /**
   * Updates an existing ticket in the database from a Ticket object.
   * @param ticket
   * @throws SQLException
   */
  public void update(Ticket ticket) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "UPDATE ticket SET"
            + "id=?,"
            + "flightid=?,"
            + "custid=?,"
            + "class=?,"
            + "price=?,"
            + "seats=?,"
            + "date=?"
    );
    AddTicketFromPreparedStatement(pst, ticket);
  }

  /**
   * Companion method that takes a prepared statement and processes it with the database.
   * @param pst
   * @param ticket
   * @throws SQLException
   */
  public void AddTicketFromPreparedStatement(PreparedStatement pst, Ticket ticket)
      throws SQLException {

    pst.setString(1, ticket.getTicketId());
    pst.setString(2, ticket.getFlightId());
    pst.setString(3, ticket.getCustId());
    pst.setString(4, ticket.getFlightClass());
    pst.setString(5, ticket.getPrice());
    pst.setString(6, ticket.getSeats());
    pst.setString(7, ticket.getDate());
    pst.executeUpdate();
  }

  /**
   * Retrieves ticket information from the database using a ticket ID.
   * @param id
   * @return
   * @throws SQLException
   * @throws InvalidTicketInputException
   */
  public Ticket get(String id) throws SQLException, InvalidTicketInputException {

    PreparedStatement pst = con.prepareStatement("SELECT * FROM ticket WHERE id = ?");
    pst.setString(1, id);
    ResultSet rs = pst.executeQuery();
    rs.next();

    return GetTicketFromResultSet(rs);
  }

  /**
   * Retrieves every ticket from the database and returns it as an array of Ticket objects.
   * @return an array of Ticket objects.
   * @throws SQLException
   * @throws InvalidTicketInputException
   */
  public Ticket[] getAll() throws SQLException, InvalidTicketInputException {
    PreparedStatement pst = con.prepareStatement("SELECT count(*) AS rowCount FROM ticket");
    ResultSet rs = pst.executeQuery();
    rs.next();
    int ticketCount = rs.getInt("rowCount");
    rs.close();
    Ticket[] tickets = new Ticket[ticketCount];

    pst = con.prepareStatement("SELECT * from ticket");
    rs = pst.executeQuery();

    int i = 0;
    while (rs.next()) {
      tickets[i] = GetTicketFromResultSet(rs);
      i++;
    }
    return tickets;
  }

  /**
   * A companion module that processes a ResultSet of ticket information and returns it as a
   * Ticket object.
   * @param rs
   * @return
   * @throws SQLException
   * @throws InvalidTicketInputException
   */
  public Ticket GetTicketFromResultSet(ResultSet rs)
      throws SQLException, InvalidTicketInputException {

    String ticketId = rs.getString("id");
    String flightId = rs.getString("flightid");
    String custId = rs.getString("custid");
    String ticketClass = rs.getString("class");
    String price = rs.getString("price");
    String seats = rs.getString("seats");
    String date = rs.getString("date");

    Ticket ticket = new Ticket(ticketId, flightId, custId, ticketClass, price, seats, date);
    return ticket;
  }
}
