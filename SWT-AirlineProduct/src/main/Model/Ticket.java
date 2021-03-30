package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Service.NetworkService;

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

	String ticketId;
	String flightId;
	String custId;
	String flightClass;
	String price;
	String seats;
	String date;

	// TODO Error checking on all ticket properties
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Ticket(String ticketId, String flightId, String custId, String flightClass, String price, String seats,
			String date) throws InvalidTicketInputException {
		this.ticketId = ticketId;
		this.flightId = flightId;
		this.custId = custId;
		this.flightClass = flightClass;
		this.price = price;
		this.seats = seats;
		this.date = date;
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
