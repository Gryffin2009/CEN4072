package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Environment;

public class NetworkService {

	private static NetworkService instance = null;
	private Connection con;
	
	private NetworkService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Environment.DATABASE_PATH, "root", Environment.DATABASE_PASSWORD);
		} catch (Exception ex) {
			Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static NetworkService getInstance() {
		if (instance == null)
			instance = new NetworkService();
		return instance;
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	
}
