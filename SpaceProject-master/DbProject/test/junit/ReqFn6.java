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

class ReqFn6 extends TestCase {
	private static Connection con;
	private final double brilliance = 826.0, ellptc_A = 2.0, ellptc_B = 7.0;
	private final int limit = 20, offset = 0;
	private final String filament_name = "SDC333.291-0.421";
	
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
	void testSearchFilamentByContrastEllipticity() throws SQLException {
		ArrayList<Filament> list = Query.searchFilamentByContrastEllipticity(con, brilliance, ellptc_A, ellptc_B, limit, offset);
		assertTrue(list.size() == 1);
		assertEquals(filament_name, list.get(0).getName());
	}
	
	@Test
	void testFailedSearchFilamentByContrastEllipticity() throws SQLException {
		double brilliance = 830;
		ArrayList<Filament> list = Query.searchFilamentByContrastEllipticity(con, brilliance, ellptc_A, ellptc_B, limit, offset);
		assertTrue(list.size() == 0);
	}

}
