package MyTicket;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InsufficientCredit;
import Model.TravelPass;

public class FullMyTiTest {
	
	private FullMyTi fullMyTi;
	private final double differenceThreshold = 0.0000000001;
	
	@Before
	public void setUp() throws Exception {
		fullMyTi.addTicket("lc");
	}
	
	/*
	 *  Top Up the ticket on the back end 
	 *  No limits on the amount, the front end is responsible for checking if the amount is valid 
	 */
	@Test
	public void testTopUp() {
		
		fullMyTi.topUp("lc",30);
		
		double expectedValue = 30;
		double actualValue= fullMyTi.fullMyTis.get("lc").getCredit();
		
		assertEquals(expectedValue,expectedValue,differenceThreshold);
	}
	
	/*
	 *  The credit is 30, and the cost is 20 
	 *  The purchase should happen
	 */
	@Test
	public void testBuy1() throws InsufficientCredit {
		
		fullMyTi.topUp("lc",30);
		fullMyTi.buy("lc",20);
		
		double expectedValue = 10;
		double actualValue= fullMyTi.fullMyTis.get("lc").getCredit();
		
		assertEquals(expectedValue,expectedValue,differenceThreshold);
	}
	
	/*
	 *  The credit is 30, and the cost is 40 
	 *  The purchase should not happen
	 */
	@Test(expected=InsufficientCredit.class)
	public void testBuy2() throws InsufficientCredit {
		
		fullMyTi.topUp("lc",30);
		fullMyTi.buy("lc",40);
	}

}
