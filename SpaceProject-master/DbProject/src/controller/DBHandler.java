package controller;

/*
 * Classe Java che ha l’obiettivo di nascondere tutti i dettagli
 * di creazione di una connessione e gestione interrogazioni.
 * In particolare implementa i seguenti metodi:
 *	• getConnection() : restituisce un oggetto Connection
 */

import java.sql.*;

public class DBHandler {
	private final String dbURI = "jdbc:postgresql://localhost/my_db";
	private final String user = "postgres";
	private final String password = "postgres";

	private Connection con;
	
	public DBHandler() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
	}	
	public Connection getConnection() throws SQLException {
		if (this.con == null || this.con.isClosed()){
			this.con = DriverManager.getConnection(dbURI, user, password);
		}
		return this.con;
	}
}