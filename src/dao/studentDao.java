package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;
import connection.dbConnector;
import controller.tableStudentListener;
import model.student;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class studentDao implements DaoInterface<student> {
	private String sqlSelectAll = "SELECT st.code, indentity, st.name, gender, birthday, phone, email, address, job, cl.name 'className', " +
								"point FROM students st LEFT JOIN learn ln ON st.code = ln.studentCode LEFT JOIN class cl ON ln.classCode = cl.code ";
    
	// Method to call direct, don't create new class
	public static studentDao getInstance() {
		return new studentDao();
	}
	
	// Method to search student (by filter)
	public ArrayList<student> search(int searchFilter, String searchData) {
		ArrayList<student> result = new ArrayList<student>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql;
			String sqlKeyFields[] = { "st.code", "indentity", "st.name", "gender", "birthday", "phone", "email", "address", "job", "cl.name", "point" };
			if (searchFilter == 0) {
				sql = sqlSelectAll
						+ "WHERE st.code LIKE ? OR indentity LIKE ? OR st.name LIKE ? OR gender LIKE ? OR birthday LIKE ? "
						+ "OR phone LIKE ? OR email LIKE ? OR address LIKE ? OR job LIKE ? OR cl.name LIKE ? OR point LIKE ?";
			// Handle point search same -1 not exam
			} else if (searchFilter == 11) {
				sql = sqlSelectAll + " WHERE " + sqlKeyFields[(searchFilter - 1)] + " LIKE ? AND point<>-1";
			} else {
				sql = sqlSelectAll + " WHERE " + sqlKeyFields[(searchFilter - 1)] + " LIKE ?";
			}
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			if (searchFilter == 0) {
				for (int i = 1; i <= 11; i++) {
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
				String job = rs.getString("job");
				String className = rs.getString("className");
				double point = rs.getDouble("point");
				if (className == null) {
					className = "Chưa đăng ký";
					point = -1;
				}
				result.add(new student(code, indentity, name, gender, birthday, phone, email, address, job, className, point));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read one specific student
	public student read(student st) {
		student result = null;
//		try {
//			// B1: Create connection to db
//			Connection con = dbConnector.getConnection();
//			// B2: Create sql query
//			String sql = sqlSelectAll + "WHERE code=?";
//			// B3: Create PreparedStatement object
//			PreparedStatement pst = con.prepareStatement(sql);
//			pst.setString(1, st.getCode());
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
//				String job = rs.getString("job");
//				String className = rs.getString("className");
//				double point = rs.getDouble("point");
//				result = new student(code, indentity, name, gender, birthday, phone, email, address, job, className, point);
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
	public ArrayList<student> readAll() {
		ArrayList<student> result = new ArrayList<student>();
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
				String job = rs.getString("job");
				String className = rs.getString("className");
				double point = rs.getDouble("point");
				if (className == null) {
					className = "Chưa đăng ký";
					point = -1;
				}
				result.add(new student(code, indentity, name, gender, birthday, phone, email, address, job, className, point));
			}
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to read all indentity id of student
	public ArrayList<String> readAllIndentity() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT indentity FROM students";
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
	
	// Method to read all phone of student
	public ArrayList<String> readAllPhone() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT phone FROM students";
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

	// Method to create a student
	public int create(student st, String passwordInput) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "INSERT INTO students (code, indentity, name, gender, birthday, phone, email, address, job) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sql3 = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql),
							pst3 = con.prepareStatement(sql3);
			pst.setString(1, st.getCode());
			pst.setString(2, st.getIndentity());
			pst.setString(3, st.getName());
			pst.setInt(4, st.getGender());
			pst.setString(5, st.getBirthday());
			pst.setString(6, st.getPhone());
			pst.setString(7, st.getEmail());
			pst.setString(8, st.getAddress());
			pst.setString(9, st.getJob());
			result = pst.executeUpdate();
			sqlFull += sql;
			if (!st.getClassName().equals("Choose class")) {
				String sql2 = "INSERT INTO learn (studentCode, classCode, point) VALUES (?, ?, ?)";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, st.getCode());
				String classCode = classxDao.getInstance().readByName(st.getClassName()).getCode();
				pst2.setString(2, classCode);
				pst2.setDouble(3, st.getPoint());
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			}
			pst3.setString(1, st.getEmail());
			String passwordHash = BCrypt.hashpw(passwordInput, BCrypt.gensalt(6));
			pst3.setString(2, passwordHash);
			pst3.setInt(3, 0);
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

	// Method to update a student
	public int update(student st) {
		String oldEmail = tableStudentListener.email;
		String oldClassName = tableStudentListener.className;
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "UPDATE students SET name=?, gender=?, birthday=?, phone=?, email=?, address=?, job=? WHERE code=?";
			String sql3 = "UPDATE users SET email=? WHERE email=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql),
							pst3 = con.prepareStatement(sql3);
			pst.setString(1, st.getName());
			pst.setInt(2, st.getGender());
			pst.setString(3, st.getBirthday());
			pst.setString(4, st.getPhone());
			pst.setString(5, st.getEmail());
			pst.setString(6, st.getAddress());
			pst.setString(7, st.getJob());
			pst.setString(8, st.getCode());
			result = pst.executeUpdate();
			sqlFull += sql;
			if (!oldClassName.equals("Chưa đăng ký") && !st.getClassName().equals("Choose class")) {
				String sql2 = "UPDATE learn SET classCode=?, point=? WHERE studentCode=? AND classCode=?";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				String classCode = classxDao.getInstance().readByName(st.getClassName()).getCode();
				String classCodeOld = classxDao.getInstance().readByName(oldClassName).getCode();
				pst2.setString(1, classCode);
				pst2.setDouble(2, st.getPoint());
				pst2.setString(3, st.getCode());
				pst2.setString(4, classCodeOld);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			} else if (oldClassName.equals("Chưa đăng ký") && !st.getClassName().equals("Choose class")) {
				String sql2 = "INSERT INTO learn (studentCode, classCode, point) VALUES (?, ?, ?)";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, st.getCode());
				String classCode = classxDao.getInstance().readByName(st.getClassName()).getCode();
				pst2.setString(2, classCode);
				pst2.setDouble(3, st.getPoint());
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			} else if (!oldClassName.equals("Chưa đăng ký") && st.getClassName().equals("Choose class")) {
				String sql2 = "DELETE FROM learn WHERE studentCode=? AND classCode=?";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, st.getCode());
				String classCode = classxDao.getInstance().readByName(oldClassName).getCode();
				pst2.setString(2, classCode);
				result += pst2.executeUpdate();
				sqlFull += "\n" + sql2;
			}
			pst3.setString(1, st.getEmail());
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
	
	// Method to delete a student
	public int delete(student st) {
		int result = 0;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sqlFull = "";
			String sql = "DELETE FROM students WHERE code=?";
			String sql2 = "DELETE FROM users WHERE email=?";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql),
								pst2 = con.prepareStatement(sql2);
			pst.setString(1, st.getCode());
			result = pst.executeUpdate();
			pst2.setString(1, st.getEmail());
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
	
	// Method to read and return new student code to create
	public static String getNewStudentCode() {
		String result = null;
		try {
			// B1: Create connection to db
			Connection con = dbConnector.getConnection();
			// B2: Create sql query
			String sql = "SELECT * FROM students ORDER BY code DESC LIMIT 1";
			// B3: Create PreparedStatement object
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String lastSTCode = rs.getString("code");
				String zhHv = lastSTCode.substring(0, lastSTCode.length() - 2);
				int num = Integer.parseInt(lastSTCode.substring(lastSTCode.length() - 2)) + 1;
				String numStr = String.format("%02d", num);
				result = zhHv + numStr;
			}
			// B4: Handle data
			System.out.println("Your SQL Query:\n" + sql);
			// B5: Close connection
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int create(student x) {
		return 0;
	}
	
}