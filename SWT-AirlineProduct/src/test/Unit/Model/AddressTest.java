package Unit.Model;

import Model.Address;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Model.Address.InvalidAddressInputException;

public class AddressTest {

	Address address;
	
	
	String streetAddress = "123 Main Street";
	String city = "Fort Myers";
	String state = "Florida";
	String zipCode = "33913";
	String country = "United States";
	
	// Positive test
	@BeforeEach
	void beforeEach() throws IOException, InvalidAddressInputException {

		// Create a fresh Address to work with for every test. This ensures all tests are working from the same
		// initial values, which ensures no test results will affect other tests.
		Assertions.assertDoesNotThrow(() -> {
			address = new Address(streetAddress, city, state, zipCode, country);
		});
	}
	
	// Tries to pass a valid street address to the Address class.
	@Test
	@DisplayName("Address, valid")
	void testAddressValid() {
		Assertions.assertDoesNotThrow(() -> address.setStreetAddress("1234 main street"));
	}

	// Tries to pass an invalid street address to the Address class.
	// No numbers
	@Test
	@DisplayName("Address, no numbers (Invalid)")
	void testAddressInvalidNoNumbers() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, 
				() -> address.setStreetAddress("main street"));
		Assertions.assertEquals("Invalid street address", e.getMessage());
	}

	// Tries to pass an invalid city to the Address class.
	// Only numbers
	@Test
	@DisplayName("Address city, empty (Invalid))")
	void testCityInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setCity(""));
		Assertions.assertEquals("Invalid city", e.getMessage());
	}
	

	// Tries to pass an invalid state to the Address class.
	// Only numbers
	@Test
	@DisplayName("Address state, empty (Invalid))")
	void testStateInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setState(""));
		Assertions.assertEquals("Invalid state", e.getMessage());
	}
	

	// Tries to pass an invalid zipCode to the Address class.
	// Only numbers
	@Test
	@DisplayName("Address zipCode, empty (Invalid))")
	void testZipCodeInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setZipCode(""));
		Assertions.assertEquals("Invalid zipCode", e.getMessage());
	}
	
	// Tries to pass an invalid country to the Address class.
	// Only numbers
	@Test
	@DisplayName("Address country, empty (Invalid))")
	void testCountryInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setCountry(""));
		Assertions.assertEquals("Invalid country", e.getMessage());
	}
	
	// Ensure the printed string contains all 
	@Test
	@DisplayName("Address, toString()")
	void testToString() {
		String str = address.toString();
		Assertions.assertTrue(str.contains(streetAddress));
		Assertions.assertTrue(str.contains(city));
		Assertions.assertTrue(str.contains(state));
		Assertions.assertTrue(str.contains(zipCode));
		Assertions.assertTrue(str.contains(country));
	}
	
	
}
