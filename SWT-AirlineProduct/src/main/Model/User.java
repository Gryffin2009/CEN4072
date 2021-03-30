package Model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Service.NetworkService;

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
	
	// Accessor for id variable 
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
		if (firstName.matches("^[a-zA-Z'-]+$")) {
			this.firstName = firstName;
		// If invalid first name, throw InvalidUIserInputException
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}
	
	//Accesor for lastName variable
	public String getLastName() {
		return lastName;
	}
	
	// Mutator for lastName
	public void setLastName(String lastName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper lastName input by user.
		if (lastName.matches("^[a-zA-Z'-]+$")) {
			this.lastName = lastName;
		// If invalid last name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}
	// Accessor for userName variable
	public String getUserName() {
		return userName;
	}
	
	// Mutator for userName variable
	public void setUserName(String userName) throws InvalidUserInputException {
		// if statement with regular expression, used to ensure proper username input by user.
		if (userName.matches("^[a-zA-Z]+$")) {
			this.userName = userName;
		// If invalid user name, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}
	
	// Accessor for password variable
	public String getPassword() {
		return password;
	}
	
	//Mutator for password variable
	public void setPassword(String password) throws InvalidUserInputException {
		//if statement with regular expression, used to ensure proper password input by user.
		if (password.matches("^[a-zA-Z0-9]+$")) {
			this.password = password;
		// If invalid password, throw InvalidUserInputException
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
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
	
	// Updates the user in the database.
	public void updateInDatabase() throws UpdateUserException {
		Connection con = NetworkService.getInstance().getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)");
			pst.setString(1, this.getId());
			pst.setString(2, this.getFirstName());
			pst.setString(3, this.getLastName());
			pst.setString(4, this.getUserName());
			pst.setString(5, this.getPassword());
			pst.executeUpdate();
		} catch (SQLException ex) {
			throw new UpdateUserException("Unable to update user in the database.");
		}
	}

	
}
