import org.junit.jupiter.api.*;

public class TestAddCustomer {

	static addCustomer thisAddCustomer;
	
	@BeforeAll
	static void beforeAll() {
		thisAddCustomer = new addCustomer();		
	}
	
	@BeforeEach
	void beforeEach() {
		
	}
	
	@AfterEach
	void afterEach() {
		
	}
	
	@AfterAll
	static void afterAll() {
		
	}

	@Test
	@DisplayName("Customer name, numbers in name")
	public void testInvalidCustomerName() {
		boolean isAlphabeticOnly = thisAddCustomer.validateCustomerName("John290");
		Assertions.assertEquals(false, isAlphabeticOnly);
	}

	@Test
	@DisplayName("Customer name, only alphabetic characters in name")
	public void testValidCustomerName() {
		boolean isAlphabeticOnly = thisAddCustomer.validateCustomerName("John");
		Assertions.assertEquals(true, isAlphabeticOnly);
	}
	
	
}
