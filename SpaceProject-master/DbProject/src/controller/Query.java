package controller;
 
import model.Position;
import model.Star;
import model.StarInfo;
import model.DistanceComparator;
import model.Filament;
import model.FluxComparator;
import controller.DBHandler;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
 
public class Query {
    //Query 5.1
    public static Position getCentroid(Connection con, int id, String satellite) throws SQLException {
        Position centroid = new Position();
        PreparedStatement statement;
        ResultSet result;
        String query_id;
 
        double lat = 0, lon = 0;
         
        query_id = "SELECT avg(g_lat) as mean_lat, avg(g_long) as mean_long"
                + " FROM g_position G JOIN boundary B on G.id=B.g_pos JOIN filament F on B.filament_name=F.name JOIN"
                + " fil_sat FS on FS.filament_name=F.name JOIN satellite S on FS.satellite = S.id"
                + " WHERE FS.filament_id = ? AND S.name = ?;";
         
        statement = con.prepareStatement(query_id);
         
        statement.setInt(1, id);
        statement.setString(2, satellite);
 
        result = statement.executeQuery();
        if(result.next()) {
            lat += result.getDouble("mean_lat");
            lon += result.getDouble("mean_long");
         
        }
        centroid.setG_lat(lat);
        centroid.setG_lon(lon);
        
        result.close();
        statement.close();
 
        return centroid;
    }
    public static Position getCentroid(Connection con, String name) throws SQLException{
        Position centroid = new Position();
        PreparedStatement statement;
        ResultSet result;
        String query_id;
 
        double lat = 0, lon = 0;
         
        query_id = "SELECT avg(g_lat) as mean_lat, avg(g_long) as mean_long"
                + " FROM g_position G JOIN boundary B on G.id=B.g_pos JOIN filament F on B.filament_id=F.id AND B.filament_name=F.name"
                + " WHERE F.name= ?";
        statement = con.prepareStatement(query_id);
         
        statement.setString(1, name);
         
        result = statement.executeQuery();
        if(result.next()) {
            lat = result.getDouble("mean_lat");
            lon = result.getDouble("mean_long");        
        }
        centroid.setG_lat(lat);
        centroid.setG_lon(lon);

        result.close();
        statement.close();
         
        return centroid;
    }
  
    //Query 5.2
    public static double[] getBoundaryExtension(Connection con, int id, String satellite) throws SQLException {
		/* L'estensione del contorno, calcolata come la distanza tra il minimo e il massimo delle
		 * latitudini e dist tra min e max longitudini.
		 */
		double[] extension = new double[2]; //conterrà le due distanze
		PreparedStatement statement;
		ResultSet result;
		String query;
		double min_lat, min_lon, max_lat, max_lon;
		min_lat = min_lon = max_lat = max_lon = 0;
		
		query = "SELECT min(g_lat), min(g_long), max(g_lat), max(g_long)"
				+ " FROM g_position G JOIN boundary B on G.id=B.g_pos JOIN"
				+ " filament F on B.filament_name=F.name JOIN fil_sat FS on FS.filament_name=F.name JOIN"
				+ " satellite S on FS.satellite=S.id"
				+ " WHERE F.id= ? AND S.name= ?;";
		
		statement = con.prepareStatement(query);
		
		statement.setInt(1, id);
		statement.setString(2, satellite);

		result = statement.executeQuery();
		
		if(result.next()) {
			min_lat = result.getDouble(1);
			min_lon = result.getDouble(2);
			max_lat = result.getDouble(3);
			max_lon = result.getDouble(4);
			
		}
		//calcolo distanze
		extension[0] = Math.abs(max_lat - min_lat);
		extension[1] = Math.abs(max_lon - min_lon);
		
		result.close();
		statement.close();
		
		return extension;
	}
	public static double[] getBoundaryExtension(Connection con, String name) throws SQLException {
		/* L'estensione del contorno, calcolata come la distanza tra il minimo e il massimo delle
		 * latitudini e dist tra min e max longitudini.
		 */
		double[] extension = new double[2]; //conterrà le due distanze
		PreparedStatement statement;
		ResultSet result;
		String query;
		double min_lat, min_lon, max_lat, max_lon;
		min_lat = min_lon = max_lat = max_lon = 0;
		
		query = "SELECT min(g_lat), min(g_long), max(g_lat), max(g_long)"
				+ " FROM g_position G JOIN boundary B on G.id=B.g_pos JOIN"
				+ " filament F on B.filament_name=F.name JOIN fil_sat FS on FS.filament_name=F.name JOIN"
				+ " satellite S on FS.satellite=S.id"
				+ " WHERE F.name= ?;";
		
		statement = con.prepareStatement(query);
		
		statement.setString(1, name);

		result = statement.executeQuery();
		
		if(result.next()) {
			min_lat = result.getDouble(1);
			min_lon = result.getDouble(2);
			max_lat = result.getDouble(3);
			max_lon = result.getDouble(4);
			
		}
		extension[0] = Math.abs(max_lat - min_lat);
		extension[1] = Math.abs(max_lon - min_lon);
		
		result.close();
		statement.close();
		
		return extension;
	}
    
    //Query 5.3
    public static int countBranch(Connection con, int id, String satellite) throws SQLException {
        PreparedStatement statement;
        ResultSet result;
        String query;
        int count_branch = 0;
 
        query = "SELECT count(B.id)"
                + " FROM branch B join filament F on B.filament_name=F.name JOIN fil_sat FS on FS.filament_name=F.name JOIN satellite S on FS.satellite=S.id"
                + " WHERE F.id = ? AND S.name = ?";
         
        statement = con.prepareStatement(query);
         
        statement.setInt(1, id);
        statement.setString(2, satellite);
 
        result = statement.executeQuery();
 
        if(result.next()) {
            count_branch = result.getInt(1);
        }
         
        return count_branch;
    }
    public static int countBranch(Connection con, String name) throws SQLException {
        PreparedStatement statement;
        ResultSet result;
        String query;
        int count_branch = 0;
 
        query = "SELECT count(B.id)"
                + " FROM branch B join filament F on B.filament_name=F.name"
                + " WHERE F.name = ?";
         
        statement = con.prepareStatement(query);
     
        statement.setString(1, name);
 
        result = statement.executeQuery();
 
        if(result.next()) {
            count_branch = result.getInt(1);
        }
         
        return count_branch;
    }
     
    //Query 6
    public static int countFilament(Connection con) throws SQLException {
        PreparedStatement statement;
        ResultSet result;
        String query;
        int count_total = 0;
             
        query = "SELECT count(*)"
                + " FROM filament;";
         
        statement = con.prepareStatement(query);
        result = statement.executeQuery();
         
        if(result.next()) {
            count_total = result.getInt(1);
        }
         
        return count_total;
    }
    public static ArrayList<Filament> searchFilamentByContrastEllipticity(Connection con, double brilliance, double ellptc_A, double ellptc_B) throws SQLException {
        //il controllo sull'intervallo dell'ellitticità e della brillantezza è effettuato
        //nella view a livello di interfaccia
        PreparedStatement statement;
        ResultSet result;
        String query;
        double contrast;
        ArrayList<Filament> linked_filam = new ArrayList<Filament>();
 
        contrast = (brilliance / 100) + 1;
 
        query = "SELECT *"
                + " FROM filament"
                + " WHERE contrast > ? AND ellipticity between ? and ?;";
         
        statement = con.prepareStatement(query);
 
        statement.setDouble(1, contrast);
        statement.setDouble(2, ellptc_A);
        statement.setDouble(3, ellptc_B);
 
        result = statement.executeQuery();
 
        while(result.next()) {
            Filament fil = new Filament();
            fil.setId(result.getInt("id"));
            fil.setName(result.getString("name"));
            fil.setTotal_flux(result.getDouble("total_flux"));
            fil.setMean_dens(result.getDouble("mean_dens"));
            fil.setMean_temp(result.getDouble("mean_temp"));
            fil.setEllipticity(result.getDouble("ellipticity"));
            fil.setContrast(result.getDouble("contrast"));
            linked_filam.add(fil);
        }
        result.close();
        statement.close();
                 
        return linked_filam;
    }
    public static ArrayList<Filament> searchFilamentByContrastEllipticity(Connection con, double brilliance, double ellptc_A, double ellptc_B, int limit, int offset) throws SQLException {
        // offset per visualizzazione grafica
        PreparedStatement statement;
        ResultSet result;
        String query;
        double contrast;
        ArrayList<Filament> linked_filam = new ArrayList<Filament>();
 
        contrast = (brilliance / 100) + 1;
 
        query = "SELECT *"
                + " FROM filament"
                + " WHERE contrast > ? AND ellipticity between ? and ?"
                + " LIMIT ? OFFSET ?";
         
        statement = con.prepareStatement(query);
 
        statement.setDouble(1, contrast);
        statement.setDouble(2, ellptc_A);
        statement.setDouble(3, ellptc_B);
        statement.setInt(4, limit);
        statement.setInt(5, offset);
 
        result = statement.executeQuery();
 
        while(result.next()) {
            Filament fil = new Filament();
            fil.setId(result.getInt("id"));
            fil.setName(result.getString("name"));
            fil.setTotal_flux(result.getDouble("total_flux"));
            fil.setMean_dens(result.getDouble("mean_dens"));
            fil.setMean_temp(result.getDouble("mean_temp"));
            fil.setEllipticity(result.getDouble("ellipticity"));
            fil.setContrast(result.getDouble("contrast"));
            linked_filam.add(fil);
        }
        result.close();
        statement.close();
                 
        return linked_filam;
    }
    
    //Query 7
    public static ArrayList<Filament> searchFilamentByRange(Connection con, int min_range, int max_range,int limit, int offset) throws SQLException { 
        PreparedStatement statement;
        ResultSet result;
        String query;
        ArrayList<Filament> linked_filam = new ArrayList<Filament>();
         
        query = "SELECT F.id, F.name, F.total_flux, F.mean_dens, F.mean_temp, F.ellipticity, F.contrast"
        		+ " FROM branch B JOIN filament F on B.filament_id=F.id AND B.filament_name=F.name"
        		+ " GROUP BY (F.id, F.name) HAVING count(B.id) >= ? AND count(B.id) <= ?"
        		+ " LIMIT ? OFFSET ?";
         
        statement = con.prepareStatement(query);
        statement.setDouble(1, min_range);
        statement.setDouble(2, max_range);
        statement.setInt(3, limit);
        statement.setInt(4, offset);
         
        result = statement.executeQuery();
         
        while(result.next()) {
            Filament fil = new Filament();
            fil.setId(result.getInt("id"));
            fil.setName(result.getString("name"));
            fil.setTotal_flux(result.getDouble("total_flux"));
            fil.setMean_dens(result.getDouble("mean_dens"));
            fil.setMean_temp(result.getDouble("mean_temp"));
            fil.setEllipticity(result.getDouble("ellipticity"));
            fil.setContrast(result.getDouble("contrast"));
            linked_filam.add(fil);
        }
         
        result.close();
        statement.close();
         
        return linked_filam;    
    }
    public static ArrayList<Filament> searchFilamentByRange(Connection con, int min_range, int max_range) throws SQLException {  
        PreparedStatement statement;
        ResultSet result;
        String query;
        ArrayList<Filament> linked_filam = new ArrayList<Filament>();
         
        query = "SELECT F.id, F.name, F.total_flux, F.mean_dens, F.mean_temp, F.ellipticity, F.contrast\n" + 
                "FROM branch B JOIN filament F on B.filament_id=F.id AND B.filament_name=F.name\n" + 
                "GROUP BY (F.id, F.name) HAVING count(B.id) >= ? AND count(B.id) <= ?;\n" + 
                "";
         
        statement = con.prepareStatement(query);
        statement.setDouble(1, min_range);
        statement.setDouble(2, max_range);
         
        result = statement.executeQuery();
         
        while(result.next()) {
            Filament fil = new Filament();
            fil.setId(result.getInt("id"));
            fil.setName(result.getString("name"));
            fil.setTotal_flux(result.getDouble("total_flux"));
            fil.setMean_dens(result.getDouble("mean_dens"));
            fil.setMean_temp(result.getDouble("mean_temp"));
            fil.setEllipticity(result.getDouble("ellipticity"));
            fil.setContrast(result.getDouble("contrast"));
            linked_filam.add(fil);
        }
         
        result.close();
        statement.close();
         
        return linked_filam;    
    }
         
    //Query 8
  	public static ArrayList<Filament> findFilamentCircle(Connection conn, double radius, Position centroid) throws SQLException {
  		ArrayList<Filament> list_filament = new ArrayList<Filament>();
  		PreparedStatement st = conn.prepareStatement("SELECT id, name " +
  													 "FROM filament F " +
  													 "WHERE NOT EXISTS (SELECT * " +
  															   		   "FROM boundary B JOIN g_position G on g_pos = id " +
  															   		   "WHERE sqrt(power(g_long - ?, 2) + power(g_lat - ?, 2)) > ? " + 
  															   			  "AND F.id = filament_id AND F.name = filament_name)");
  		
  		st.setDouble(1, centroid.getG_lon());
  		st.setDouble(2, centroid.getG_lat());
  		st.setDouble(3, radius);
  		
  		ResultSet rs = st.executeQuery();
  		
  		while (rs.next()) {
  			Filament fil = new Filament();
  			int id = rs.getInt("id");
  			String name = rs.getString("name");
  			
  			fil.setId(id);
  			fil.setName(name);
  			list_filament.add(fil);
  		}
  		return list_filament;
  	}
  	public static ArrayList<Filament> findFilamentSquare(Connection conn, double side, Position centroid) throws SQLException {
  		ArrayList<Filament> list_filament = new ArrayList<Filament>();
  		PreparedStatement st = conn.prepareStatement("SELECT id, name " +
  													 "FROM filament F " +
  													 "WHERE NOT EXISTS (SELECT * " +
  															   		   "FROM boundary B JOIN g_position G on g_pos = id " +
  															   		   "WHERE (g_lat > ? OR g_lat < ? OR g_long < ? OR g_long > ?) " +
  															   			  "AND F.id = filament_id AND F.name = filament_name)");
  		
  		st.setDouble(1, centroid.getG_lat() + side / 2);
  		st.setDouble(2, centroid.getG_lat() - side / 2);
  		st.setDouble(3, centroid.getG_lon() - side / 2);
  		st.setDouble(4, centroid.getG_lon() + side / 2);

  		
  		ResultSet rs = st.executeQuery();
  		
  		while (rs.next()) {
  			Filament fil = new Filament();
  			int id = rs.getInt("id");
  			String name = rs.getString("name");
  			
  			fil.setId(id);
  			fil.setName(name);
  			list_filament.add(fil);
  		}
  		return list_filament;
  	}

    //Query 9
    public static int countBoundary(Connection con, String name) throws SQLException {
        PreparedStatement statement_count;
        ResultSet result_count;
        String query_count;
        int count = 0;
         
        query_count = "select count(*)"
                + " FROM boundary B JOIN filament F ON B.filament_name=F.name"
                + " WHERE F.name= ? ;";
        statement_count = con.prepareStatement(query_count);
        statement_count.setString(1, name);
        result_count = statement_count.executeQuery();
        if(result_count.next()) {
            count = result_count.getInt(1);
        }
        return count;       
    }
    public static ArrayList<Position> getBoundary(Connection con, String name) throws SQLException {
        ArrayList<Position> bound = new ArrayList<Position>();
        PreparedStatement statement_bound;
        ResultSet result_bound;
        String query_bound;
         
        query_bound = "SELECT g_lat, g_long"
                + " FROM g_position G JOIN boundary B ON G.id=B.g_pos JOIN filament F ON F.name=B.filament_name"
                + " WHERE F.name= ?;";
        statement_bound = con.prepareStatement(query_bound);
        statement_bound.setString(1, name);
        result_bound = statement_bound.executeQuery();
        while(result_bound.next()) {
            Position b = new Position();
            b.setG_lat(result_bound.getDouble("g_lat"));
            b.setG_lon(result_bound.getDouble("g_long"));
            bound.add(b);
        }
        // Rinseriamo il punto iniziale che chiude il contorno
        bound.add(bound.get(0));
        return bound;
    }
    public static ArrayList<Star> getStarsInFilament(Connection con, String name) throws SQLException {
        PreparedStatement statement_star;
        ResultSet result_star;
        String query_star;
        ArrayList<Star> star = new ArrayList<Star>();
        ArrayList<Position> bound = new ArrayList<Position>();
        
        bound = getBoundary(con, name);
         
        query_star = "SELECT S.id as starid, S.name as starname, S.type, S.flux, G.g_lat, G.g_long"
                + " FROM star S JOIN g_position G ON S.g_pos=G.id;";
                 
        statement_star = con.prepareStatement(query_star);
        result_star = statement_star.executeQuery();
        
        while(result_star.next()) {
        	Star s = new Star();
            s.setG_lat(result_star.getDouble("g_lat"));  //St_B
            s.setG_lon(result_star.getDouble("g_long")); //St_L
            
            if (isIn(con, s, bound)) {
                // la stella è interna al filamento
                s.setId(result_star.getInt("starid"));
                s.setName(result_star.getString("starname"));
                s.setType(result_star.getString("type"));
                s.setFlux(result_star.getDouble("flux"));
                star.add(s);
            }
        }
        return star;
    }
    public static StarInfo infoStarsInFilament(Connection con, String name) throws SQLException {
        ArrayList<Star> star = new ArrayList<Star>();
        star = getStarsInFilament(con, name);
        int total_unbound = 0, total_protostellar = 0, total_prestellar = 0;
        int i, total_star;
         
        total_star = star.size();
        for(i = 0; i < total_star; i++) {
            Star s = (Star) star.get(i);
            switch(s.getType()) {
            case "PROTOSTELLAR" :
                total_protostellar++;
                break;
            case "UNBOUND" : 
                total_unbound++;
                break;
            case "PRESTELLAR" :
                total_prestellar++;
                break;
            }
        }
        StarInfo info = new StarInfo();
        info.setOccurrence_star(total_star);
        info.setPercentual_protostellar((float)100*total_protostellar/total_star);
        info.setPercentual_unbound((float)100*total_unbound/total_star);
        info.setPercentual_prestellar((float)100*total_prestellar/total_star);
        return info;
    }
    public static boolean isIn(Connection conn, Star s, ArrayList<Position> bound) throws SQLException {
        double lat_star, lon_star, sum = 0, formule;
        double C_Li, C_Bi, C_Lj, C_Bj;
        int i = 0, count_bound = 0;

        count_bound = bound.size();

        lat_star = s.getG_lat();  //St_B
        lon_star = s.getG_lon(); //St_L

        while(i < count_bound - 1) {
            Position bound_i = (Position) bound.get(i); 
            Position bound_j = (Position) bound.get(i+1);
            C_Li = bound_i.getG_lon();
            C_Lj = bound_j.getG_lon();
            C_Bi = bound_i.getG_lat();
            C_Bj = bound_j.getG_lat();
            formule = ((C_Li - lon_star)*(C_Bj - lat_star)-(C_Bi - lat_star)*(C_Lj - lon_star))/((C_Li - lon_star)*(C_Lj - lon_star)+(C_Bi - lat_star)*(C_Bj - lat_star));
            sum += Math.atan(formule);
            ++i;
        }

	    sum = Math.abs(sum);

	    // la stella è interna al filamento
	    if (sum >= 0.01) {
	    	return true;
	    }

	    return false;
  	}
    
    //Query 10
  	public static ArrayList<Star> getStarsInRectangle(Connection conn, double height,
  			double basis, Position centroid) throws SQLException {
  		ArrayList<Star> list_star = new ArrayList<Star>();
  		PreparedStatement st = conn.prepareStatement("SELECT * " +
  													 "FROM star S join g_position G on S.g_pos = G.id " +
  													 "WHERE (G.g_lat BETWEEN ? AND ?) AND (G.g_long BETWEEN ? AND ?)");
  		
  		st.setDouble(1, centroid.getG_lat() - height / 2);
  		st.setDouble(2, centroid.getG_lat() + height / 2);
  		st.setDouble(3, centroid.getG_lon() - basis / 2);
  		st.setDouble(4, centroid.getG_lon() + basis / 2);

  		
  		ResultSet rs = st.executeQuery();
  		while (rs.next()) {
  			Star s = new Star();

  			String type = rs.getString("type");
  			double g_lat = rs.getDouble("g_lat");
  			double g_long = rs.getDouble("g_long");
  			
  			s.setG_lat(g_lat);
  			s.setG_lon(g_long);
  			s.setType(type);
  			
  			list_star.add(s);
  		}
  		return list_star;
  	}
  	public static ArrayList<Filament> getFilamentsInRectangle(Connection conn, double height, double basis, Position centroid) throws SQLException {
  		ArrayList<Filament> list_filament = new ArrayList<Filament>();
  		PreparedStatement st = conn.prepareStatement("SELECT name " +
													 "FROM filament F " +
													 "WHERE EXISTS (SELECT * " +
															   	   "FROM boundary JOIN g_position on g_pos = id " +
															   	   "WHERE (g_lat BETWEEN ? AND ?) AND (g_long BETWEEN ? AND ?) " +
															   	   		"AND F.name = filament_name)");
  		
  		st.setDouble(1, centroid.getG_lat() - height / 2);
  		st.setDouble(2, centroid.getG_lat() + height / 2);
  		st.setDouble(3, centroid.getG_lon() - basis / 2);
  		st.setDouble(4, centroid.getG_lon() + basis / 2);

  		ResultSet rs = st.executeQuery();
  		
  		while (rs.next()) {
  			String name = rs.getString("name");
  			
  			Filament f = new Filament();
  			f.setName(name);
  			
  			list_filament.add(f);
  		}
  		return list_filament;
  	} 	
  	public static StarInfo[] starsInFilament(Connection conn, double height, double basis, Position centroid) throws SQLException {
  		ArrayList<Star> stars;
  		ArrayList<Filament> filament;
  		StarInfo si[] = {new StarInfo(), new StarInfo()};
  		
  		int unbound_in = 0, protostellar_in = 0, prestellar_in = 0, star_in = 0, total_filament = 0,
  				unbound_ext = 0, protostellar_ext = 0, prestellar_ext = 0, star_ext = 0, i = 0;
  		
  		stars = getStarsInRectangle(conn, height, basis, centroid);
  		filament = getFilamentsInRectangle(conn, height, basis, centroid);
  		total_filament = filament.size();
  		
  		System.out.println("NUM FILAMENT: " + total_filament +
  						   "\nNUM STARS: " + stars.size());
  		
  		@SuppressWarnings("unchecked")
		ArrayList<Position>[] bound = new ArrayList[total_filament];
  		
  		
  		for(Star s:stars) {
  			i = 0;
  			for(Filament f:filament) {
  			
  				if (bound[i] == null)
  					bound[i] = getBoundary(conn, f.getName());
  				
	  			if(isIn(conn, s, bound[i])) {
	  	            switch(s.getType()) {
	  	            	case "PROTOSTELLAR" :
	  	            		protostellar_in++;
	  	            		break;
	  	            	case "UNBOUND" : 
	  	            		unbound_in++;
	  	            		break;
	  	            	case "PRESTELLAR" :
	  	            		prestellar_in++;
	  	            		break;
	  	            }
	  	            star_in++;
	  				break;
	  			}
	  			
  	            i++;
  			}
  			if (i == total_filament) {
  	            switch(s.getType()) {
	            	case "PROTOSTELLAR" :
	            		protostellar_ext++;
	            		break;
	            	case "UNBOUND" : 
	            		unbound_ext++;
	            		break;
	            	case "PRESTELLAR" :
	            		prestellar_ext++;
	            		break;
	            }
  	            star_ext++;
  			}
  		}
  		
        si[0].setOccurrence_star(star_in);
        si[0].setPercentual_protostellar((float)100*protostellar_in/star_in);
        si[0].setPercentual_unbound((float)100*unbound_in/star_in);
        si[0].setPercentual_prestellar((float)100*prestellar_in/star_in);
        
        si[1].setOccurrence_star(star_ext);
        si[1].setPercentual_protostellar((float)100*protostellar_ext/star_ext);
        si[1].setPercentual_unbound((float)100*unbound_ext/star_ext);
        si[1].setPercentual_prestellar((float)100*prestellar_ext/star_ext);
        
  		return si;
  	}
    
    //Query 11
    public static int getMinVertix(Connection con, int branch_id) throws SQLException {
        PreparedStatement statement;
        ResultSet result;
        String query;
        int min = 0;
         
        query = "SELECT min(prog_num) as min"
                + " FROM branch_point"
                + " WHERE branch = ?;";
        statement = con.prepareStatement(query);
        statement.setInt(1, branch_id);
        result = statement.executeQuery();
        if(result.next()) {
            min = result.getInt("min");
        }
        return min;
    }
    public static int getMaxVertix(Connection con, int branch_id) throws SQLException {
        PreparedStatement statement;
        ResultSet result;
        String query;
        int max = 0;
         
        query = "SELECT max(prog_num) as max"
                + " FROM branch_point"
                + " WHERE branch = ?;";
        statement = con.prepareStatement(query);
        statement.setInt(1, branch_id);
        result = statement.executeQuery();
        if(result.next()) {
            max = result.getInt("max");
        }
        return max;
    }
    public static Position getPosition(Connection con, int branch_id, int prog_num) throws SQLException {
        PreparedStatement statement;
        ResultSet result;       
        String query;
        Position pos = new Position();
 
        query = "SELECT g_lat, g_long"
                + " FROM g_position join branch_point ON id=g_pos"
                + " WHERE branch = ? AND prog_num = ?;";
         
        statement = con.prepareStatement(query);
        statement.setInt(1, branch_id);
        statement.setInt(2, prog_num);
        result = statement.executeQuery();
        if(result.next()) {
            pos.setG_lat(result.getDouble("g_lat"));
            pos.setG_lon(result.getDouble("g_long"));
        }
        return pos;
    }
    public static ArrayList<Double> distanceVertix(Connection con, int branch_id) throws SQLException {
        PreparedStatement statement_boundary;
        ResultSet result_boundary;      
        String query;
        int min, max;
        double boundary_lat, boundary_lon, distance_min, distance_max;
        double distance_min_final = Double.MAX_VALUE, distance_max_final = Double.MAX_VALUE; //distanze piu grandi possibili
        ArrayList<Double> l = new ArrayList<Double>();
        
        min = getMinVertix(con, branch_id);
        max = getMaxVertix(con, branch_id);
 
        Position pos_min = getPosition(con, branch_id, min);
        Position pos_max = getPosition(con, branch_id, max);
         
        query = "SELECT G.g_lat, G.g_long"
                + " FROM g_position G JOIN boundary B ON B.g_pos=G.id JOIN branch R ON B.filament_name=R.filament_name"
                + " WHERE R.id = ?;";
        statement_boundary = con.prepareStatement(query);
        statement_boundary.setInt(1, branch_id);
        result_boundary = statement_boundary.executeQuery();
        
        while(result_boundary.next()) {
            boundary_lat = result_boundary.getDouble("g_lat");
            boundary_lon = result_boundary.getDouble("g_long");
            
            distance_min = Math.sqrt(Math.pow(pos_min.getG_lat() - boundary_lat, 2) + Math.pow(pos_min.getG_lon() - boundary_lon, 2));
            distance_max = Math.sqrt(Math.pow(pos_max.getG_lat() - boundary_lat, 2) + Math.pow(pos_max.getG_lon() - boundary_lon, 2));
             
            if(distance_min < distance_min_final) {
                distance_min_final = distance_min;
            }
            if(distance_max < distance_max_final) {
                distance_max_final = distance_max;
            }
        }
        //Inserisco le due distanze in una lista
        if(!(distance_min_final == Double.MAX_VALUE && distance_max_final == Double.MAX_VALUE)) {
        	l.add(distance_min_final);
        	l.add(distance_max_final);
        }
        return l; //ritorna min->max oppure lista vuota
    }

    //Query 12
    public static double getDistance(Position p1, Position p2) {
    	double distance;
    	distance = Math.sqrt(Math.pow(p1.getG_lat() - p2.getG_lat(), 2) + Math.pow(p1.getG_lon() - p2.getG_lon(), 2));
    	return distance;
    }
    public static ArrayList<Position> getPositionMainBranch(Connection con, String name) throws SQLException {
    	PreparedStatement statement;
    	ResultSet result;
    	ArrayList<Position> branches_position = new ArrayList<Position>();
 
    	String query;
    	query = "SELECT distinct g_lat, g_long" + 
    			" FROM g_position G JOIN branch_point BP on G.id=BP.g_pos JOIN branch B on B.id=BP.branch" + 
    			" WHERE B.filament_name = ? AND B.type='S';";
    	statement = con.prepareStatement(query);
    	statement.setString(1, name);
    	result = statement.executeQuery();
    	while(result.next()) {
    		Position pos = new Position();
    		pos.setG_lat(result.getDouble("g_lat"));
    		pos.setG_lon(result.getDouble("g_long"));
    		branches_position.add(pos);
    	}
    	
    	return branches_position;
    }
    public static ArrayList<Star> distanceStarsToMainBranch(Connection con, String name) throws SQLException {
        ArrayList<Star> stars = new ArrayList<Star>();
        ArrayList<Position> branches_position = new ArrayList<Position>();
        //ottengo le stelle nel filamento, devo calcolare la distanza di ogni stella dal ramo principale, dopodichè ordinare rispetto a flusso o distanza
        stars = getStarsInFilament(con, name); 
        branches_position = getPositionMainBranch(con, name);

        //double distance[] = new double[stars.size()];
        /*for(i = 0; i < distance.length; ++i) {
        	distance[i] = Double.MAX_VALUE;
        }*/

        for(Star s:stars) {
        	s.setDistanceBranch(Double.MAX_VALUE);
        	Position star_pos = new Position();
        	star_pos.setG_lat(s.getG_lat());
        	star_pos.setG_lon(s.getG_lon());
        	for(Position branch_pos:branches_position) {
        		double new_dist = getDistance(star_pos, branch_pos);
        		double actual_dist = s.getDistanceBranch();
        		if(actual_dist >  new_dist) {
        			s.setDistanceBranch(new_dist);
        		}
        	}
        }
        return stars;
    }
    public static ArrayList<Star> orderStars(ArrayList<Star> stars, String type) {
    	switch (type) {
	    	case "distance":
	        	DistanceComparator d_c = new DistanceComparator();
	        	stars.sort(d_c);
	        	break;
	    	case "flux":
	    		FluxComparator f_c = new FluxComparator();
	    		stars.sort(f_c);
	    		break;
    	}
    	    	
    	return stars;
    }
    /*public static ArrayList<Star> viewStarsLimit1(ArrayList<Star> stars, int limit, int offset) {
    	// la lista di stelle è completa, si deciderà da quale a quale stella visualizzare
    	ArrayList<Star> limit_stars = new ArrayList<Star>();
    	int i;
    	for (i = 0; i < limit && i != stars.size(); ++i) {
    		limit_stars.add(stars.get(i + offset));
    	}
    	return limit_stars;
    }*/
    public static ArrayList<Star> viewStarsLimit(ArrayList<Star> stars, int limit, int offset) {
    	ArrayList<Star> limit_stars = new ArrayList<Star>();
    	int i;
    	for (Star s : stars) {
    		if (limit == 0)
    			break;
    		i = stars.indexOf(s);
    		if (i >= offset) {
    			limit --;
    			limit_stars.add(s);
    		}
    	}
    	return limit_stars;
    }
    
    //Query di supporto
    public static String[] getSatellitesNames(Connection con) throws SQLException  {
		String[] satellitesNames=null;
		Statement query= con.createStatement();
		ResultSet resultCount = query.executeQuery("select count(*) from satellite");
		if (resultCount.next()) {
			int rowCounter = resultCount.getInt(1);
			satellitesNames = new String[rowCounter+1];
			satellitesNames[0]="SELECT";
			Statement query2= con.createStatement();
			ResultSet result = query2.executeQuery("select name from satellite");
			int i=1;
			while(result.next() && i!=(rowCounter+1)) {
				String satelliteName = result.getString("name");
				satellitesNames[i]= satelliteName;
				i=i+1;
			}
	    }
		return satellitesNames;
	}
	public static String[] getFilNames(Connection con) throws SQLException  {
		String[] filamentsNames = null;
		Statement query= con.createStatement();
		ResultSet resultCount = query.executeQuery("select count(*) from filament");
		if (resultCount.next()) {
			int rowCounter = resultCount.getInt(1);
			filamentsNames = new String[rowCounter+1];
			filamentsNames[0]="SELECT";
			Statement query2= con.createStatement();
			ResultSet result = query2.executeQuery("select name from filament order by name");
			int i=1;
			while(result.next() && i!=(rowCounter+1)) {
				String filamentName = result.getString("name");
				filamentsNames[i]= filamentName;
				i=i+1;
			}
	    }
		return filamentsNames;
	}
	public static String[] getInsNames(Connection con) throws SQLException  {
		String[] instrumentsNames = null;
		Statement query= con.createStatement();
		ResultSet resultCount = query.executeQuery("select count(*) from instrument");
		if (resultCount.next()) {
			int rowCounter = resultCount.getInt(1);
			instrumentsNames = new String[rowCounter+1];
			instrumentsNames[0]="SELECT";
			Statement query2= con.createStatement();
			ResultSet result = query2.executeQuery("select name from instrument");
			int i=1;
			while(result.next() && i!=(rowCounter+1)) {
				String filamentName = result.getString("name");
				instrumentsNames[i]= filamentName;
				i=i+1;
			}
	    }
		return instrumentsNames;
	}
	public static void insertSatellite(Connection con, String satellite, String datef, String datel) throws SQLException {
		String[] oldSatelliteList = getSatellitesNames(con);
		int i=0;
		while(i!=oldSatelliteList.length) {
			if (oldSatelliteList[i].equals(satellite)) {
				JOptionPane.showMessageDialog(null, "Satellite with name " + satellite + " already exists!");
				return;
			}
			else {
				i=i+1;
			}
		}
		int myId=0;
		Statement query= con.createStatement();
		ResultSet resultCount = query.executeQuery("select count(*) from satellite");
		if (resultCount.next()) {
			myId = resultCount.getInt(1)+1;
		}
		PreparedStatement statement2;
		String query2 = "insert into satellite values(?,?,'" + datef + "','" + datel +"')";
		statement2 = con.prepareStatement(query2);
		statement2.setInt(1, myId);
		statement2.setString(2, satellite);
		statement2.executeUpdate();
		JOptionPane.showMessageDialog(null, "Satellite successfully added!");
		return;
	}	
	public static void insertInstrument(Connection con, String instrument, LinkedList<Double> bandDList, int satId) throws SQLException {
		String[] oldInstrumentList = getInsNames(con);
		int i=0;
		while(i!=oldInstrumentList.length) {
			if (oldInstrumentList[i].equals(instrument)) {
				JOptionPane.showMessageDialog(null, "Instrument with name " + instrument + " already exists!");
				return;
			}
			else {
				i=i+1;
			}
		}
		int insId=0;
		Statement query= con.createStatement();
		ResultSet resultCount = query.executeQuery("select count(*) from instrument");
		if (resultCount.next()) {
			insId = resultCount.getInt(1)+1;
		}
		
		
		PreparedStatement statement2;
		String query2 = "insert into instrument values(?,?)";
		statement2 = con.prepareStatement(query2);
		statement2.setInt(1, insId);
		statement2.setString(2, instrument);
		statement2.executeUpdate();
		
		PreparedStatement statement8;
		String query8 = "insert into ins_sat values(?,?)";
		statement8 = con.prepareStatement(query8);
		statement8.setInt(1, satId);
		statement8.setInt(2, insId);
		statement8.executeUpdate();
		
		int w=0;
		while (w!=bandDList.size()) {
			Double bandD = bandDList.get(w);
			
			PreparedStatement statement4;
			String query4 = "select id from band where value=?";
			statement4 = con.prepareStatement(query4);
			statement4.setDouble(1, bandD);
			ResultSet result3 = null;
			result3 = statement4.executeQuery();
			if (result3.next()) {
				PreparedStatement statement5;
				String query5 = "insert into ins_band values(?,?)";
				statement5 = con.prepareStatement(query5);
				statement5.setInt(1, insId);
				statement5.setInt(2, result3.getInt(1));
				statement5.executeUpdate();
			} else {
				int insBand=0;
				Statement query9 = con.createStatement();
				ResultSet resultCount4 = query9.executeQuery("select count(*) from band");
				if (resultCount4.next()) {
					insBand = resultCount4.getInt(1)+1;
				}
				
				PreparedStatement statement6;
				String query6 = "insert into band values(?,?)";
				statement6 = con.prepareStatement(query6);
				statement6.setInt(1, insBand);
				statement6.setDouble(2, bandD);
				statement6.executeUpdate();	
				
				PreparedStatement statement7;
				String query7 = "insert into ins_band values(?,?)";
				statement7 = con.prepareStatement(query7);
				statement7.setInt(1, insId);
				statement7.setInt(2, insBand);
				statement7.executeUpdate();
			}
			w++;
		}
		
		JOptionPane.showMessageDialog(null, "Instrument added!");
		return;
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHandler handl = new DBHandler();
        Connection con = handl.getConnection();
        //int id = 409;
        //String name = "HiGALFil015.9322-1.0422";
        //String name = "HiGALFil014.3940-0.9294";
        //String satellite = "Herschel";
         
        //Query 5.1.a
        /* 
        Position p = getCentroid(con, id, satellite);
        System.out.println("Centroide\n"+p);
        // Query 5.1.b
        Position p2 = getCentroid(con, name);
        System.out.println("Centroide\n"+p2);
        */
        //Query 5.2
        //Position p1 = getBoundaryExtension(con, id, satellite);
        //System.out.println(p1);
        //Position p2 = getBoundaryExtension(con, name);
        //System.out.println(p2);
         
        // Query 5.3
        //int n = countBranch(con, id, satellite);
        //System.out.println(n);
        //int n2 = countBranch(con, name);
        //System.out.println(n2);
         
        // Query 6
        //ArrayList l = searchFilamentByContrastEllipticity(con, 170.0, 2.0, 7.0, 20, 660);
        //System.out.println(l); //migliorare la stampa di filament, metodo toString
         
        // Query 7
        //int min_range = 31, max_range = 32;
        //ArrayList l = searchFilamentByRange(con, min_range, max_range);
        //System.out.println(l);
 
        // Query 8
        /*
        Position p = getCentroid(con, name);
		System.out.println(p);
        //ArrayList list_filament = findFilamentCircle(con, 3, p);
        ArrayList list_filament = findFilamentSquare(con, 3, p);
        System.out.println(list_filament);
        */
        // Query 9
        //StarInfo info = infoStarsInFilament(con, name); 
        //System.out.println(info);
        
		/* -------------------------Query 10 --------------------------- */ 
		
		long ms = System.currentTimeMillis();
		
		StarInfo[] si = starsInFilament(con, 8, 6, new Position(-5,14)); 
		
		double sec = (double)(System.currentTimeMillis() - ms) / 1000;
		
		System.out.println("INT\n" + si[0]);
		System.out.println("EXT\n" + si[1]);
		
		System.out.println(sec + "s");
     
        //Query 11 -> se non ho risultati avrò lista vuota
        /*
        int branch_id = 1;
        double distance_min_final, distance_max_final;
        ArrayList two_dist = distanceVertix(con, branch_id);
        if(two_dist.size() != 0)
        	System.out.println("Minimum distance for vertix min : "+two_dist.get(0)+"\nMinimum distance for vertix max : "+two_dist.get(1));
        else
        	System.out.println("No result, id_branch doesn't exist");
        	*/
        //Query 12
		/*
        ArrayList<Star> stars = distanceStarsToMainBranch(con , name);
        String type = "distance"; // or type = "flux"
        ArrayList<Star> stars_order = orderStars(stars, type);
        long start = System.currentTimeMillis();
        System.out.println(viewStarsLimit1(stars_order, 20, 0));
        long end = System.currentTimeMillis();
        System.out.println("LIMIT 1 "+ (end - start) + "ms");
        
        start = System.currentTimeMillis();
        System.out.println(viewStarsLimit2(stars_order, 20, 0));
        end = System.currentTimeMillis();
        System.out.println("LIMIT 2 "+ (end - start) + "ms");
        */
    }
}