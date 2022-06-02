package MyTicket;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import Exceptions.InsufficientCredit;

public class FullMyTiTest {
	private final double differenceThreshold = 0.0000000001;
	
	@Before
	public void setUp() throws Exception {
		FullMyTi.addTicket("lc");
	}
	
	/*
	 *  Top Up the ticket on the back end 
	 *  No limits on the amount, the front end is responsible for checking if the amount is valid 
	 */
	@Test
	public void testTopUp() {
		
		FullMyTi.topUp("lc",30);
		
		double expectedValue = 30;
		double actualValue= FullMyTi.fullMyTis.get("lc").getCredit();
		
		assertEquals(expectedValue,actualValue,differenceThreshold);
	}
	
	/*
	 *  The credit is 30, and the cost is 20 
	 *  The purchase should happen
	 */
	@Test
	public void testBuy1() throws InsufficientCredit {
		
		FullMyTi.topUp("lc",30);
		FullMyTi.buy("lc",20);
		
		double expectedValue = 10;
		double actualValue= FullMyTi.fullMyTis.get("lc").getCredit();
		
		assertEquals(expectedValue,actualValue,differenceThreshold);
	}
	
	/*
	 *  The credit is 30, and the cost is 40 
	 *  The purchase should not happen
	 */
	@Test(expected=InsufficientCredit.class)
	public void testBuy2() throws InsufficientCredit {
		
		FullMyTi.topUp("lc",30);
		FullMyTi.buy("lc",40);
	}

}
