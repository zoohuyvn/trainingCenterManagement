package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;
import connection.dbConnector;
import model.user;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class authorizationDao {
	
	// Method to call direct, don't create new authorization object
	public static authorizationDao getInstance() {
		return new authorizationDao();
	}
	
	// Method to authorize a user
	public user authorization(user us) {
		user result = null;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT * FROM users WHERE email=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, us.getEmail());
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String email = rs.getString("email");
				String password = "";
				boolean match = BCrypt.checkpw(us.getPassword(), rs.getString("password"));
				int role = rs.getInt("role");
		        if (match) {
					password = rs.getString("password");
					result = new user(email, password, role);
		        } else {
		        	return new user(email, "Wrong password!", -1);
		        }
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read all user
//	public ArrayList<user> readAll() {
//		ArrayList<user> result = new ArrayList<user>();
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = "SELECT * FROM users";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			ResultSet rs = pst.executeQuery();
//			// B4: Handle data
//			while (rs.next()) {
//				String email = rs.getString("email");
//				String password = rs.getString("password");
//				int role = rs.getInt("role");
//				result.add(new user(email, password, role));
//			}
//			System.out.println("Your SQL Query:\n" + sql);
//			// B5: Close connection
//			dbConnector.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	// Method to read all indentity id of student
	public ArrayList<String> readAllEmail() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT email FROM users";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String email = rs.getString("email");
				result.add(email);
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to create a user
//	public int create(user us) {
//		int result = 0;
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			// Encrypt password
//			String passwordHash = BCrypt.hashpw(us.getPassword(), BCrypt.gensalt(6));
//			pst.setString(1, us.getEmail());
//			pst.setString(2, passwordHash);
//			pst.setInt(3, us.getRole());
//			result = pst.executeUpdate();
//			// B4: Handle data
//			System.out.println("Your SQL Query:\n" + sql + "\n" + result + " row updated.");
//			// B5: Close connection
//			dbConnector.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	// Method to delete a user
//	public int delete(user us) {
//		int result = 0;
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = "DELETE FROM users WHERE email=?";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			pst.setString(1, us.getEmail());
//			result = pst.executeUpdate();
//			// B4: Handle data
//			System.out.println("Your SQL Query:\n" + sql + "\n" + result + " row updated.");
//			// B5: Close connection
//			dbConnector.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
}