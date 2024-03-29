package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.LoginInterface;
import view.MainInterface1;

public class UserHandler {
	public static boolean login(String user_id, String pass,Connection con) throws SQLException, ClassNotFoundException {
		PreparedStatement statement;
		String query = "select * from users where (user_id=?)";
		statement = con.prepareStatement(query);
		statement.setString(1, user_id);
		ResultSet result = null;
		result = statement.executeQuery();
		if (result.next() == true) {
			//OK
		} else {
			JOptionPane.showMessageDialog(null, "User doesn't exist.");
			LoginInterface LoginIn=new LoginInterface();
		    LoginIn.setVisible(true);
		    LoginIn.setResizable(false);
		    return false;
		}
		PreparedStatement statement2;
		String query2 = "select * from users where (user_id=? and pass =md5(?))";
		statement2 = con.prepareStatement(query2);
		statement2.setString(1, user_id);
		statement2.setString(2, pass);
		ResultSet result2 = null;
		try {
			result2 = statement2.executeQuery();
		} catch(SQLException e1) {
		}
		if (result2.next() == true) {
			boolean type = result.getBoolean("type");
			String privilege = (type) ? "admin" : "user"; 
			JOptionPane.showMessageDialog(null, "You are now connected to the database as "+ privilege+"!");
			MainInterface1 MainIn=new MainInterface1(con,user_id,type);
		    MainIn.setVisible(true);
		    MainIn.setResizable(false);
		    return true;
		} else {
			JOptionPane.showMessageDialog(null, "Password incorrect!");
			LoginInterface LoginIn=new LoginInterface();
		    LoginIn.setVisible(true);
		    LoginIn.setResizable(false);
		    return false;
		}
	}
	
	public static boolean register(String name, String surname, String user_id,
			String password,String e_mail, boolean type, Connection con) throws SQLException {
		//user id e mail devono essere diversi
		Statement query= con.createStatement();
		ResultSet result = query.executeQuery("select user_id,e_mail from users");
		while (result.next()) {
			String user_idC = result.getString("user_id");
			String e_mailC = result.getString("e_mail");
			if (user_id.equals(user_idC)) {
				JOptionPane.showMessageDialog(null, "Username already in use");
				return false;
			} else {
				// DO NOTHING
			}
			if (e_mail.equals(e_mailC)) {
				JOptionPane.showMessageDialog(null, "E-mail already in use");
				return false;
			} else {
				// DO NOTHING
			}
		}
		PreparedStatement statement;
		String insert = "insert into users(name,surname,user_id,pass,e_mail,type) values (?,?,?,md5(?),?,?)";
		statement = con.prepareStatement(insert);
		statement.setString(1, name);
		statement.setString(2, surname);
		statement.setString(3, user_id);
		statement.setString(4, password);
		statement.setString(5, e_mail);
		statement.setBoolean(6, type);
		statement.executeUpdate();
		JOptionPane.showMessageDialog(null, "User successfully registered!");
		return true;
	}
}

