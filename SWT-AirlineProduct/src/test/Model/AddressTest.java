package test.Model;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import main.Model.Address;
import main.Model.Customer.InvalidCustomerInputException;

public class AddressTest {

	Address address;
	

	@BeforeAll
	static void beforeAll() {

	}
	
	@BeforeEach
	void beforeEach() throws InvalidCustomerInputException, IOException {

		String streetAddress = "123 Main Street";
		String city = "Fort Myers";
		String state = "Florida";
		String zipCode = "33913";
		String country = "United States";
		
		// Create a fresh Address to work with for every test. This ensures all tests are working from the same
		// initial values, which ensures no test results will affect other tests.
		address = new Address(streetAddress, city, state, zipCode, country);
	}
	
}
