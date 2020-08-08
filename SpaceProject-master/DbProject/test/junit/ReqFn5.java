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

class ReqFn5 extends TestCase {
	//default value
	private final int id = 409;
	private final String satellite = "Herschel";
	private final String name = "HiGALFil015.9322-1.0422"; 
	
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
	void testGetCentroid() throws SQLException, ClassNotFoundException {
		
		Position position = Query.getCentroid(con, id, satellite);
		final double effective_lat = -1.0459;
		final double effective_lon = 15.9302;
		assertEquals(effective_lat, position.getG_lat(), 0.0001);
		assertEquals(effective_lon, position.getG_lon(), 0.0001);
	}
	@Test
	void testGetCentroidName() throws SQLException {
		Position position = Query.getCentroid(con, name);
		double effective_lat = -1.0459;
		double effective_lon = 15.9302;
		assertEquals(effective_lat, position.getG_lat(), 0.0001);
		assertEquals(effective_lon, position.getG_lon(), 0.0001);
	}
	
	@Test
	void testGetBoundaryExtension() throws SQLException {
		double[] distance = Query.getBoundaryExtension(con, id, satellite);
		final double dist_lat = 0.1820;
		final double dist_lon = 0.1629;
		assertEquals(dist_lat, distance[0], 0.0001);
		assertEquals(dist_lon, distance[1], 0.0001);
	}
	@Test
	void testGetBoundaryExtensionName() throws SQLException {
		double[] distance = Query.getBoundaryExtension(con, name);
		final double dist_lat = 0.1820;
		final double dist_lon = 0.1629;
		assertEquals(dist_lat, distance[0], 0.0001);
		assertEquals(dist_lon, distance[1], 0.0001);
	}
	
	@Test
	void testCountBranch() throws SQLException {
		int num_branch = Query.countBranch(con, id, satellite);
		final int effective_num_branch = 21;
		assertEquals(effective_num_branch, num_branch);
	}
	@Test
	void testCountBranchName() throws SQLException {
		int num_branch = Query.countBranch(con, name);
		final int effective_num_branch = 21;
		assertEquals(effective_num_branch, num_branch);
	}
	
}