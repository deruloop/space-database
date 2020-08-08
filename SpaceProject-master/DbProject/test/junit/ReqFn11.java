package junit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DBHandler;
import controller.Query;
import junit.framework.TestCase;

class ReqFn11 extends TestCase {
	private static Connection con;
	private final int branch_id = 26;
	private final double distance_vertix_min = 0.0157, distance_vertix_max = 0.0128;
	
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
	void testDistanceVertix() throws SQLException {
		 ArrayList<Double> list = Query.distanceVertix(con, branch_id);
		 assertEquals(distance_vertix_min, list.get(0), 0.0001);
		 assertEquals(distance_vertix_max, list.get(1), 0.0001);
	}	
	
	@Test
	void testFailedDistanceVertix() throws SQLException {
		int branch_id = 25;
		ArrayList<Double> list = Query.distanceVertix(con, branch_id);
		assertTrue(list.size() == 0);
	}	
}
