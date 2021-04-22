package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Service.NetworkService;

public class Flight {

	// A custom exception to handle any invalid input to all of the properties of
	// the Flight class.
	public class InvalidFlightInputException extends Exception {
		public InvalidFlightInputException(String message) {
			super(message);
		}
	}

	String id;
	String name;
	String source;
	String depart;
	String date;
	String depTime;
	String arrTime;
	String charge;

	// Constructor for the Flight class.
	public Flight(String id, String name, String source, String depart, String date, String depTime, String arrTime,
			String charge) throws InvalidFlightInputException {
		setId(id);
		setName(name);
		setSource(source);
		setDepart(depart);
		setDate(date);
		setDepTime(depTime);
		setArrTime(arrTime);
		setCharge(charge);
	}

	public void updateInDatabase() throws Exception {
		Connection con = NetworkService.getInstance().getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into flight(id,flightname,source,depart,date,deptime,arrtime,flightcharge)values(?,?,?,?,?,?,?,?)");
			pst.setString(1, this.getId());
			pst.setString(2, this.getName());
			pst.setString(3, this.getSource());
			pst.setString(4, this.getDepart());
			pst.setString(5, this.getDate());
			pst.setString(6, this.getDepTime());
			pst.setString(7, this.getArrTime());
			pst.setString(8, this.getCharge());
			pst.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("invalid value for flight");
		}
	}

	// Ensures an Id is of the format FO###.
	private boolean validateId(String id) {
		return id.matches("^FO[0-9]{3}$");
	}

	// Ensures a name contains only letters and is at least 1 character.
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z\\s]+$");
	}

	// Ensures a location (e.g. source and depart) can only be the countries the
	// airline flies to.
	private boolean validateLocale(String locale) {
		return locale.equals("India") || locale.equals("Srilanka") || locale.equals("UK") || locale.equals("Uk") || locale.equals("USA") || locale.equals("Canada")
				|| locale.equals("China");
	}

	// Ensures a date is in the format YYY-MM-DD.
	private boolean validateDate(String date) {
		return date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}

	// Ensures a time is in the format #.## or ##.## followed by AM or PM. Also
	// ensures the hour cannot be above 12.
	private boolean validateTime(String time) {
		return time.matches("^([0-9]|1[0-2]).[0-9]{2}(AM|PM)$");
	}

	// Ensured a charge contains only numbers.
	private boolean validateCharge(String charge) {
		return charge.matches("^[0-9]+$");
	}

	// Returns the flight ID.
	public String getId() {
		return id;
	}

	// Sets a flight ID if valid, otherwise throws a custom exception marking an
	// invalid property value.
	public void setId(String id) throws InvalidFlightInputException {
		if (validateId(id)) {
			this.id = id;
		} else {
			throw new InvalidFlightInputException("Flight ID must be in the format \"FO###\".");
		}
	}

	// Returns the name of the flight.
	public String getName() {
		return name;
	}

	// Sets a flight name if valid, otherwise throws a custom exception marking an
	// invalid property value.
	public void setName(String name) throws InvalidFlightInputException {
		if (validateName(name)) {
			this.name = name;
		} else {
			throw new InvalidFlightInputException("Flight name must contain alphabetic characters only.");
		}
	}

	// Returns the source country of the flight.
	public String getSource() {
		return source;
	}

	// Sets a source country if valid, otherwise throws a custom exception marking
	// an invalid property value.
	public void setSource(String source) throws InvalidFlightInputException {
		if (validateLocale(source)) {
			this.source = source;
		} else {
			throw new InvalidFlightInputException(
					"Flight source country must be: India, Srilanka, Uk, USA, Canada, or China.");
		}
	}

	// Returns the departing country of the flight.
	public String getDepart() {
		return depart;
	}

	// Sets a departing country if valid, otherwise throws a custom exception
	// marking an invalid property value.
	public void setDepart(String depart) throws InvalidFlightInputException {
		if (validateLocale(depart)) {
			this.depart = depart;
		} else {
			throw new InvalidFlightInputException(
					"Flight departure country must be: India, Srilanka, Uk, USA, Canada, or China.");
		}
	}

	// Returns the date of the flight.
	public String getDate() {
		return date;
	}

	// Sets the date of the flight, otherwise throws a custom exception marking an
	// invalid property value.
	public void setDate(String date) throws InvalidFlightInputException {
		if (validateDate(date)) {
			this.date = date;
		} else {
			throw new InvalidFlightInputException("Flight date must be in the format \"YYYY-MM-DD\".");
		}
	}

	// Returns the departure time of the flight.
	public String getDepTime() {
		return depTime;
	}

	// Sets the flight's departure time if valid, otherwise throws a custom
	// exception marking an invalid property value.
	public void setDepTime(String depTime) throws InvalidFlightInputException {
		if (validateTime(depTime)) {
			this.depTime = depTime;
		} else {
			throw new InvalidFlightInputException(
					"Flight departure time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM.");
		}
	}

	// Returns the arrival time of the flight.
	public String getArrTime() {
		return arrTime;
	}

	// Sets the flight's arrival time if valid, otherwise throws a custom exception
	// marking an invalid property value.
	public void setArrTime(String arrTime) throws InvalidFlightInputException {
		if (validateTime(arrTime)) {
			this.arrTime = arrTime;
		} else {
			throw new InvalidFlightInputException(
					"Flight arrival time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM..");
		}
	}

	// Returns the price of a seat on the flight.
	public String getCharge() {
		return charge;
	}

	// Sets the price of the flight if valid, otherwise throws a custom exception
	// marking an invalid property value.
	public void setCharge(String charge) throws InvalidFlightInputException {
		if (validateCharge(charge)) {
			this.charge = charge;
		} else {
			throw new InvalidFlightInputException("Flight charge must be numeric characters only.");
		}
	}
}
