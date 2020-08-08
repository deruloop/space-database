package junit;

import java.sql.Connection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import controller.DBHandler;
import controller.DataImport;
import junit.framework.TestCase;

class ReqFn4 extends TestCase {
	private static Connection con;
	private final String sat = "Herschel";
	private final String filament_file = System.getProperty("user.dir") + "/csv_test/filamenti_Herschel.csv";
	private final String star_file = System.getProperty("user.dir") + "/csv_test/stelle_Herschel.csv";
	private final String boundary_file = System.getProperty("user.dir") + "/csv_test/contorni_filamenti_Herschel.csv";
	private final String branch_file = System.getProperty("user.dir") + "/csv_test/scheletro_filamenti_Herschel.csv";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DBHandler handl = new DBHandler();
		con = handl.getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
		con.close();
	}

	// FILAMENT IMPORT
	@Test
	void testImportFilamentData() {
		int retval = DataImport.importFilamentData(con, sat, filament_file);
		assertEquals(retval, 0);
	}
	@Test
	void testFailedImportFilamentData() {
		String path = star_file; // qualsiasi file che non corrisponde ai campi della tabella temporanea
		int retval = DataImport.importFilamentData(con, sat, path);
		assertEquals(retval, 1);
	}
	
	// STAR IMPORT
	@Test
	void testImportStarData() {
		int retval = DataImport.importStarData(con, star_file);
		assertEquals(retval, 0);
	}
	@Test
	void testFailedImportStarData() {
		String path = filament_file; // qualsiasi file che non corrisponde ai campi della tabella temporanea
		int retval = DataImport.importStarData(con, path);
		assertEquals(retval, 1);
	}
	
	// BOUNDARY IMPORT
	@Test
	void testImportBoundaryData() {
		int retval = DataImport.importBoundaryData(con, sat, boundary_file);
		assertEquals(retval, 0);
	}
	@Test
	void testFailedImportBoundaryData() {
		String path = branch_file; // qualsiasi file che non corrisponde ai campi della tabella temporanea
		int retval = DataImport.importBoundaryData(con, sat, path);
		assertEquals(retval, 1);
	}
	
	// BRANCH IMPORT
	@Test
	void testImportBranchData() {
		int retval = DataImport.importBranchData(con, sat, branch_file);
		assertEquals(retval, 0);
	}
	@Test
	void testFailedImportBranchData() {
		String path = star_file; // qualsiasi file che non corrisponde ai campi della tabella temporanea
		int retval = DataImport.importBranchData(con, sat, path);
		assertEquals(retval, 1);
	}
}
