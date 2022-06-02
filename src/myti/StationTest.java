package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {
	
	/*
	 *  Every time addStartCount is called, the startCount of the specific station will plus 1 
	 */
	@Test
	public void testAddStartCount() {
		Station.addStation("testStation",1);
		
		// call addStartCount() 3 times
		Station.addStartCount("testStation");
		Station.addStartCount("testStation");
		Station.addStartCount("testStation");
		
		// The expected value is 3
		int expectedCount = 3;
		int actualCount = Station.stations.get("testStation").getStartCount();
		
		assertEquals(expectedCount,actualCount);
	}
	
	/*
	 *  Every time addEndCount is called, the endCount of the specific station will plus 1 
	 */
	@Test
	public void testAddEndCount() {
		Station.addStation("testStation",1);
		
		// call addEndCount() 2 times
		Station.addEndCount("testStation");
		Station.addEndCount("testStation");
		
		// The expected value is 2
		int expectedCount = 2;
		int actualCount = Station.stations.get("testStation").getEndCount();
		
		assertEquals(expectedCount,actualCount);
	}
	
	@Test
	public void testGetStationZone1() {
		
		Station.addStation("testStation1",1);
		
		int expectedValue1 = 1;
		int actualValue1 = Station.getStationZone("testStation1");
		
		assertEquals(expectedValue1,actualValue1);
	}
	
	@Test
	public void testGetStationZone2() {
		
		Station.addStation("testStation2",2);
		
		int expectedValue2 = 2;
		int actualValue2 = Station.getStationZone("testStation2");
		
		assertEquals(expectedValue2,actualValue2);
	}

}
