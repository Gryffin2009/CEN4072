package main.Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Service.NetworkService;

public class Customer {

	// A custom exception to handle any invalid input to all of the properties of the Customer class.
	public class InvalidCustomerInputException extends Exception {
		public InvalidCustomerInputException(String message) {
			super(message);
		}
	}
	
	public class UpdateCustomerException extends Exception {
		public UpdateCustomerException(String message) {
			super(message);
		}
	}

	private String id;
	private String firstname;
	private String lastname;
	private String nic;
	private String passport;
	private String address;
	private String dob;
	private String gender;
	private String contact;
	private byte[] photo;
	
	// Constructor with the image as a byte[] array, which is how the image is stored in the database.
	public Customer(String id, String firstname, String lastname, String nic, String passport,
			String address, String dob, String gender, String contact, byte[] photo) throws InvalidCustomerInputException {
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setNic(nic);
		setPassport(passport);
		setAddress(address);
		setDob(dob);
		setGender(gender);
		setContact(contact);
		setPhoto(photo);
	}
	
	// Constructor with image path, which then converts the image to a byte[] array so it can be stored in the database.
	public Customer(String id, String firstname, String lastname, String nic, String passport,
			String address, String dob, String gender, String contact, String photo) throws InvalidCustomerInputException, IOException {
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setNic(nic);
		setPassport(passport);
		setAddress(address);
		setDob(dob);
		setGender(gender);
		setContact(contact);
		setPhoto(photo);
	}
	
	// Updates a customer in the database.
	public void updateInDatabase() throws UpdateCustomerException {
		Connection con = NetworkService.getInstance().getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into customer(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, this.getId());
			pst.setString(2, this.getFirstname());
			pst.setString(3, this.getLastname());
			pst.setString(4, this.getNic());
			pst.setString(5, this.getPassport());
			pst.setString(6, this.getAddress());
			pst.setString(7, this.getDob());
			pst.setString(8, this.getGender());
			pst.setString(9, this.getContact());
			pst.setBytes(10, this.getPhoto());
			pst.executeUpdate();
		} catch (SQLException ex) {
			throw new UpdateCustomerException(ex.getMessage());
		}
	}
	
	// Ensures an Id is of the format CS###.
	private boolean validateId(String id) {
		return id.matches("^CS[0-9]{3}$");
	}
	
	// Ensures a name contains only letters, dashes, and/or apostrophes, as well as at least 1 character.
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z'-]+$");
	}
	
	// Ensures a NIC contains only letters and numbers and at least 1 character.
	private boolean validateNic(String nic) {
		return nic.matches("^[a-zA-Z0-9]+$");
	}
	
	// Ensures a passport number contains only letters and numbers and at least 1 character.
	private boolean validatePassport(String passport) {
		return passport.matches("^[a-zA-Z0-9]+$");
	}

	/*
	// TODO add address formatting RegEx checks
	public boolean validateAddress(String address, String street, String city, String region, String zip, String country) {
		boolean isValid = false;
		
		if (address.matches("^[0-9]+$")
				&& street.matches("^[a-zA-Z]+ [a-zA-Z]+$")
				&& city.matches("^[a-zA-Z]+$")
				&& region.matches("^[a-zA-Z]+$")
				&& zip.matches("^[a-zA-Z0-9]+$")
				&& country.matches("^[a-zA-Z]+$")) {
			isValid = true;
		}
		
		return isValid;
	}
	*/
	
	// Ensures a date of birth is in the format YYY-MM-DD.
	private boolean validateDob(String dob) {
		return dob.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");		
	}

	// Ensures a gender is either Male or Female.
	private boolean validateGender(String gender) {
		if (gender.contains("Male") || gender.contains("Female")) {
			return true;
		} else {
			return false;
		}
	}
	
	// Ensures a phone number consists only of numbers and has 7 digits.
	private boolean validateContact(String contact) {
		return contact.matches("^[0-9]{7}$");
	}
	
	// Returns the customer ID.
	public String getId() {
		return id;
	}

	// Sets an ID if valid, otherwise throws a custom exception marking an invalid property value.
	public void setId(String id) throws InvalidCustomerInputException {
		if (validateId(id)) {
			this.id = id;
		} else {
			throw new InvalidCustomerInputException("ID must be in the format \"CS###\".");
		}
	}

	// Returns the first name of the customer.
	public String getFirstname() {
		return firstname;
	}

	// Sets a first name if valid, otherwise throws a custom exception marking an invalid property value.
	public void setFirstname(String firstname) throws InvalidCustomerInputException {
		if (validateName(firstname)) {
			this.firstname = firstname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	// Returns the last name of the customer.
	public String getLastname() {
		return lastname;
	}

	// Sets a last name if valid, otherwise throws a custom exception marking an invalid property value.
	public void setLastname(String lastname) throws InvalidCustomerInputException {
		if (validateName(lastname)) {
			this.lastname = lastname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	// Returns the NIC of the customer.
	public String getNic() {
		return nic;
	}

	// Sets the NIC if valid, otherwise throws a custom exception marking an invalid property value.
	public void setNic(String nic) throws InvalidCustomerInputException {
		if (validateNic(nic)) {
			this.nic = nic;
		} else {
			throw new InvalidCustomerInputException("Customer NIC must contain alphanumeric characters only.");
		}
	}

	// Returns the passport number of the customer.
	public String getPassport() {
		return passport;
	}

	// Sets a passport number if valid, otherwise throws a custom exception marking an invalid property value.
	public void setPassport(String passport) throws InvalidCustomerInputException {
		if (validatePassport(passport)) {
			this.passport = passport;
		} else {
			throw new InvalidCustomerInputException("Customer Passport must contain alphanumeric characters only.");
		}
	}

	// Returns the address of the customer.
	public String getAddress() {
		return address;
	}

	// Sets the address of the customer.
	public void setAddress(String address) throws InvalidCustomerInputException {
		
		//TODO Make an Address class!
		if (address == address) {
			this.address = address;
		} else {
			throw new InvalidCustomerInputException("");
		}
	}

	// Returns the date of birth of the customer.
	public String getDob() {
		return dob;
	}

	// Sets a date of birth if valid, otherwise throws a custom exception marking an invalid property value.
	public void setDob(String dob) throws InvalidCustomerInputException {
		if (validateDob(dob)) {
			this.dob = dob;
		} else {
			throw new InvalidCustomerInputException("Customer date of birth must be in the following format: YYYY-MM-DD.");
		}
	}

	// Returns the gender of the customer.
	public String getGender() {
		return gender;
	}

	// Sets a gender if valid, otherwise throws a custom exception marking an invalid property value.
	public void setGender(String gender) throws InvalidCustomerInputException {
		if (validateGender(gender)) {
			this.gender = gender;
		} else {
			throw new InvalidCustomerInputException("Customer gender must be either Male or Female.");
		}
	}

	// Returns the phone number of the customer.
	public String getContact() {
		return contact;
	}

	// Sets a phone number if valid, otherwise throws a custom exception marking an invalid property value.
	public void setContact(String contact) throws InvalidCustomerInputException {
		if (validateContact(contact)) {
			this.contact = contact;
		} else {
			throw new InvalidCustomerInputException("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.");
		}
	}

	// Returns the photo of the customer.
	public byte[] getPhoto() {
		return photo;
	}

	// Sets a photo directly if the photo is given as a byte[].
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	// If a photo is given as a file path, converts the image to a byte[], then sets it.
	public void setPhoto(String path) throws FileNotFoundException, IOException {
		File image = new File(path);
		FileInputStream fis = new FileInputStream(image);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		for (int readNum; (readNum = fis.read(buff)) != -1;) {
			baos.write(buff, 0, readNum);
		}
		fis.close();
		byte[] byteArray = baos.toByteArray();
		photo = byteArray;
	}
}
