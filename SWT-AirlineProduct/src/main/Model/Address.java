package main.Model;

public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;


	public Address(String streetAddress, String city, String state, String zipCode, String country) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	public String toString() {
		return streetAddress 
				+ city + " " 
				+ state  + " " 
				+ zipCode + " " 
				+ country;
	}
	
}
