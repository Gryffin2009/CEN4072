package Service;

import Model.Flight;
import Model.Flight.InvalidFlightInputException;
import Model.Ticket.InvalidTicketInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDao {
  Connection con;

  public FlightDao(Connection con) {
    this.con = con;
  }
  public FlightDao() {
    con = NetworkService.getInstance().getConnection();
  }

  /**
   * Adds a new flight to the database from a Flight object.
   * @param flight
   * @throws SQLException
   */
  public void add(Flight flight) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO flight"
            + "(id,flightname,source,depart,date,deptime,arrtime,flightcharge)"
            + "values(?,?,?,?,?,?,?,?)");
    AddFlightFromPreparedStatement(pst, flight);
  }

  /**
   * Updates an existing flight in the database from a Flight object.
   * @param pst
   * @param flight
   * @throws SQLException
   */
  public void AddFlightFromPreparedStatement(PreparedStatement pst, Flight flight)
      throws SQLException {
    pst.setString(1, flight.getId());
    pst.setString(2, flight.getName());
    pst.setString(3, flight.getSource());
    pst.setString(4, flight.getDepart());
    pst.setString(5, flight.getDate());
    pst.setString(6, flight.getDepTime());
    pst.setString(7, flight.getArrTime());
    pst.setString(8, flight.getCharge());
    pst.executeUpdate();
  }

  /**
   * Companion method that takes a prepared statement and processes it with the database.
   * @param flight
   * @throws SQLException
   */
  public void update(Flight flight) throws SQLException {
    PreparedStatement pst = con.prepareStatement(
        "UPDATE flight SET"
            + "id=?,"
            + "flightname=?,"
            + "source=?,"
            + "depart=?,"
            + "date=?,"
            + "deptime=?,"
            + "arrtime=?,"
            + "flightcharge=?"
    );
    AddFlightFromPreparedStatement(pst, flight);
  }

  /**
   * Retrieves flight information from the database using a flight ID.
   * @param id
   * @return
   * @throws SQLException
   * @throws InvalidTicketInputException
   * @throws InvalidFlightInputException
   */
  public Flight get(String id)
      throws SQLException, InvalidTicketInputException, InvalidFlightInputException {

    PreparedStatement pst = con.prepareStatement("SELECT * FROM flight WHERE id = ?");
    pst.setString(1, id);
    ResultSet rs = pst.executeQuery();
    rs.next();

    return GetFlightFromResultSet(rs);
  }

  /**
   * Retrieves every flight from the database and returns it as an array of Flight objects.
   * @return an array of Flight objects.
   * @throws SQLException
   * @throws InvalidFlightInputException
   */
  public Flight[] getAll() throws SQLException, InvalidFlightInputException {
    PreparedStatement pst = con.prepareStatement("SELECT count(*) AS rowCount FROM flight");
    ResultSet rs = pst.executeQuery();
    rs.next();
    int ticketCount = rs.getInt("rowCount");
    rs.close();
    Flight[] flights = new Flight[ticketCount];

    pst = con.prepareStatement("SELECT * from flight");
    rs = pst.executeQuery();

    int i = 0;
    while (rs.next()) {
      flights[i] = GetFlightFromResultSet(rs);
      i++;
    }
    return flights;
  }

  /**
   * A companion module that processes a ResultSet of flight information and returns it as a
   * Flight object.
   * @param rs
   * @return
   * @throws InvalidFlightInputException
   * @throws SQLException
   */
  public Flight GetFlightFromResultSet(ResultSet rs)
      throws InvalidFlightInputException, SQLException {

    String id = rs.getString("id");
    String flightName = rs.getString("flightname");
    String source = rs.getString("source");
    String depart = rs.getString("depart");
    String date = rs.getString("date");
    String deptTime = rs.getString("deptime");
    String arrTime = rs.getString("arrtime");
    String charge = rs.getString("flightcharge");

    Flight flight = new Flight(id, flightName, source, depart, date, deptTime, arrTime, charge);
    return flight;
  }
}
