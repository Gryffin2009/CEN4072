package Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Customer {

	/**
	 *  A custom exception to handle any invalid input to all of the properties of the Customer class.
	 */
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
	private Address address;
	private String dob;
	private String gender;
	private String phoneNumber;
	private byte[] photo;

	/** Constructor with the image as a byte[] array, which is how the image is stored in the database.
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param nic
	 * @param passport
	 * @param address
	 * @param dob
	 * @param gender
	 * @param contact
	 * @param photo
	 * @throws InvalidCustomerInputException
	 */
	public Customer(String id, String firstname, String lastname, String nic, String passport,
			Address address, String dob, String gender, String contact, byte[] photo) throws InvalidCustomerInputException {
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setNic(nic);
		setPassport(passport);
		setAddress(address);
		setDob(dob);
		setGender(gender);
		setPhoneNumber(contact);
		setPhotoFromPath(photo);
	}

	/**
	 * Constructor with image path, which then converts the image to a byte[] array so it can be stored in the database.
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param nic
	 * @param passport
	 * @param address
	 * @param dob
	 * @param gender
	 * @param contact
	 * @param photo
	 * @throws InvalidCustomerInputException
	 * @throws IOException
	 */
	public Customer(String id, String firstname, String lastname, String nic, String passport,
			Address address, String dob, String gender, String contact, String photo) throws InvalidCustomerInputException, IOException {
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setNic(nic);
		setPassport(passport);
		setAddress(address);
		setDob(dob);
		setGender(gender);
		setPhoneNumber(contact);
		setPhoto(photo);
	}

	/**
	 * setPhoneNumber
	 * Ensures the input string is valid before setting the class property.
	 * @param phoneNumber
	 * @throws InvalidCustomerInputException
	 */
	public void setPhoneNumber(String phoneNumber) throws InvalidCustomerInputException {
		if (validatePhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidCustomerInputException("Invalid Phone Number");
		}
	}

	/**
	 * getPhoneNumber returns the phone number associated with the Customer.
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Ensures a phone number consists only of numbers and has 7-13 digits.
	 * @param phoneNumber
	 * @return A boolean that represents if the input string is a valid phoneNumber.
	 */
	private boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber.matches("^[0-9]{7,13}$");
	}

	/**
	 * Ensures a name contains only letters, dashes, and/or apostrophes, as well as at least 1 character.
	 * @param name
	 * @return
	 */
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z'-]+$");
	}

	/**
	 * Ensures a NIC contains only letters and numbers and at least 1 character.
	 * @param nic
	 * @return A boolean that represents if the input string is a valid nic.
	 */
	private boolean validateNic(String nic) {
		return nic.matches("^[a-zA-Z0-9]+$");
	}

	/**
	 * Ensures a passport number contains only letters and numbers and at least 1 character.
	 * @param passport
	 * @return A boolean that represents if the input string is a valid passport number.
	 */
	private boolean validatePassport(String passport) {
		return passport.matches("^[a-zA-Z0-9]+$");
	}

	/**
	 * Ensures a date of birth is in the format YYY-MM-DD.
	 * @param dob
	 * @return A boolean that represents if the input string is a valid dob.
	 */
	private boolean validateDob(String dob) {
		return dob.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	}

	/**
	 * Ensures a gender is either Male or Female.
	 * @param gender
	 * @return A boolean that represents if the input string is a valid gender.
	 */
	private boolean validateGender(String gender) {
		if (gender.contains("Male") || gender.contains("Female")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns the customer ID.
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets an ID if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the first name of the customer.
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets a first name if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param firstname
	 * @throws InvalidCustomerInputException
	 */
	public void setFirstname(String firstname) throws InvalidCustomerInputException {
		if (validateName(firstname)) {
			this.firstname = firstname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	/**
	 * Returns the last name of the customer.
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets a last name if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param lastname
	 * @throws InvalidCustomerInputException
	 */
	public void setLastname(String lastname) throws InvalidCustomerInputException {
		if (validateName(lastname)) {
			this.lastname = lastname;
		} else {
			throw new InvalidCustomerInputException("Customer name must contain alphabetic characters only.");
		}
	}

	/**
	 * Returns the NIC of the customer.
	 * @return nic
	 */
	public String getNic() {
		return nic;
	}

	/**
	 * Sets the NIC if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param nic
	 * @throws InvalidCustomerInputException
	 */
	public void setNic(String nic) throws InvalidCustomerInputException {
		if (validateNic(nic)) {
			this.nic = nic;
		} else {
			throw new InvalidCustomerInputException("Customer NIC must contain alphanumeric characters only.");
		}
	}

	/**
	 * Returns the passport number of the customer.
	 * @return passport
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * Sets a passport number if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param passport
	 * @throws InvalidCustomerInputException
	 */
	public void setPassport(String passport) throws InvalidCustomerInputException {
		if (validatePassport(passport)) {
			this.passport = passport;
		} else {
			throw new InvalidCustomerInputException("Customer Passport must contain alphanumeric characters only.");
		}
	}

	/**
	 * Returns the address of the customer.
	 * @return address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Returns the address of the customer.
	 * @return A string representation of the full address.
	 */
	public String getAddressAsString() {
		return address.toString();
	}

	/**
	 * Sets the address of the customer after ensuring it is not null.
	 * @param address
	 * @throws InvalidCustomerInputException
	 */
	public void setAddress(Address address) throws InvalidCustomerInputException {
		if (address != null) {
			this.address = address;
		} else {
			throw new InvalidCustomerInputException("Invalid address.");
		}
	}

	/**
	 * Returns the date of birth of the customer.
	 * @return dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets a date of birth if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param dob
	 * @throws InvalidCustomerInputException
	 */
	public void setDob(String dob) throws InvalidCustomerInputException {
		if (validateDob(dob)) {
			this.dob = dob;
		} else {
			throw new InvalidCustomerInputException("Customer date of birth must be in the following format: YYYY-MM-DD.");
		}
	}

	/**
	 * Returns the gender of the customer.
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets a gender if valid, otherwise throws a custom exception marking an invalid property value.
	 * @param gender
	 * @throws InvalidCustomerInputException
	 */
	public void setGender(String gender) throws InvalidCustomerInputException {
		if (validateGender(gender)) {
			this.gender = gender;
		} else {
			throw new InvalidCustomerInputException("Customer gender must be either Male or Female.");
		}
	}

	/**
	 * Returns the photo of the customer.
	 * @return photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * Sets a photo directly if the photo is given as a byte[].
	 * @param photo
	 */
	public void setPhotoFromPath(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * If a photo is given as a file path, converts the image to a byte[], then sets it.
	 * @param path
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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
