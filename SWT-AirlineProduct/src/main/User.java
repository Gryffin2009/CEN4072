package main;

public class User {

	public class InvalidUserInputException extends Exception {
		public InvalidUserInputException(String message) {
			super(message);
		}
	}
	
	String id;
	String firstName;
	String lastName;
	String userName;
	String password;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidUserInputException {
		if (firstName.matches("^[a-zA-Z'-]+$")) {
			this.firstName = firstName;
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidUserInputException {
		if (lastName.matches("^[a-zA-Z'-]+$")) {
			this.lastName = lastName;
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws InvalidUserInputException {
		if (userName.matches("^[a-zA-Z]+$")) {
			this.userName = userName;
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidUserInputException {
		if (password.matches("^[a-zA-Z0-9]+$")) {
			this.password = password;
		} else {
			throw new InvalidUserInputException("Usernames may only contain letters, dashes, and apostrophes.");
		}
	}
	
	public User(String id, String firstName, String lastName, String userName, String password) throws InvalidUserInputException {
		
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassword(password);
	}
	
	
}
