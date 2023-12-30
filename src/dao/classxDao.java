package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import connection.dbConnector;
import model.classx;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class classxDao implements DaoInterface<classx> {
	String sqlSelectAll = "SELECT * FROM class ";
    
	// Method to call direct, don't create new class
	public static classxDao getInstance() {
		return new classxDao();
	}
	
	// Method to search class (by filter)
	public ArrayList<classx> search(int searchFilter, String searchData) {
		ArrayList<classx> result = new ArrayList<classx>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql;
			String sqlKeyFields[] = { "code", "name", "startDay", "price" };
			if (searchFilter == 0) {
				sql = sqlSelectAll
						+ "WHERE code LIKE ? OR name LIKE ? OR startDay LIKE ? OR price LIKE ?";
			} else {
				sql = sqlSelectAll + " WHERE " + sqlKeyFields[(searchFilter - 1)] + " LIKE ?";
			}
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			if (searchFilter == 0) {
				for (int i = 1; i <= 4; i++) {
					pst.setString(i, "%" + searchData + "%");
				}
			} else {
				pst.setString(1, "%" + searchData + "%");
			}
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				String startDay = rs.getString("startDay");
				double price = rs.getDouble("price");
				result.add(new classx(code, name, startDay, price));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read one specific class
	public classx read(classx cl) {
		classx result = null;
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = "SELECT * FROM class WHERE code=?";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			pst.setString(1, cl.getCode());
//			ResultSet rs = pst.executeQuery();
//			// B4: Handle data
//			while (rs.next()) {
//				String code = rs.getString("code");
//				String name = rs.getString("name");
//				String startDay = rs.getString("startDay");
//				double price = rs.getDouble("price");
//				result = new classx(code, name, startDay, price);
//			}
//			System.out.println("Your SQL Query:\n" + sql);
//			// B5: Close connection
//			dbConnector.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}
	
	public classx readByName(String nameInput) {
		classx result = null;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT * FROM class WHERE name=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nameInput);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				String startDay = rs.getString("startDay");
				double price = rs.getDouble("price");
				result = new classx(code, name, startDay, price);
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read all class
	public ArrayList<classx> readAll() {
		ArrayList<classx> result = new ArrayList<classx>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT * FROM class";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				String startDay = rs.getString("startDay");
				double price = rs.getDouble("price");
				result.add(new classx(code, name, startDay, price));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to create a class
	public int create(classx cl) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "INSERT INTO class (code, name, startDay, price) VALUES (?, ?, ?, ?)";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getCode());
			pst.setString(2, cl.getName());
			pst.setString(3, cl.getStartDay());
			pst.setDouble(4, cl.getPrice());
			result = pst.executeUpdate();
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sql + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Method to update a class
	public int update(classx cl) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "UPDATE class SET name=?, startDay=?, price=? WHERE code=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getName());
			pst.setString(2, cl.getStartDay());
			pst.setDouble(3, cl.getPrice());
			pst.setString(4, cl.getCode());
			result = pst.executeUpdate();
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sql + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to delete a class
	public int delete(classx cl) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "DELETE FROM class WHERE code=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getCode());
			result = pst.executeUpdate();
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sql + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}