package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import connection.dbConnector;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class analyticDao {
	
	// Method to get total revenue of class
	public static double getTotalRevenue() {
		double totalRevenue = 0;
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT ln.classCode, COUNT(ln.classCode) totalClassReged, cl.price * COUNT(ln.classCode) totalPriceOfClass "
						+ "FROM learn ln "
						+ "JOIN class cl ON ln.classCode = cl.code "
						+ "GROUP BY ln.classCode, cl.price "
						+ "ORDER BY ln.classCode";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double totalPriceOfClass = rs.getDouble("totalPriceOfClass");
				totalRevenue += totalPriceOfClass;
			}
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRevenue;
	}
	
	// Method to get total of student
	public static int getTotalStudent() {
		int totalStudent = 0;
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT COUNT(*) totalStudent FROM students";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double totalStudentI = rs.getDouble("totalStudent");
				totalStudent += totalStudentI;
			}
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalStudent;
	}
	
	// Method to get total of advisor
	public static int getTotalAdvisor() {
		int totalAdvisor = 0;
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT COUNT(*) totalAdvisor FROM advisors";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double totalAdvisorI = rs.getDouble("totalAdvisor");
				totalAdvisor += totalAdvisorI;
			}
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAdvisor;
	}
	
	// Method to get total of class
	public static int getTotalClass() {
		int totalClass = 0;
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT COUNT(*) totalClass FROM class";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double totalClassI = rs.getDouble("totalClass");
				totalClass += totalClassI;
			}
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalClass;
	}
	
	// Method to get student, advisor of each class
	public static ArrayList<ArrayList<String>> getStAvsOfClass() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> listClassName = new ArrayList<String>();
		ArrayList<String> listStQuantity = new ArrayList<String>();
		ArrayList<String> listAvsQuantity = new ArrayList<String>();
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT cl.name, COUNT(DISTINCT ln.studentCode) studentQuantity, COUNT(DISTINCT tch.advisorCode) advisorQuantity "
						+ "FROM class cl "
						+ "LEFT JOIN learn ln ON cl.code = ln.classCode "
						+ "LEFT JOIN teach tch ON cl.code = tch.classCode "
						+ "GROUP BY cl.name "
						+ "ORDER BY cl.code";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String className = rs.getString("name");
				String stQuantity = rs.getString("studentQuantity");
				String avsQuantity = rs.getString("advisorQuantity");
				listClassName.add(className);
				listStQuantity.add(stQuantity);
				listAvsQuantity.add(avsQuantity);
			}
			result.add(listClassName);
			result.add(listStQuantity);
			result.add(listAvsQuantity);
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to get average point of each class
	public static ArrayList<ArrayList<String>> getAvgPointOfClass() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> listClassName = new ArrayList<String>();
		ArrayList<String> listAvgPoint = new ArrayList<String>();
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT cl.name, AVG(point) average "
						+ "FROM learn ln "
						+ "JOIN class cl ON ln.classCode = cl.code WHERE point >= 0 "
						+ "GROUP BY cl.name "
						+ "ORDER BY cl.code";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String className = rs.getString("name");
				String avgPoint = rs.getString("average");
				listClassName.add(className);
				listAvgPoint.add(avgPoint);
			}
			result.add(listClassName);
			result.add(listAvgPoint);
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Method to get score rate of each class
	public static ArrayList<Integer> getScoreRate() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			Connection con = dbConnector.getConnection();
			String sql = "SELECT "
						+ "SUM(CASE WHEN point < 4.0 AND point <> -1.0 THEN 1 ELSE 0 END) 'poor', "
						+ "SUM(CASE WHEN (point >= 4.0 AND point <= 5.4) AND point <> -1.0 THEN 1 ELSE 0 END) 'weak', "
						+ "SUM(CASE WHEN (point > 5.4 AND point <= 6.9) AND point <> -1.0 THEN 1 ELSE 0 END) 'average', "
						+ "SUM(CASE WHEN (point > 6.9 AND point <= 8.4) AND point <> -1.0 THEN 1 ELSE 0 END) 'pretty', "
						+ "SUM(CASE WHEN point > 8.4 AND point <> -1.0 THEN 1 ELSE 0 END) 'good' "
						+ "FROM learn";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int poor = rs.getInt("poor");
				int weak = rs.getInt("weak");
				int average = rs.getInt("average");
				int pretty = rs.getInt("pretty");
				int good = rs.getInt("good");
				result.add(poor);
				result.add(weak);
				result.add(average);
				result.add(pretty);
				result.add(good);
			}
			System.out.println("Your SQL Query:\n" + sql);
			dbConnector.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}