package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.UserExists;

public class UserTest {	
	
	/*
	 *  Add valid users
	 */
	@Test
	public void testAddUser1() throws UserExists {
		User.addUser("lc", "Lawrence Cavedon",  "Senior", "lawrence.cavedon@rmit.edu.au");
		User.addUser("vm", "Vu Mai", "Adult", "vuhuy.mai@rmit.edu.au");
	}
	
	
	/*
	 *  The user ID must be unique
	 *  Adding a new user with existing ID will result in exception
	 */
	@Test(expected=UserExists.class)
	public void testAddUser2() throws UserExists {
		User.addUser("lc", "aaa", "Adult", "aaaaaaaaa");
	}
	
	@Test
	public void testGetUserType1() {
		
		String expectedValue1 = "Senior";
		String actualValue1 = User.getUserType("lc");
		
		assertEquals(expectedValue1,actualValue1);
	}
	
	@Test
	public void testGetUserType2() {
		
		String expectedValue2 = "Adult";
		String actualValue2 = User.getUserType("vm");
		
		assertEquals(expectedValue2,actualValue2);
	}

}
