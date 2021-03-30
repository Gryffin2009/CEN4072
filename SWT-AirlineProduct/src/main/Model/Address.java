package Model;

public class Address {

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

	public Address(String streetAddress, String city, String state, String zipCode, String country)
			throws InvalidAddressInputException {
		super();
		setStreetAddress(streetAddress);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setCountry(country);
	}

	public boolean validStreetAddress(String streetAddress) {
		return streetAddress.matches(".*\\d.*") && streetAddress.matches(".*[a-zA-Z]+.*");
	}

	public void setStreetAddress(String streetAddress) throws InvalidAddressInputException {
		if (validStreetAddress(streetAddress)) {
			this.streetAddress = streetAddress;
		} else {
			throw new InvalidAddressInputException("Invalid street address");
		}
	}

	public void setCity(String city) throws InvalidAddressInputException {
		if (!city.isEmpty()) {
			this.city = city;
		} else {
			throw new InvalidAddressInputException("Invalid city");
		}
	}

	public void setState(String state) throws InvalidAddressInputException {
		if (!state.isEmpty()) {
			this.state = state;
		} else {
			throw new InvalidAddressInputException("Invalid state");
		}
	}

	public void setZipCode(String zipCode) throws InvalidAddressInputException {
		if (!zipCode.isEmpty()) {
			this.zipCode = zipCode;
		} else {
			throw new InvalidAddressInputException("Invalid zipCode");
		}
	}

	public void setCountry(String country) throws InvalidAddressInputException {
		if (!country.isEmpty()) {
			this.country = country;
		} else {
			throw new InvalidAddressInputException("Invalid country");
		}
	}

	public String toString() {
		return streetAddress + city + " " + state + " " + zipCode + " " + country;
	}

}
