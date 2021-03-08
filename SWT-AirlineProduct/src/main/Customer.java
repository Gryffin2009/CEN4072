package main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Customer {

	public class InvalidCustomerInputException extends Exception {
		public InvalidCustomerInputException(String message) {
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
	
	private boolean validateId(String id) {
		return id.matches("^CS[0-9]{3}$");
	}
	
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z]+$");
	}
	
	private boolean validateNic(String nic) {
		return nic.matches("^[a-zA-Z0-9]+$");
	}
	
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
	
	private boolean validateDob(String dob) {
		return dob.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");		
	}

	private boolean validateGender(String gender) {
		if (gender == "Male" || gender == "Female") {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateContact(String contact) {
		return contact.matches("^[0-9]{7}$");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) throws InvalidCustomerInputException {
		if (validateId(id)) {
			this.id = id;
		} else {
			throw new InvalidCustomerInputException("ID must be in the format \"CS###\".");
		}
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) throws InvalidCustomerInputException {
		if (validateName(firstname)) {
			this.firstname = firstname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) throws InvalidCustomerInputException {
		if (validateName(lastname)) {
			this.lastname = lastname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) throws InvalidCustomerInputException {
		if (validateNic(nic)) {
			this.nic = nic;
		} else {
			throw new InvalidCustomerInputException("Customer NIC must contain alphanumeric characters only.");
		}
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) throws InvalidCustomerInputException {
		if (validatePassport(passport)) {
			this.passport = passport;
		} else {
			throw new InvalidCustomerInputException("Customer Passport must contain alphanumeric characters only.");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidCustomerInputException {
		
		//TODO Make an Address class!
		if (address == address) {
			this.address = address;
		} else {
			throw new InvalidCustomerInputException("");
		}
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) throws InvalidCustomerInputException {
		if (validateDob(dob)) {
			this.dob = dob;
		} else {
			throw new InvalidCustomerInputException("Customer date of birth must be in the following format: YYYY-MM-DD.");
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) throws InvalidCustomerInputException {
		if (validateGender(gender)) {
			this.gender = gender;
		} else {
			throw new InvalidCustomerInputException("Customer gender must be either Male or Female.");
		}
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) throws InvalidCustomerInputException {
		if (validateContact(contact)) {
			this.contact = contact;
		} else {
			throw new InvalidCustomerInputException("Customer phone number must be 7 numeric characters with no separators, i.e. 1234567.");
		}
	}

	public byte[] getPhoto() {
		return photo;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

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
	
	
	private byte[] imageToByteArray(String path) {

		try {
			File image = new File(path);
			FileInputStream fis = new FileInputStream(image);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			for (int readNum; (readNum = fis.read(buff)) != -1;) {
				baos.write(buff, 0, readNum);
			}
			fis.close();
			byte[] byteArray = baos.toByteArray();
			return byteArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
