package junit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.UserHandler;
import junit.framework.TestCase;

class ReqFn3 extends TestCase {
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
	void testRegister() throws SQLException {
		boolean reg_test = UserHandler.register("Giuseppe", "Bianchi", "bianchigiuseppe", "bianchigiuseppe", "bianchi@gmail.com", false, con);
		assertEquals(reg_test, true);
	}
	@Test
	void testRegisterEmailInUse() throws SQLException {
		boolean reg_test = UserHandler.register("Mario", "Rossi", "rossimario12", "rossimario", "cris.cali@hotmail.com", false, con);
		assertEquals(reg_test, false);
	}
	@Test
	void testRegisterUserInUse() throws SQLException {
		boolean reg_test = UserHandler.register("Mario", "Rossi", "spaceproject", "rossimario", "rossi@hotmail.com", false, con);
		assertEquals(reg_test, false);
	}

}
