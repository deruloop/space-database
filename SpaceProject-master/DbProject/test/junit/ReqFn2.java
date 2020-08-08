package junit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.UserHandler;
import junit.framework.TestCase;

class ReqFn2 extends TestCase {
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
	void testAdmin() throws ClassNotFoundException, SQLException, InterruptedException {
		boolean admin_test = UserHandler.login("spaceproject", "database", con);
		assertEquals(admin_test, true);
		// Permettiamo la visione dell'interfaccia di un admin 
		Thread.sleep(2000);
	}
	
	@Test
	void testUser() throws ClassNotFoundException, SQLException, InterruptedException {
		boolean user_test = UserHandler.login("rossimario", "rossimario", con);
		assertEquals(user_test, true);
		// Permettiamo la visione dell'interfaccia di un utente
		Thread.sleep(2000);
	}
}
