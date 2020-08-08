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
import model.Filament;

class ReqFn7 extends TestCase {
	private static Connection con;
	private final int min_range = 155, max_range = 158;
	private final int limit = 20, offset = 0;
	private final String filament_name = "SDC43.207-0.013";
	
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
	void testSearchFilamentByRange() throws SQLException {
		ArrayList<Filament> list = Query.searchFilamentByRange(con, min_range, max_range, limit, offset);
		assertTrue(list.size() == 1);
		assertEquals(filament_name, list.get(0).getName());
	}
	
	@Test
	void testFailedSearchFilamentByRange() throws SQLException {
		int min_range = 235, max_range = 238;
		ArrayList<Filament> list = Query.searchFilamentByRange(con, min_range, max_range, limit, offset);
		assertTrue(list.size() == 0);
	}
	

}
