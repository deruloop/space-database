package junit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.Query;
import junit.framework.TestCase;
import model.Position;
import model.StarInfo;

class ReqFn10 extends TestCase {
	private static Connection con;
	private final double height = 0.2, basis = 0.05, lat = 0.08, lon = 5.0;

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
	void testStarsInFilament() throws SQLException {
		Position pos = new Position(lat, lon);
		StarInfo si[] = Query.starsInFilament(con, height, basis, pos);
		assertTrue(si[0].getOccurrence_star() == 1);
		assertTrue(si[1].getOccurrence_star() == 0);
	}
	
	@Test
	void testFailedStarsInFilament() throws SQLException {
		Position pos = new Position(lat, lon);
		double height = 0.005;
		StarInfo si[] = Query.starsInFilament(con, height, basis, pos);
		assertTrue(si[0].getOccurrence_star() == 0);
		assertTrue(si[1].getOccurrence_star() == 0);
	}
}
