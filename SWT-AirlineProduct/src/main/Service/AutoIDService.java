package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutoIDService {

	/**
	 * Returns a string that represents the next unique id for the given table. The prefix will
	 * precede the number value in the id.
	 * @param table
	 * @param prefix
	 * @return
	 */
	public static String generateAutoID(String table, String prefix) {
		
		try {

			// Creates a connection to communicate with the database.
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = NetworkService.getInstance().getConnection();
			Statement s = con.createStatement();

			// Selects all rows from the table name that was passed to the class.
			ResultSet rs = s.executeQuery("select MAX(id) from " + table);
			rs.next();
			rs.getString("MAX(id)");

			if (prefix.isEmpty()) {
				return null;
			}

			// If no ID entries exist in the database, increment to the first row.
			if (rs.getString("MAX(id)") == null) {
				return prefix + "001";

			// Else, increment to 1 more than the last ID entry that exists in the database.
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(2, rs.getString("MAX(id)").length()));
				id++;
				return prefix + String.format("%03d", id);
			}
		} catch (Exception ex) {
			return null;
		}
	}
}
