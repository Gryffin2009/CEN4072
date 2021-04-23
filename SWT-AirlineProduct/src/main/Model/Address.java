package Model;

public class Address {
	/** InvalidAddressInputException
	 *  A customer Exception class to handle invalid address input.
	 */
	public class InvalidAddressInputException extends Exception {
		public InvalidAddressInputException(String message) {
			super(message);
		}
	}

	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;

	/**
	 * Address
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 * @throws InvalidAddressInputException
	 */
	public Address(String streetAddress, String city, String state, String zipCode, String country)
			throws InvalidAddressInputException {
		super();
		setStreetAddress(streetAddress);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setCountry(country);
	}

	/**
	 * validStreetAddress
	 * @param streetAddress
	 * @return a boolean that represents if streetAddress is in the correct format
	 */
	public boolean validStreetAddress(String streetAddress) {
		return streetAddress.matches(".*\\d.*") && streetAddress.matches(".*[a-zA-Z]+.*");
	}

	/**
	 * setStreetAddress
	 * Ensures the street address is valid before setting the class property.
	 * @param streetAddress
	 * @throws InvalidAddressInputException
	 */
	public void setStreetAddress(String streetAddress) throws InvalidAddressInputException {
		if (validStreetAddress(streetAddress)) {
			this.streetAddress = streetAddress;
		} else {
			throw new InvalidAddressInputException("Invalid street address");
		}
	}

	/**
	 * getStreetAddress()
	 * @return the streetAddress class property value
	 */
	public String getStreetAddress() { return streetAddress; }

	/**
	 * setCity(String city)
	 * Ensures the city is valid before setting the class property.
	 * @param city
	 * @throws InvalidAddressInputException
	 */
	public void setCity(String city) throws InvalidAddressInputException {
		if (!city.isEmpty()) {
			this.city = city;
		} else {
			throw new InvalidAddressInputException("Invalid city");
		}
	}

	/**
	 * getCity()
	 * @return the city class property value
	 */
	public String getCity() { return city;}

	/**
	 * setState(String state)
	 * Ensures the state is valid before setting the class property.
	 * @param state
	 * @throws InvalidAddressInputException
	 */
	public void setState(String state) throws InvalidAddressInputException {
		if (!state.isEmpty()) {
			this.state = state;
		} else {
			throw new InvalidAddressInputException("Invalid state");
		}
	}

	/**
	 * getState()
	 * @return the state class property value
	 */
	public String getState() { return state;}

	/**
	 * setZipCode(String zipCode)
	 * Ensures the zipCode is valid before setting the class property.
	 * @param zipCode
	 * @throws InvalidAddressInputException
	 */
	public void setZipCode(String zipCode) throws InvalidAddressInputException {
		if (!zipCode.isEmpty()) {
			this.zipCode = zipCode;
		} else {
			throw new InvalidAddressInputException("Invalid zipCode");
		}
	}

	/**
	 * getZipCode()
	 * @return the zipCode class property value
	 */
	public String getZipCode() { return zipCode;}

	/**
	 * setZipCode(String country)
	 * Ensures the zipCode is valid before setting the class property.
	 * @param country
	 * @throws InvalidAddressInputException
	 */
	public void setCountry(String country) throws InvalidAddressInputException {
		if (!country.isEmpty()) {
			this.country = country;
		} else {
			throw new InvalidAddressInputException("Invalid country");
		}
	}

	/**
	 * getCountry()
	 * @return the country class property value
	 */
	public String getCountry() { return country;}

	/**
	 * toString()
	 * @return A string representation of the full address.
	 */
	public String toString() {
		return getStreetAddress() + " " + getCity() + " " + getState() + " " + getZipCode() + " " + getCountry();
	}

}
