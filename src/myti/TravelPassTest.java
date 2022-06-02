package Model;

import static org.junit.Assert.*;
import org.junit.Test;
import MyTicket.SeniorMyTi;

public class TravelPassTest {
	
	private final double differenceThreshold = 0.0000000001;
	/*
	 *  Test on update the ticket price
	 */
	@Test
	public void testSetPrice() {
		
		// update the price of 2 Hour for Zone 1
		TravelPass.setPrice("a", "a", 2.7);
		double expectedValue1 = 2.7;
		double actualValue1= TravelPass.TWO_HOUR_ZONE1;
		
		// update the price of All Day for Zones 1&2
		TravelPass.setPrice("b", "c", 7.0);
		double expectedValue2 = 7.0;
		double actualValue2= TravelPass.ALL_DAY_ZONES12;
		
		assertEquals(expectedValue1,actualValue1,differenceThreshold);
		assertEquals(expectedValue2,actualValue2,differenceThreshold);
	}
	
	/*
	 *  Test on getting the discountRate
	 */
	@Test
	public void testComputeDiscount() {
		
		double expectedValue1 = 0;
		double actualValue1= TravelPass.computeDiscount("Adult","Sun");
		
		double expectedValue2 = 0.5;
		double actualValue2 = TravelPass.computeDiscount("Junior","Sun");
		
		double expectedValue3 = 0.5;
		double actualValue3= TravelPass.computeDiscount("Senior","Mon");
		
		double expectedValue4 = 1;
		double actualValue4= TravelPass.computeDiscount("Senior","Sun");
		
		assertEquals(expectedValue1,actualValue1,differenceThreshold);
		assertEquals(expectedValue2,actualValue2,differenceThreshold);
		assertEquals(expectedValue3,actualValue3,differenceThreshold);
		assertEquals(expectedValue4,actualValue4,differenceThreshold);
	}
	
	/*
	 *  Buy a new Travel Pass for lc 
	 *  The expected result is purchaseFail
	 *  So if the purchased is successful, the result should be false
	 */
	@Test
	public void testBuyNewPass1() {
		
		SeniorMyTi.addTicket("lc");
		SeniorMyTi.topUp("lc", 20);
		
		boolean expectedValue1 = false;
		boolean actualValue1= TravelPass.buyNewPass("lc","Senior","a","a",1.25);
		assertEquals(expectedValue1,actualValue1);
	}
	
	/*
	 *  Buy a new Travel Pass for lc 
	 *  In this case, lc doesn't have enough credit
	 *  So the purchased failed, the result should be true
	 */
	@Test
	public void testBuyNewPass() {
		
		SeniorMyTi.addTicket("lc");
		SeniorMyTi.topUp("lc", 0);
		
		boolean expectedValue2 = true;
		boolean actualValue2= TravelPass.buyNewPass("lc","Senior","a","a",1.25);
		assertEquals(expectedValue2,actualValue2);
	}
	
	/*
	 *  Suppose lc has purchased for 2 Hour Zone 1 Pass
	 *  check validity if he travels in Zone in 2 hours
	 */
	@Test
	public void testCheckValidity1() {
		
		TravelPass travelPass = new TravelPass(900,1100,"Mon","a","a");
		TravelPass.purchases.put("lc", travelPass);
		
		boolean expectedValue1 = true;
		boolean actualValue1 = TravelPass.checkValidity("lc","a","a",935);
		assertEquals(expectedValue1,actualValue1);
	}
	
	/*
	 *  Suppose lc has purchased for 2 Hour Zone 1 Pass
	 *  check validity if he travels in Zone in more than 2 hours
	 */
	@Test
	public void testCheckValidity2() {
		
		TravelPass travelPass = new TravelPass(900,905,"Mon","a","a");
		TravelPass.purchases.put("lc", travelPass);
		
		boolean expectedValue2 = false;
		boolean actualValue2 = TravelPass.checkValidity("lc","a","a",1305);
		assertEquals(expectedValue2,actualValue2);
	}
	
	/*
	 *  Upgrade 2 Hour Pass to All Day Pass for lc
	 *  The result is purchaseFail, so it is false when purchase succeeds
	 */
	@Test
	public void testUpgradePass() {
		
		SeniorMyTi.addTicket("lc");
		SeniorMyTi.topUp("lc", 20);
		
		TravelPass travelPass = new TravelPass(900,905,"Mon","a","a");
		TravelPass.purchases.put("lc", travelPass);
		
		boolean expectedValue = false;
		boolean actualValue = TravelPass.upgradePass("lc", "Senior", "a", "a", 0.5,1305);
		assertEquals(expectedValue,actualValue);
	}

	@Test
	public void testComputePeriod1() {
		String expectedValue1 = "a";
		String actualValue1 = TravelPass.computePeriod(900,905);
		assertEquals(expectedValue1,actualValue1);
	}
	
	@Test
	public void testComputePeriod2() {
		String expectedValue2 = "b";
		String actualValue2 = TravelPass.computePeriod(900,1300);
		assertEquals(expectedValue2,actualValue2);
	}
	
	@Test
	public void testComputeZones1() {
		Station.addStation("Central", 1);
		Station.addStation("Richmond", 1);
		
		String expectedValue1 = "a";
		String actualValue1 = TravelPass.computeZones("Central","Richmond");
		
		assertEquals(expectedValue1,actualValue1);
	}
	
	@Test
	public void testComputeZones2() {
		Station.addStation("Central", 1);
		Station.addStation("Epping", 2);
		
		String expectedValue2 = "c";
		String actualValue2 = TravelPass.computeZones("Central","Epping");
		
		assertEquals(expectedValue2,actualValue2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
