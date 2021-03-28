package main.Model;

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

	public Address(String streetAddress, String city, String state, String zipCode, String country) throws InvalidAddressInputException {
		super();
		setStreetAddress(streetAddress);
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	public void setStreetAddress(String streetAddress) throws InvalidAddressInputException {
		if (validStreetAddress(streetAddress)) {
			this.streetAddress = streetAddress;
		} else {
			throw new InvalidAddressInputException("Invalid street address");
		}
	}
	
	public boolean validStreetAddress(String streetAddress) {
		return streetAddress.matches(".*\\d.*") && streetAddress.matches(".*[a-zA-Z]+.*");
	}
	
	public String toString() {
		return streetAddress 
				+ city + " " 
				+ state  + " " 
				+ zipCode + " " 
				+ country;
	}
	
}
