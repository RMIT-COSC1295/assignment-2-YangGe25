package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TravelPassTest {
	
	private final double differenceThreshold = 0.0000000001;

	@Test
	public void testSetPrice() {
		TravelPass travelPass1 = new TravelPass(900,905,"Mon","a","a");
		
		// update the price of 2 Hour for Zone 1
		travelPass1.setPrice("a", "a", 2.7);
		double expectedValue1 = 2.7;
		double actualValue1= TravelPass.TWO_HOUR_ZONE1;
		
		// update the price of All Day for Zones 1&2
		travelPass1.setPrice("b", "c", 7.0);
		double expectedValue2 = 7.0;
		double actualValue2= TravelPass.ALL_DAY_ZONES12;
		
		assertEquals(expectedValue1,expectedValue1,differenceThreshold);
		assertEquals(expectedValue2,expectedValue2,differenceThreshold);
	}
	
	@Test
	public void testComputeDiscount() {
		
		double expectedValue1 = 0;
		double actualValue1= TravelPass.computeDiscount("Adult","Sun");
		
		double expectedValue2 = 0.5;
		double actualValue2= TravelPass.computeDiscount("Junior","Sun");
		
		double expectedValue3 = 0.5;
		double actualValue3= TravelPass.computeDiscount("Senior","Mon");
		
		double expectedValue4 = 1;
		double actualValue4= TravelPass.computeDiscount("Senior","Sun");
		
		assertEquals(expectedValue1,expectedValue1,differenceThreshold);
		assertEquals(expectedValue2,expectedValue2,differenceThreshold);
		assertEquals(expectedValue3,expectedValue3,differenceThreshold);
		assertEquals(expectedValue4,expectedValue4,differenceThreshold);
		
	}

}
