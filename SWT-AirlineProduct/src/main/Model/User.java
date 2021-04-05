package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Java Class to Contain User data. 
 */
public class User {

	public class InvalidUserInputException extends Exception {
		public InvalidUserInputException(String message) {
			super(message);
		}
	}
	
	public class UpdateUserException extends Exception {
		public UpdateUserException(String message) {
			super(message);
		}
	}
	
	// User Attributes 
	String id;
	String firstName;
	String lastName;
	String userName;
	String password;


	public String getId() {
		return id;
	}
	
	// Mutuator for id variable
	public void setId(String id) {
		this.id = id;
	}

	// Accessor for firsName variable
	public String getFirstName() {
		return firstName;
	}

	// Mutator for firstName variable
	public void setFirstName(String firstName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper firstname input by user.
		if (firstName.matches("^[a-zA-Z'’-]+$")) {
			this.firstName = firstName;
		// If invalid first name, throw InvalidUIserInputException
		} else {
			throw new InvalidUserInputException("Invalid First Name.");
		}
	}
	
	//Accesor for lastName variable
	public String getLastName() {
		return lastName;
	}
	
	// Mutator for lastName
	public void setLastName(String lastName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper lastName input by user.
		if (lastName.matches("^[a-zA-Z'’-]+$")) {
			this.lastName = lastName;
		// If invalid last name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Last Name.");
		}
	}
	// Accessor for userName variable
	public String getUserName() {
		return userName;
	}
	
	// Mutator for userName variable
	public void setUserName(String userName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper username input by user.

		if (userName.matches("^[a-zA-z]{4,20}$")) {
			this.userName = userName;
		// If invalid user name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Username.");
		}
	}
	
	// Accessor for password variable
	public String getPassword() {
		return password;
	}
	
	//Mutator for password variable
	public void setPassword(String password) throws InvalidUserInputException {
		//String passwordPattern = "^[a-zA-Z0-9]+$";
		String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]*$";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		//if statement with regular expression, used to ensure proper password input by user.
		if (matcher.matches()) {
			this.password = password;
		// If invalid password, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Invalid Password.");
		}
	}
	
	// Constructor method to set the user attributes.
	public User(String id, String firstName, String lastName, String userName, String password) throws InvalidUserInputException {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassword(password);
	}
}
