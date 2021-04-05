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

  public void add(Ticket ticket) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO ticket"
            + "(id,flightid,custid,class,price,seats,date)"
            + "values(?,?,?,?,?,?,?)");
    AddTicketFromPreparedStatement(pst, ticket);
  }

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

  public Ticket get(String id) throws SQLException, InvalidTicketInputException {

    PreparedStatement pst = con.prepareStatement("SELECT * FROM ticket WHERE id = ?");
    pst.setString(1, id);
    ResultSet rs = pst.executeQuery();
    rs.next();

    return GetTicketFromResultSet(rs);
  }

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
