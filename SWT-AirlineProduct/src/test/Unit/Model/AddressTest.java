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

	/* Tries to pass a valid street address to the Address class.
	 * Test Case ID: AddressTC-1
	 * Requirement ID/Description: A-1 An address shall contain a valid street address. A valid street
	 * 		address shall contain a building number followed by a street name. It may optionally end in
	 * 		an apartment number.
	 * Purpose: To test a valid street address input
	 * Test setup: an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: positive testing
	 * Input: Data type String: 1234 main street
	 * Expected Output: Accepts the input.
	 */
	@Test
	@DisplayName("Address, valid")
	void testAddressValid() {
		Assertions.assertDoesNotThrow(() -> address.setStreetAddress("1234 main street"));
	}


	// Tries to pass an invalid street address to the Address class.
	// No numbers
	/*
	 * Test Case ID: AddressTC-2
	 * Requirement ID/Description: A-1 An address shall contain a valid street address. A valid street
	 * 		address shall contain a building number followed by a street name. It may optionally end in
	 * 		an apartment number.
	 * Purpose: To test an invalid street address.
	 * Test setup:  an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: Negative Testing
	 * Input: string: main street
	 * Expected Output: Does not accept the input.
	 */
	@Test
	@DisplayName("Address, no numbers (Invalid)")
	void testAddressInvalidNoNumbers() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class,
				() -> address.setStreetAddress("main street"));
		Assertions.assertEquals("Invalid street address", e.getMessage());
	}

	// Tries to pass an invalid city to the Address class.
	// Only numbers
	/*
	 * Test Case ID:AddressTC-3
	 * Requirement ID/Description: A-2 An address shall contain a valid city. A valid city shall
	 * 		contain only alphabetical input.
	 * Purpose: To test an invalid city.
	 * Test setup:   an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: negative testing
	 * Input: empty string.
	 * Expected Output: Does not accept the input.
	 */
	@Test
	@DisplayName("Address city, empty (Invalid))")
	void testCityInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setCity(""));
		Assertions.assertEquals("Invalid city", e.getMessage());
	}


	// Tries to pass an invalid state to the Address class.
	// Only numbers
	/*
	 * Test Case ID: Address TC-4
	 * Requirement ID/Description: A-3 An address shall contain a valid state. A valid state shall
	 * 		contain only alphabetical input.
	 * Purpose: To test an invalid city.
	 * Test setup:   an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: negative testing
	 * Input: empty string.
	 * Expected Output: Does not accept the input.
	 */
	@Test
	@DisplayName("Address state, empty (Invalid))")
	void testStateInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setState(""));
		Assertions.assertEquals("Invalid state", e.getMessage());
	}


	// Tries to pass an invalid zipCode to the Address class.
	// Only numbers
	/*
	 * Test Case ID: Address TC-5
	 * Requirement ID/Description: A-4 An address shall contain a valid zip code. A valid zip code
	 * 		shall only contain numeric input.
	 * Purpose: To test an invalid zip code.
	 * Test setup:   an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: negative testing
	 * Input: empty string.
	 * Expected Output: Does not accept the input.
	 */
	@Test
	@DisplayName("Address zipCode, empty (Invalid))")
	void testZipCodeInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setZipCode(""));
		Assertions.assertEquals("Invalid zipCode", e.getMessage());
	}

	// Tries to pass an invalid country to the Address class.
	// Only numbers
	/*
	 * Test Case ID: Address TC-6
	 * Requirement ID/Description: A-5 An address shall contain a valid zip code. A valid zip code
	 * 		shall only contain numeric input.
	 * Purpose: To test an invalid country.
	 * Test setup:   an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: negative testing
	 * Input: empty string.
	 * Expected Output: Does not accept the input.
	 */
	@Test
	@DisplayName("Address country, empty (Invalid))")
	void testCountryInvalid() throws InvalidAddressInputException, Exception {
		InvalidAddressInputException e = Assertions.assertThrows(InvalidAddressInputException.class, () -> address.setCountry(""));
		Assertions.assertEquals("Invalid country", e.getMessage());
	}

	// Ensure the printed string contains all
	/*
	 * Test Case ID: Address TC-7
	 * Requirement ID/Description: A-6 An address shall contain the following components: street
	 * 		address, city, state, zip code, and country.
	 * Purpose: To ensure address object contains all components stated in A-6.
	 * Test setup:   an address object is created with the following values: 123 Main Street, Fort Myers,
	 * 		Florida, 33913, United States
	 * Test Strategy: positive testing
	 * Input: empty string.
	 * Expected Output: Address object contains all components.
	 */
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
