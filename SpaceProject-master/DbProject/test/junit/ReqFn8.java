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
import model.Position;

class ReqFn8 extends TestCase {
	private static Connection con;
	private final String filament_name = "HiGALFil015.9322-1.0422"; 
	private final double radius = 0.15, side = .2;
		
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
	void testFindFilamentCircle() throws SQLException {
        Position p = Query.getCentroid(con, filament_name);
        ArrayList<Filament> list = Query.findFilamentCircle(con, radius, p);
		assertTrue(list.size() == 1);
		assertEquals(filament_name, list.get(0).getName());
    }
	@Test
	void testFailedFindFilamentCircle() throws SQLException {
        Position p = Query.getCentroid(con, filament_name);
        double radius = 0.1;
        ArrayList<Filament> list = Query.findFilamentCircle(con, radius, p);
		assertTrue(list.size() == 0);
    }
	
	// FAIL TEST
	@Test
	void testFindFilamentSquare() throws SQLException {
        Position p = Query.getCentroid(con, filament_name);
        ArrayList<Filament> list = Query.findFilamentSquare(con, side, p);
		assertTrue(list.size() == 1);
		assertEquals(filament_name, list.get(0).getName());
	}

	@Test
	void testFailedFindFilamentSquare() throws SQLException {
        Position p = Query.getCentroid(con, filament_name);
        double side = 0.1;
        ArrayList<Filament> list = Query.findFilamentSquare(con, side, p);
		assertTrue(list.size() == 0);
	}
	

}
