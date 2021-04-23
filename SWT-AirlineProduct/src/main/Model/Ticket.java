package Model;

public class Ticket {

	public class InvalidTicketInputException extends Exception {
		public InvalidTicketInputException(String message) {
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

	/**
	 *  Ensures a date is in the format YYY-MM-DD.
	 * @param date
	 * @return
	 */
	private boolean validateDate(String date) {
		return date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}

	/**
	 * Ensures a price contains only numbers.
	 * @param price
	 * @return
	 */
	private boolean validatePrice(String price) {
		return price.matches("^[0-9]+$");
	}

	/**
	 * Ensures that a flight class can only be the two options specified in the GUI: either Business
	 * or Economy.
	 * @param flightClass
	 * @return
	 */
	public boolean validateFlightClass(String flightClass) {
		return flightClass.equals("Economy") || flightClass.equals("Business");
	}

	/**
	 * Ensured that the user cannot enter a negative number of seats or a number of seats greater
	 * than 10.
	 * @param numSeats
	 * @return true if the number is between 0 and 10, false if any other number.
	 */
	public boolean validNumSeats(int numSeats) {
		return (numSeats > 0 && numSeats < 10);
	}

	/**
	 * Sets the price of the ticket.
	 * @param price
	 * @throws InvalidTicketInputException
	 */
	public void setPrice(String price) throws InvalidTicketInputException {
		if (validatePrice(price)) {
			this.price = price;
		} else {
			throw new InvalidTicketInputException("Invalid price. Numeric input only.");
		}
	}

	/**
	 * Handler function to set number of seats if it passes validation, and throw an exception if it
	 * does not.
	 * @param seats
	 * @throws InvalidTicketInputException
	 */
	public void setSeats(String seats) throws InvalidTicketInputException {
		try {
			this.setNumSeats(Integer.parseInt(seats));
		} catch (InvalidTicketInputException e) {
			throw new InvalidTicketInputException("Invalid number of seats.");
		}
	}

	/**
	 * Handler function to set the date if it passes validation, and throw an exception if it
	 * does not.
	 * @param date
	 * @throws InvalidTicketInputException
	 */
	public void setDate(String date) throws InvalidTicketInputException {
		if (validateDate(date)) {
			this.date = date;
		} else {
			throw new InvalidTicketInputException("Invalid date. Improper format.");
		}
	}

	/**
	 * Handler function to set the class of the ticket if it passes validation, and throw an
	 * exception if it does not.
	 * @param flightClass
	 * @throws InvalidTicketInputException
	 */
	public void setFlightClass(String flightClass) throws InvalidTicketInputException {
		if (validateFlightClass(flightClass)) {
			this.flightClass = flightClass;
		} else {
			throw new InvalidTicketInputException("Invalid flight class.");
		}
	}

	/**
	 * Handler function to set the number of seats of the ticket if it passes validation, and throw an
	 * exception if it does not.
	 * @param numSeats
	 * @throws InvalidTicketInputException
	 */
	public void setNumSeats(int numSeats) throws InvalidTicketInputException {
		if (validNumSeats(numSeats)) {
			this.seats = String.valueOf(numSeats);
		} else {
			throw new InvalidTicketInputException("Invalid Number of Seats");
		}
	}

	/**
	 * Returns the date associated with the ticket booking.
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Returns the ID number of the ticket.
	 * @return
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * Returns the ID number of the flight associated with the ticket.
	 * @return
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * Returns the ID number of the customer associated with the ticket.
	 * @return
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * Returns the flight class of the ticket.
	 * @return
	 */
	public String getFlightClass() {
		return flightClass;
	}

	/**
	 *  Returns the price of the ticket.
	 * @return
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Returns the number of seats booked on the ticket.
	 * @return
	 */
	public String getSeats() {
		return seats;
	}

	/**
	 * Default constructor to set all properties associated with the ticket.
	 * @param ticketId
	 * @param flightId
	 * @param custId
	 * @param flightClass
	 * @param price
	 * @param seats
	 * @param date
	 * @throws InvalidTicketInputException
	 */
	public Ticket(String ticketId, String flightId, String custId, String flightClass, String price,
			String seats, String date) throws InvalidTicketInputException {
		this.ticketId = ticketId;
		this.flightId = flightId;
		this.custId = custId;
		setPrice(price);
		setDate(date);
		setSeats(seats);
		setFlightClass(flightClass);
	}
}
