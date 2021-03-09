package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.*;

import main.addflight;

class addflightTest {

	static addflight thisFlight;
	
	@BeforeAll
	static void beforeAll() {
		//create new flight before all other tests.
		thisFlight = new addflight();		
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
	/*
	@Test
	void testCreateFlight() {

		String id = "1234";
		String flightname = "Test Flight";
		String source = thisFlight.locations[0];
		String depart = thisFlight.locations[0];
		DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
		String date = da.format(new Date());
		String departtime = "Test D Time";
		String arrtime = "Test A Time"; 
		String flightcharge = "100";
		boolean success = thisFlight.createFlight(id, flightname, source, depart, date, departtime, arrtime, flightcharge);
		
		Assertions.assertTrue(success);
		
		// TODO get from db and ensure its correct
	}
	*/
	
	@Test 
	void testFields() {
		
	}


}
