package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;
import connection.dbConnector;
import controller.tableAdvisorListener;
import model.advisor;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class advisorDao implements DaoInterface<advisor> {
	private String sqlSelectAll = "SELECT avs.code, indentity, avs.name, gender, birthday, phone, email, address, cl.name 'className' "
			+ "FROM advisors avs LEFT JOIN teach tch ON avs.code = tch.advisorCode LEFT JOIN class cl ON tch.classCode = cl.code ";

	// Method to call direct, don't create new class
	public static advisorDao getInstance() {
		return new advisorDao();
	}

	// Method to search advisor (by filter)
	public ArrayList<advisor> search(int searchFilter, String searchData) {
		ArrayList<advisor> result = new ArrayList<advisor>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql;
			String sqlKeyFields[] = { "avs.code", "indentity", "avs.name", "gender", "birthday", "phone", "email", "address", "cl.name" };
			if (searchFilter == 0) {
				sql = sqlSelectAll
						+ "WHERE avs.code LIKE ? OR indentity LIKE ? OR avs.name LIKE ? OR gender LIKE ? OR birthday LIKE ? "
						+ "OR phone LIKE ? OR email LIKE ? OR address LIKE ? OR cl.name LIKE ?";
			} else {
				sql = sqlSelectAll + " WHERE " + sqlKeyFields[(searchFilter - 1)] + " LIKE ?";
			}
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			if (searchFilter == 0) {
				for (int i = 1; i <= 9; i++) {
					pst.setString(i, "%" + searchData + "%");
					// Handle data search include male, female
					if (searchData.equalsIgnoreCase("Nam")) {
						pst.setString(4, "%" + '1' + "%");
					} else if (searchData.equalsIgnoreCase("Nữ")) {
						pst.setString(4, "%" + '0' + "%");
					}
				}
			// Handle male, female search
			} else if (searchFilter == 4) {
				if (searchData.equalsIgnoreCase("Nam")) {
					pst.setString(1, "%" + '1' + "%");
				} else if (searchData.equalsIgnoreCase("Nữ")) {
					pst.setString(1, "%" + '0' + "%");
				}
			} else {
				pst.setString(1, "%" + searchData + "%");
			}
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String code = rs.getString("code");
				String indentity = rs.getString("indentity");
				String name = rs.getString("name");
				int gender = rs.getInt("gender");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String className = rs.getString("className");
				if (className == null) {
					className = "Chưa đăng ký";
				}
				result.add(new advisor(code, indentity, name, gender, birthday, phone, email, address, className));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Method to read one specific advisor
	public advisor read(advisor avs) {
		advisor result = null;
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = sqlSelectAll + "WHERE code=?";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			pst.setString(1, avs.getCode());
//			ResultSet rs = pst.executeQuery();
//			// B4: Handle data
//			while (rs.next()) {
//				String code = rs.getString("code");
//				String indentity = rs.getString("indentity");
//				String name = rs.getString("name");
//				int gender = rs.getInt("gender");
//				String birthday = rs.getString("birthday");
//				String phone = rs.getString("phone");
//				String email = rs.getString("email");
//				String address = rs.getString("address");
//				String className = rs.getString("className");
//				result = new advisor(code, indentity, name, gender, birthday, phone, email, address, className);
//			}
//			System.out.println("Your SQL Query: " + sql);
//			// B5: Close connection
//			dbConnector.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}

	// Method to read all student
	public ArrayList<advisor> readAll() {
		ArrayList<advisor> result = new ArrayList<advisor>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = sqlSelectAll;
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String code = rs.getString("code");
				String indentity = rs.getString("indentity");
				String name = rs.getString("name");
				int gender = rs.getInt("gender");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String className = rs.getString("className");
				if (className == null) {
					className = "Chưa đăng ký";
				}
				result.add(new advisor(code, indentity, name, gender, birthday, phone, email, address, className));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read all indentity id of advisor
	public ArrayList<String> readAllIndentity() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT indentity FROM advisors";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String indentity = rs.getString("indentity");
				result.add(indentity);
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read all phone of advisor
	public ArrayList<String> readAllPhone() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT phone FROM advisors";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// B4: Handle data
			while (rs.next()) {
				String phone = rs.getString("phone");
				result.add(phone);
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Method to create a advisor
	public int create(advisor avs) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "INSERT INTO advisors (code, indentity, name, gender, birthday, phone, email, address) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			String sql3 = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql), pst3 = con.prepareStatement(sql3);
			pst.setString(1, avs.getCode());
			pst.setString(2, avs.getIndentity());
			pst.setString(3, avs.getName());
			pst.setInt(4, avs.getGender());
			pst.setString(5, avs.getBirthday());
			pst.setString(6, avs.getPhone());
			pst.setString(7, avs.getEmail());
			pst.setString(8, avs.getAddress());
			result = pst.executeUpdate();
			sqlFull += sql;
			if (!avs.getClassName().equals("Choose class")) {
				String sql2 = "INSERT INTO teach (advisorCode, classCode) VALUES (?, ?)";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, avs.getCode());
				String classCode = classxDao.getInstance().readByName(avs.getClassName()).getCode();
				pst2.setString(2, classCode);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			}
			pst3.setString(1, avs.getEmail());
			String passwordHash = BCrypt.hashpw("123456", BCrypt.gensalt(6));
			pst3.setString(2, passwordHash);
			pst3.setInt(3, 1);
			result += pst3.executeUpdate();
			sqlFull += "\n" + sql3;
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sqlFull + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Method to update a advisor
	public int update(advisor avs) {
		String oldEmail = tableAdvisorListener.email;
		String oldClassName = tableAdvisorListener.className;
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "UPDATE advisors SET name=?, gender=?, birthday=?, phone=?, email=?, address=? WHERE code=?";
			String sql3 = "UPDATE users SET email=? WHERE email=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql), pst3 = con.prepareStatement(sql3);
			pst.setString(1, avs.getName());
			pst.setInt(2, avs.getGender());
			pst.setString(3, avs.getBirthday());
			pst.setString(4, avs.getPhone());
			pst.setString(5, avs.getEmail());
			pst.setString(6, avs.getAddress());
			pst.setString(7, avs.getCode());
			result = pst.executeUpdate();
			sqlFull += sql;
			if (!oldClassName.equals("Chưa đăng ký") && !avs.getClassName().equals("Choose class")) {
				String sql2 = "UPDATE teach SET classCode=? WHERE advisorCode=? AND classCode=?";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				String classCode = classxDao.getInstance().readByName(avs.getClassName()).getCode();
				String classCodeOld = classxDao.getInstance().readByName(oldClassName).getCode();
				pst2.setString(1, classCode);
				pst2.setString(2, avs.getCode());
				pst2.setString(3, classCodeOld);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			} else if (oldClassName.equals("Chưa đăng ký") && !avs.getClassName().equals("Choose class")) {
				String sql2 = "INSERT INTO teach (advisorCode, classCode) VALUES (?, ?)";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, avs.getCode());
				String classCode = classxDao.getInstance().readByName(avs.getClassName()).getCode();
				pst2.setString(2, classCode);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			} else if (!oldClassName.equals("Chưa đăng ký") && avs.getClassName().equals("Choose class")) {
				String sql2 = "DELETE FROM teach WHERE advisorCode=? AND classCode=?";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, avs.getCode());
				String classCode = classxDao.getInstance().readByName(oldClassName).getCode();
				pst2.setString(2, classCode);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			}
			pst3.setString(1, avs.getEmail());
			pst3.setString(2, oldEmail);
			result += pst3.executeUpdate();
			sqlFull += "\n" + sql3;
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sqlFull + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Method to delete a advisor
	public int delete(advisor avs) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "DELETE FROM advisors WHERE code=?";
			String sql2 = "DELETE FROM users WHERE email=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql), pst2 = con.prepareStatement(sql2);
			pst.setString(1, avs.getCode());
			result = pst.executeUpdate();
			pst2.setString(1, avs.getEmail());
			result += pst2.executeUpdate();
			sqlFull += sql + "\n" + sql2;
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sqlFull + "\n" + result + " row updated.");
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}