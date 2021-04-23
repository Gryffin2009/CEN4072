package Model;

import Service.NetworkService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Java Class to Contain User data. 
 */
public class User {

	/**
	 * Custom exception to be thrown when any property does not pass input validation.
	 */
	public class InvalidUserInputException extends Exception {
		public InvalidUserInputException(String message) {
			super(message);
		}
	}

	// User Attributes 
	String id;
	String firstName;
	String lastName;
	String userName;
	String password;

	String passwordPattern;
	Pattern pattern;
	Matcher matcher;
	boolean isMatch;

	/**
	 * Accessor for id variable.
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Accessor for firsName variable.
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Accessor for lastName variable.
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Accessor for userName variable.
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Accessor for password variable.
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Mutuator for id variable
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Validates a first name, ensuring it contains only letters, apostrophes, or dashes.
	 * @param firstName
	 * @throws InvalidUserInputException
	 */
	public void setFirstName(String firstName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper firstname input by user.
		if (firstName.matches("^[a-zA-Z'’-]+$")) {
			this.firstName = firstName;
		// If invalid first name, throw InvalidUIserInputException
		} else {
			throw new InvalidUserInputException("Invalid First Name.");
		}
	}

	/**
	 * Validates a last name, ensuring it contains only letters, apostrophes, or dashes.
	 * @param lastName
	 * @throws InvalidUserInputException
	 */
	public void setLastName(String lastName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper lastName input by user.
		if (lastName.matches("^[a-zA-Z'’-]+$")) {
			this.lastName = lastName;
		// If invalid last name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Last Name.");
		}
	}

	/**
	 * Validates a user name, ensuring that it is 4-20 characters long and contains only letters.
	 * @param userName
	 * @throws InvalidUserInputException
	 */
	public void setUserName(String userName) throws InvalidUserInputException {

		// if statement with regular expression, used to ensure proper username input by user.
		if (userName.matches("^[a-zA-z]{4,20}$")) {
			this.userName = userName;
		// If invalid user name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Username.");
		}
	}

	/**
	 * Validate a user name, ensuring that it does not already exist in the database.
	 * @param userName
	 * @return
	 * @throws InvalidUserInputException
	 */
	private boolean isUniqueUserName(String userName) throws InvalidUserInputException {
		try {
			Connection con = NetworkService.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM user WHERE username=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
				return count <= 0;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}

	/**
	 * Ensures that a password contains 1 number, 1 uppercase letter, 1 lowercase letter, and is
	 * between 5-15 characters in length.
	 * @param password
	 * @throws InvalidUserInputException
	 */
	public void setPassword(String password) throws InvalidUserInputException {
		//passwordPattern = "^[a-zA-Z0-9]+$";
		passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,15}$";
		pattern = Pattern.compile(passwordPattern);
		matcher = pattern.matcher(password);
		isMatch = matcher.matches();
		//if statement with regular expression, used to ensure proper password input by user.
		if (isMatch) {
			this.password = password;
		// If invalid password, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Password.");
		}
	}

	/**
	 * Returns all fields belonging to User as a single string.
	 * @return
	 */
	public String toString() {
		return getId() + " " + getFirstName() + " " + getLastName() + " " + getUserName() + " " + getPassword();
	}

	/**
	 * Default constructor to add all of the data belonging to a user and instantiate a User object.
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @throws InvalidUserInputException
	 */
	// Constructor method to set the user attributes.
	public User(String id, String firstName, String lastName, String userName, String password)
			throws InvalidUserInputException {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassword(password);
	}
}
