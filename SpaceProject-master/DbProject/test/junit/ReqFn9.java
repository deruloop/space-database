package junit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.Query;
import junit.framework.TestCase;
import model.StarInfo;

class ReqFn9 extends TestCase {
	private static Connection con;
	private final String filament_name = "HiGALFil005.0602+0.7267"; 
	
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
	void testInfoStarsInFilament() throws SQLException {
		String filament_name = "HiGALFil005.1032-0.9400";
		StarInfo si = Query.infoStarsInFilament(con, filament_name);
		assertTrue(si.getOccurrence_star() == 1);
		assertTrue(si.getPercentual_prestellar() == 100);
	}
	
	@Test
	void testFailedInfoStarsInFilament() throws SQLException {
		StarInfo si = Query.infoStarsInFilament(con, filament_name);
		assertTrue(si.getOccurrence_star() == 0);
	}
}
