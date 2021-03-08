package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.*;

import main.addflight;
import main.ticket;

class addflightTest {

	static addflight thisFlight;
	
	@BeforeAll
	static void beforeAll() {
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
	
	@Test
	void testCreateFlight() {

		String id = "1234";
		String flightname = "Test Flight";
		String source = "Test Source";
		String depart = "Test Depart";
		DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
		String date = da.format(new Date());
		String departtime = "Test D Time";
		String arrtime = "Test A Time"; 
		String flightcharge = "100";
		boolean success = thisFlight.createFlight(id, flightname, source, depart, date, departtime, arrtime, flightcharge);
		

		Assertions.assertTrue(success);
		
		// TODO get from db and ensure its correct
	}
	
	@Test 
	void testAutoID() {
		thisFlight.autoID();
		
	}

	@Test 
	void testFields() {
		
	}


}
