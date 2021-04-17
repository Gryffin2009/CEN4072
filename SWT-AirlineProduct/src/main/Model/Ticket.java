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

	// Ensures a date is in the format YYY-MM-DD.
	private boolean validateDate(String date) {
		return date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}

	// Ensured a price contains only numbers.
	private boolean validatePrice(String price) {
		return price.matches("^[0-9]+$");
	}

	public boolean validateFlightClass(String flightClass) {
		return flightClass.equals("Economy") || flightClass.equals("Business");
	}

	public boolean validNumSeats(int numSeats) {
		return (numSeats > 0 && numSeats < 10);
	}

	public void setPrice(String price) throws InvalidTicketInputException {
		if (validatePrice(price)) {
			this.price = price;
		} else {
			throw new InvalidTicketInputException("Invalid price. Numeric input only.");
		}
	}

	public void setSeats(String seats) throws InvalidTicketInputException {
		try {
			this.setNumSeats(Integer.parseInt(seats));
		} catch (InvalidTicketInputException e) {
			throw new InvalidTicketInputException("Invalid number of seats.");
		}
	}

	public void setDate(String date) throws InvalidTicketInputException {
		if (validateDate(date)) {
			this.date = date;
		} else {
			throw new InvalidTicketInputException("Invalid date. Improper format.");
		}
	}

	public void setFlightClass(String flightClass) throws InvalidTicketInputException {
		if (validateFlightClass(flightClass)) {
			this.flightClass = flightClass;
		} else {
			throw new InvalidTicketInputException("Invalid flight class.");
		}
	}
	public void setNumSeats(int numSeats) throws InvalidTicketInputException {
		if (validNumSeats(numSeats)) {
			this.seats = String.valueOf(numSeats);
		} else {
			throw new InvalidTicketInputException("Invalid Number of Seats");
		}
	}

	public String getDate() {
		return date;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getFlightId() {
		return flightId;
	}

	public String getCustId() {
		return custId;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public String getPrice() {
		return price;
	}

	public String getSeats() {
		return seats;
	}

	public Ticket(String ticketId, String flightId, String custId, String flightClass, String price, String seats,
			String date) throws InvalidTicketInputException {
		this.ticketId = ticketId;
		this.flightId = flightId;
		this.custId = custId;
		setPrice(price);
		setDate(date);
		setSeats(seats);
		setFlightClass(flightClass);
	}




}
