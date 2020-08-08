package junit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.UserHandler;
import junit.framework.TestCase;

class ReqFn1 extends TestCase {
	private static Connection con;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DBHandler handl = new DBHandler();
		con = handl.getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
		con.close();
	}

	@Test
	void testLoginAccessGained() throws ClassNotFoundException, SQLException {
		boolean log_test = UserHandler.login("spaceproject", "database", con);
		assertEquals(log_test, true);
	}
	@Test
	void testLoginUserNotExist() throws ClassNotFoundException, SQLException {
		boolean log_test = UserHandler.login("DigitalThief", "stealall", con);
		assertEquals(log_test, false);
	}
	@Test
	void testLoginPassIncorrect() throws ClassNotFoundException, SQLException {
		boolean log_test = UserHandler.login("spaceproject", "stealall", con);
		assertEquals(log_test, false);
	}
}
