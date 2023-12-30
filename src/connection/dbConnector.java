package connection;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class dbConnector {

	// Method to get connection from JDBC
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/training_center_management";
			String USERNAME = "root";
			String PASSWORD = "";
			con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("========== Connect successfully (Started on port: 3306) ==========");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connect failure!");
		}
		return con;
	}
	
	// Method to close connection from JDBC
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}