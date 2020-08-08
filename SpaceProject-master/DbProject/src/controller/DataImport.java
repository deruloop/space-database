package controller;

import java.sql.*;

public class DataImport {
	
	public static int importFilamentData(Connection conn, String sat, String file) {
		long start_time;
		double tot_time;
		Statement st;
		PreparedStatement update;
		
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			update = conn.prepareStatement("UPDATE filament " +
											 "SET total_flux = ?, mean_dens = ?, " +
											     "mean_temp = ?, ellipticity = ?, contrast = ? " +
											 "WHERE id = ? and name = ?");
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		}	
		
		System.out.println("Update started...");
		start_time = System.currentTimeMillis();
		
		/* importo file csv in una tabella temporanea (inizio transazione) */
		try {
			String query_create = "CREATE TABLE filament_temp( " +
								    "id int, " +
								    "name varchar(64), " +
								    "total_flux float(25), " +
								    "mean_dens float(25), " +
								    "mean_temp float(25), " +
								    "ellipticity float(25), " +
								    "contrast float(25), " +
								    "satellite varchar(64), " +
								    "instrument varchar(64), " +
								    "PRIMARY KEY(id, name))";
			
			String query_copy = "COPY filament_temp " + 
					       		"FROM '" + file + "' " +
					       		"DELIMITER ',' CSV HEADER";
			
			st.executeUpdate(query_create);
			st.executeUpdate(query_copy);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return 1;
		}	
		
		/* inserisco (aggiorno) i dati relativi */
		try {
			int filament_id;
			String filament_name;
			double total_flux, mean_dens,mean_temp,ellipticity,contrast;
			
			/* aggiornamento filamenti esistenti  
			 * si presuppone che non cambiera' la corrispondenza con il satellite e lo strumento */
			ResultSet rs = st.executeQuery("SELECT * " +
										     "FROM filament_temp " +
										     "WHERE (id,name) IN (SELECT id, name " +
																 "FROM filament)");
			
			while(rs.next()) {
				filament_id = rs.getInt("id");
				filament_name = rs.getString("name");
				total_flux = rs.getDouble("total_flux");
				mean_dens = rs.getDouble("mean_dens");
				mean_temp = rs.getDouble("mean_temp");
				ellipticity = rs.getDouble("ellipticity");
				contrast = rs.getDouble("contrast");
				
				update.setDouble(1, total_flux);
				update.setDouble(2, mean_dens);
				update.setDouble(3, mean_temp);
				update.setDouble(4, ellipticity);
				update.setDouble(5, contrast);
				update.setInt(6, filament_id);
				update.setString(7, filament_name);
				
				update.executeUpdate();
				
				System.out.println("Updated filament (" + filament_id + ", " + filament_name + ")");
			}
			
			/* rimuovo i filamenti gia' presenti nel db
			in modo da avere a che fare con solo nuovi filamenti */
			st.executeUpdate("DELETE FROM filament_temp " +
							  "WHERE (id, name) IN (SELECT id, name " +
												   "FROM filament)");
			
			/* inserimento nuovi filamenti */
			st.executeUpdate("INSERT INTO filament " +
							 "SELECT id, name, total_flux, mean_dens, mean_temp, ellipticity, contrast " +
							 "FROM filament_temp");
			
			/* inserimento filamento-satellite
			 * deve essere gia' presente nel db il relativo satellite*/
			st.executeUpdate("INSERT INTO fil_sat " +
			                  "SELECT FT.id, FT.name, S.id " +
			                  "FROM filament_temp FT join satellite S ON FT.satellite = S.name");
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		/* elimino la tabella temporanea (fine transazione)*/
		try {
			st.executeUpdate("DROP TABLE filament_temp");
			conn.commit();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}	
		
		tot_time = (double)(System.currentTimeMillis() - start_time) / 1000;
		
		System.out.println("Update successfully completed! (" + tot_time +"s)");
		
		return 0;
	}

	public static int importStarData(Connection conn, String file) {
		long start_time;
		double tot_time;
		Statement st;
		PreparedStatement update_s;
		
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			update_s = conn.prepareStatement("UPDATE star " +
											   "SET name = ?, type = ?, " +
											        "flux = ?, g_pos = (SELECT id FROM g_position WHERE g_lat = ? and g_long = ?) " +
											   "WHERE id = ?");
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		}	
		
		System.out.println("Update started...");
		start_time = System.currentTimeMillis();
		
		/* importo file csv in una tabella temporanea (inizio transazione)*/
		try {
			String query_create = "CREATE TABLE star_temp( " +
								    "id int PRIMARY KEY, " +
								    "name varchar(64), " +
								    "g_long float(25), " +
								    "g_lat float(25), " +
								    "flux float(25), " +
								    "type varchar(64))";
			
			String query_copy = "COPY star_temp " + 
					       		"FROM '" + file + "' " +
					       		"DELIMITER ',' CSV HEADER";
			
			st.executeUpdate(query_create);
			st.executeUpdate(query_copy);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return 1;
		}	
		
		/* inserisco (aggiorno) i dati relativi */
		try {
			int star_id;
			String star_name, type;
			double flux, g_long, g_lat;
			
			/* inserisco le nuove posizioni galattiche */
			st.executeUpdate("INSERT INTO g_position(g_lat, g_long) " +
							 "(SELECT DISTINCT g_lat, g_long " +
							 "FROM star_temp " +
							 "except " +
							 "SELECT DISTINCT g_lat, g_long " + 
							 "FROM star_temp " +
							 "WHERE (g_lat, g_long) IN (SELECT g_lat, g_long FROM g_position))");
			
			/* aggiornamento stelle gia' esistenti */
			ResultSet rs = st.executeQuery("SELECT * " + 
										   "FROM star_temp " +
										   "WHERE id IN (SELECT id " +
												   		"FROM star)");
			
			while(rs.next()) {
				star_id = rs.getInt("id");
				star_name = rs.getString("name");
				flux = rs.getDouble("flux");
				g_long = rs.getDouble("g_long");
				g_lat = rs.getDouble("g_lat");
				type = rs.getString("type");
				
				update_s.setString(1, star_name);
				update_s.setString(2, type);
				update_s.setDouble(3, flux);
				update_s.setDouble(4, g_lat);
				update_s.setDouble(5, g_long);
				update_s.setInt(6, star_id);

				update_s.executeUpdate();
				
				System.out.println("Updated star " + star_id);
			}
			
			/* rimuovo le stelle gia' presenti nel db
			in modo da avere a che fare con solo nuove */
			st.executeUpdate("DELETE FROM star_temp " +
							  "WHERE id IN (SELECT id " +
										   "FROM star)");
			
			/* inserimento nuove stelle */
			st.executeUpdate("INSERT INTO star " +
							  "SELECT ST.id, ST.name, ST.type, ST.flux, G.id " +
							  "FROM star_temp ST JOIN g_position G ON (ST.g_long = G.g_long AND ST.g_lat = G.g_lat)");
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		/* elimino la tabella temporanea (fine transazione) */
		try {
			st.executeUpdate("DROP TABLE star_temp");
			conn.commit();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}	
		
		tot_time = (double)(System.currentTimeMillis() - start_time) / 1000;
		
		System.out.println("Update successfully completed! (" + tot_time +"s)");
		
		return 0;

	}

	public static int importBoundaryData(Connection conn, String sat, String file){
		long start_time;
		double tot_time;
		Statement st;
		
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		}
		
		System.out.println("Update started...");
		start_time = System.currentTimeMillis();
		
		/* importo file csv in una tabella temporanea (inizio transazione) */
		try {
			String query_create = "CREATE TABLE boundary_temp( " +
								    "id SERIAL PRIMARY KEY, " +
								    "filament_id int, " +
								    "g_long float(25), " +
								    "g_lat float(25))";
			
			String query_copy = "COPY boundary_temp(filament_id, g_long, g_lat) " + 
					       		"FROM '" + file + "' " +
					       		"DELIMITER ',' CSV HEADER";
			
			st.executeUpdate(query_create);
			st.executeUpdate(query_copy);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return 1;
		}	
		
		/* inserisco (aggiorno) i dati relativi */
		try {
			
			/* inserisco le nuove posizioni galattiche */
			st.executeUpdate("INSERT INTO g_position(g_lat, g_long) " +
							 "(SELECT DISTINCT g_lat, g_long " +
							 "FROM boundary_temp " +
							 "except " +
							 "SELECT DISTINCT g_lat, g_long " + 
							 "FROM boundary_temp " +
							 "WHERE (g_lat, g_long) IN (SELECT g_lat, g_long FROM g_position))");
			
			/* rimuovo i punti gia' presenti nel db e gli id dei filamenti non esistenti
			in modo da avere a che fare con solo nuovi (punti) e filamenti esistenti */
			st.executeUpdate("DELETE FROM boundary_temp " +
							  "WHERE filament_id NOT IN (SELECT id " +
									  					"FROM filament) " +
							  		"OR (filament_id, g_long, g_lat) IN (SELECT B.filament_id, G.g_long, G.g_lat " +
							  											"FROM boundary B JOIN g_position G ON B.g_pos = G.id " +
							  												"JOIN fil_sat FS ON (B.filament_id = FS.filament_id AND B.filament_name = FS.filament_name) " + 
							  												"JOIN satellite S ON S.id = FS.satellite " +
							  											"WHERE S.name = '" + sat + "')");
			
			/* inserisco i nuovi punti per i filamenti esistenti
			(non possono esistere filamenti con stesso id e stesso satellite) */
			st.executeUpdate("INSERT INTO boundary " +
							  "(SELECT DISTINCT BT.filament_id, FS.filament_name, G.id " +
							  "FROM boundary_temp BT JOIN g_position G ON (BT.g_long = G.g_long AND BT.g_lat = G.g_lat) " +
							  		"JOIN fil_sat FS ON FS.filament_id = BT.filament_id  JOIN satellite S ON S.id = FS.satellite " +
							  "WHERE S.name = '" + sat + "')");
				
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		/* elimino la tabella temporanea (fine transazione)*/
		try {
			st.executeUpdate("DROP TABLE boundary_temp");
			conn.commit();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		tot_time = (double)(System.currentTimeMillis() - start_time) / 1000;
		
		System.out.println("Update successfully completed! (" + tot_time +"s)");
		
		return 0;
	}

	public static int importBranchData(Connection conn, String sat, String file) {
		long start_time;
		double tot_time;
		Statement st;
		PreparedStatement find_point, insert_point, update_branch, update_branch_point;
		
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			find_point = conn.prepareStatement("SELECT * " +
							 				  "FROM branch_point " + 
							 				  "WHERE branch = ? AND g_pos = (SELECT id " +
							 											    "FROM g_position " +
							 											    "WHERE g_lat = ? AND g_long = ?)");

			insert_point = conn.prepareStatement("INSERT INTO branch_point VALUES (?,(SELECT id " +
			 									"FROM g_position " +
			 									"WHERE g_lat = ? AND g_long = ?),?,?)");                         

			update_branch = conn.prepareStatement("UPDATE branch " +
												 "SET type = ?, filament_id = ?, filament_name = ? " +
												 "WHERE id = ?");

			update_branch_point = conn.prepareStatement("UPDATE branch_point " +
													   "SET prog_num = ?, flux = ? " +
													   "WHERE branch = ? AND g_pos = (SELECT id " +
															   						 "FROM g_position " +
															   						 "WHERE g_lat = ? AND g_long = ?)");

		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		}	
		
		System.out.println("Update started...");
		start_time = System.currentTimeMillis();
		
		/* importo file csv in una tabella temporanea (inizio transazione) */
		try {
			String query_create = "CREATE TABLE branch_temp( " +
								    "id SERIAL PRIMARY KEY, " +
								    "filament_id int, " +
								    "branch_id int, " +
								    "type char, " +
								    "g_long float(25), " +
								    "g_lat float(25), " +
								    "prog_num int, " +
								    "flux float(25))";
			
			String query_copy = "COPY branch_temp(filament_id, branch_id, type, g_long, g_lat, prog_num, flux) " + 
					       		"FROM '" + file + "' " +
					       		"DELIMITER ',' CSV HEADER";

			st.executeUpdate(query_create);
			st.executeUpdate(query_copy);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return 1;
		}	
		
		/* inserisco (aggiorno) i dati relativi */
		try {
			int filament_id, branch_id, prog_num;
			String type, filament_name;
			double g_long, g_lat, flux;
			
			/* inserisco le nuove posizioni galattiche */
			st.executeUpdate("INSERT INTO g_position(g_lat, g_long) " +
							 "(SELECT DISTINCT g_lat, g_long " +
							 "FROM branch_temp " +
							 "except " +
							 "SELECT DISTINCT g_lat, g_long " + 
							 "FROM branch_temp " +
							 "WHERE (g_lat, g_long) IN (SELECT g_lat, g_long FROM g_position))");
			
			/* aggiornamento rami esistenti 
			 * (devono esistere gia', l'id del ramo e l'id del filamento) */
			ResultSet rs = st.executeQuery("SELECT BT.*, F.name " +
										    "FROM branch_temp BT JOIN filament F ON BT.filament_id = F.id " +
										    	"JOIN fil_sat FS ON (F.id = FS.filament_id AND F.name = FS.filament_name) " + 
										    	"JOIN satellite S ON S.id = FS.satellite " +
										    "WHERE S.name = '" + sat + "' AND BT.branch_id IN (SELECT id " +
												   				                              "FROM branch) " +
												  "AND (BT.filament_id, F.name) IN (SELECT id, name " +
														  						   "FROM filament)");
			
			while(rs.next()) {
				filament_name = rs.getString("name");
				filament_id = rs.getInt("filament_id");
				branch_id = rs.getInt("branch_id");
				type = rs.getString("type");
				prog_num = rs.getInt("prog_num");
				flux = rs.getDouble("flux");
				g_long = rs.getDouble("g_long");
				g_lat = rs.getDouble("g_lat");
				 
				update_branch.setString(1, type);
				update_branch.setInt(2, filament_id);
				update_branch.setString(3, filament_name);
				update_branch.setInt(4, branch_id);
				 
				update_branch.executeUpdate(); 
				
				System.out.println("Updated branch (" + branch_id + ")");
				
				find_point.setInt(1, branch_id);
				find_point.setDouble(2, g_lat);
				find_point.setDouble(3, g_long);
				
				ResultSet rs2 = find_point.executeQuery();
				
				/* controlle se il punto esiste */
				if (rs2.next()) {
					update_branch_point.setInt(1, prog_num);
					update_branch_point.setDouble(2, flux);
					update_branch_point.setInt(3, branch_id);
					update_branch_point.setDouble(4, g_lat);
					update_branch_point.setDouble(5, g_long);
					
					update_branch_point.executeUpdate();
					
					System.out.println("Updated branch point (" + branch_id + ", " + g_long + ", " + g_lat + ")");
				}
				else {
					insert_point.setInt(1, branch_id);
					insert_point.setDouble(2, g_lat);
					insert_point.setDouble(3, g_long);
					insert_point.setInt(4, prog_num);
					insert_point.setDouble(5, flux);
					
					insert_point.executeUpdate();
					
					System.out.println("Inserted branch point (" + branch_id + ", " + g_long + ", " + g_lat + ")");
				}
			}
			
			/* rimuovo i rami (e i punti) gia' presenti nel db
			in modo da avere a che fare con solo nuovi */
			st.executeUpdate("DELETE FROM branch_temp " +
							  "WHERE branch_id IN (SELECT id " +
										   		  "FROM branch)");
			
			/* inserimento nuovi rami */
			st.executeUpdate("INSERT INTO branch " +
							  "SELECT DISTINCT BT.branch_id, BT.type, BT.filament_id, FS.filament_name " +
							  "FROM branch_temp BT JOIN fil_sat FS ON FS.filament_id = BT.filament_id " +
							  		"JOIN satellite S ON S.id = FS.satellite " +
							  "WHERE S.name = '" + sat + "'");
			
			/* inserimento nuovi punti */
			st.executeUpdate("INSERT INTO branch_point " +
							  "SELECT BT.branch_id, G.id, BT.prog_num, BT.flux " +
							  "FROM branch_temp BT JOIN g_position G ON (BT.g_long = G.g_long AND BT.g_lat = G.g_lat)");		
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		/* elimino la tabella temporanea (fine transazione) */
		try {
			st.executeUpdate("DROP TABLE branch_temp");
			conn.commit();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			return -1;
		}
		
		tot_time = (double)(System.currentTimeMillis() - start_time) / 1000;
		
		System.out.println("Update successfully completed! (" + tot_time +"s)");
		
		return 0;
	}
}