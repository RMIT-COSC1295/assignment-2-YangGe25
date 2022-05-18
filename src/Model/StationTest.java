package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StationTest {
	private Station station1;
	
	@Before
	public void setUp() {
		station1 = new Station("testStation",1);
	}
	/*
	 *  Every time addStartCount is called, the startCount of the specific station will plus 1 
	 */
	@Test
	public void testAddStartCount() {
		station1.addStation("testStation",1);
		
		// call addStartCount() 3 times
		station1.addStartCount("testStation");
		station1.addStartCount("testStation");
		station1.addStartCount("testStation");
		
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
		station1.addStation("testStation",1);
		
		// call addEndCount() 2 times
		station1.addEndCount("testStation");
		station1.addEndCount("testStation");
		
		// The expected value is 2
		int expectedCount = 2;
		int actualCount = Station.stations.get("testStation").getEndCount();
		
		assertEquals(expectedCount,actualCount);
	}

}
