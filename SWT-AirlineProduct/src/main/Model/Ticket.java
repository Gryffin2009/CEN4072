package main.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.Service.NetworkService;

public class Ticket {

	public class InvalidTicketInputException extends Exception {
		public InvalidTicketInputException(String message) {
			super(message);
		}
	}

	public class UpdateTicketException extends Exception {
		public UpdateTicketException(String message) {
			super(message);
		}
	}

	String ticketid;
	String flightid;
	String custid;
	String flightclass;
	String price;
	String seats;
	String date;

	public Ticket(String ticketid, String flightid, String custid, String flightclass, String price, String seats,
			String date) throws InvalidTicketInputException {
		this.ticketid = ticketid;
		this.flightid = flightid;
		this.custid = custid;
		this.flightclass = flightclass;
		this.price = price;
		this.seats = seats;
		this.date = date;
	}
	
	public void updateInDatabase() throws UpdateTicketException {
		Connection con = NetworkService.getInstance().getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into ticket(id,flightid,custid,class,price,seats,date)values(?,?,?,?,?,?,?)");
			pst.setString(1, ticketid);
			pst.setString(2, flightid);
			pst.setString(3, custid);
			pst.setString(4, flightclass);
			pst.setString(5, price);
			pst.setString(6, seats);
			pst.setString(7, date);
			pst.executeUpdate();
		} catch (SQLException ex) {
			throw new UpdateTicketException(ex.getMessage());
		}
	}
	
	public void setNumSeats(int numSeats) throws InvalidTicketInputException {
		if (validNumSeats(numSeats)) {
			this.seats = String.valueOf(numSeats);
		} else {
			throw new InvalidTicketInputException("Invalid Number of Seats");
		}
	}
	
	public boolean validNumSeats(int numSeats) {
		return (numSeats > 0 && numSeats < 10);
	}



}
