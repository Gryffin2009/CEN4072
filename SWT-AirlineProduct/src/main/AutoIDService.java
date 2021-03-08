package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoIDService {

	
	// returns a string that represents the next unique id 
	// for the given table. The prefix will precede the number
	// value in the id.
	public static String generateAutoID(String table, String prefix) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Environment.DATABASE_PATH, "root", Environment.DATABASE_PASSWORD);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select MAX(id) from " + table);
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				return prefix + "001";
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(2, rs.getString("MAX(id)").length()));
				id++;
				return prefix + String.format("%03d", id);
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}
}
