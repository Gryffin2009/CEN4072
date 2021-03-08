package main;

import main.Customer.InvalidCustomerInputException;

public class Flight {

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
	
	public Flight(String id, String name, String source, String depart,
			String date, String depTime, String arrTime, String charge) throws InvalidFlightInputException {
		setId(id);
		setName(name);
		setSource(source);
		setDepart(depart);
		setDate(date);
		setDepTime(depTime);
		setArrTime(arrTime);
		setCharge(charge);
	}
	
	private boolean validateId(String id) {
		return id.matches("^FO[0-9]{3}$");
	}
	
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z]+$");
	}
	
	private boolean validateLocale(String locale) {
		if (locale == "India" || locale == "Srilanka" || locale == "Uk" || locale == "Usa" || locale == "Canada" || locale == "Chinna") {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateDate(String date) {
		return date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}
	
	private boolean validateTime(String time) {
		return time.matches("^([0-9]|1[0-2]).[0-9]{2}(AM|PM)$");
	}
	
	private boolean validateCharge(String charge) {
		return charge.matches("^[0-9]+$");
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) throws InvalidFlightInputException {
		if (validateId(id)) {
			this.id = id;
		} else {
			throw new InvalidFlightInputException("Flight ID must be in the format \"FO###\".");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidFlightInputException {
		if (validateName(name)) {
			this.name = name;
		} else {
			throw new InvalidFlightInputException("Flight name must contain alphabetic characters only.");
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) throws InvalidFlightInputException {
		if (validateLocale(source)) {
			this.source = source;
		} else {
			throw new InvalidFlightInputException("Flight source country must be: India, Srilanka, Uk, Usa, Canada, or Chinna.");
		}
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) throws InvalidFlightInputException {
		if (validateLocale(depart)) {
			this.depart = depart;
		} else {
			throw new InvalidFlightInputException("Flight departure country must be: India, Srilanka, Uk, Usa, Canada, or Chinna.");
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws InvalidFlightInputException {
		if (date.matches(date)) {
			this.date = date;
		} else {
			throw new InvalidFlightInputException("Flight date must be in the format \"YYYY-MM-DD\".");
		}
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) throws InvalidFlightInputException {
		if (validateTime(depTime)) {
			this.depTime = depTime;
		} else {
			throw new InvalidFlightInputException("Flight departure time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM.");
		}
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) throws InvalidFlightInputException {
		if (validateTime(arrTime)) {
			this.arrTime = arrTime;
		} else {
			throw new InvalidFlightInputException("Flight arrival time must be in the format \"#.##AM\" or \"#.##PM\", i.e. 8.00AM or 12.00PM..");
		}
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) throws InvalidFlightInputException {
		if (validateCharge(charge)) {
			this.charge = charge;
		} else {
			throw new InvalidFlightInputException("Flight charge must be numeric characters only.");
		}
	}
}
