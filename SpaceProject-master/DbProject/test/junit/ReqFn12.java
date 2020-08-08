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
import model.Star;

class ReqFn12 extends TestCase {
	private static Connection con;
	private final String name = "HiGALFil005.0636-0.5511";
	private final int limit = 20, offset = 0;
	private final String type = "distance"; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DBHandler handl = new DBHandler();
		con = handl.getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
		con.close();
	}
	
	// verifichiamo l'effettivo ordinamento per distanza/flusso delle stelle

	@Test
	void viewStarsLimitDistance() throws SQLException {
	    ArrayList<Star> starsToMainBranch = Query.distanceStarsToMainBranch(con , name);
	    ArrayList<Star> stars_order = Query.orderStars(starsToMainBranch, type);
	   
	    ArrayList<Star> all_stars = Query.viewStarsLimit(stars_order, limit, offset);
	    
	    double distance = all_stars.get(0).getDistanceBranch(); 
	    
	    for(int i = 1; i < all_stars.size(); i++) {
	    	double new_distance = all_stars.get(i).getDistanceBranch();
	    	
	    	if (new_distance < distance)
	    		fail("Stars not ordered by distance!");
	    	
	    	distance = new_distance;
	    }
	}
	
	@Test
	void viewStarsLimitFlux() throws SQLException {
		String type = "flux";
		
	    ArrayList<Star> starsToMainBranch = Query.distanceStarsToMainBranch(con , name);
	    ArrayList<Star> stars_order = Query.orderStars(starsToMainBranch, type);
	    ArrayList<Star> all_stars = Query.viewStarsLimit(stars_order, limit, offset);
	    
	    double flux = all_stars.get(0).getFlux();
	    
	    for(int i = 1; i < all_stars.size(); i++) {
	    	double new_flux = all_stars.get(i).getFlux();
	    	
	    	if (new_flux < flux)
	    		fail("Stars not ordered by flux!");
	    	
	    	flux = new_flux;
	    }
	}
}
