package utils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import controller.pnSlidebarListener;
import dao.advisorDao;
import dao.classxDao;
import dao.studentDao;
import model.advisor;
import model.classx;
import model.student;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class exportToExcel {
	public static String path = "";
	static JFileChooser chooser = new JFileChooser();
	static String firstFileName = "students";
	
	public static void export() {
		if (pnSlidebarListener.nodeStr.equals(" Students")
		|| pnSlidebarListener.nodeStr.equals(" Zoohuy Training Center")
		|| pnSlidebarListener.nodeStr.equals("")) {
			firstFileName = "students";
		} else if (pnSlidebarListener.nodeStr.equals(" Advisors")) {
			firstFileName = "advisors";
		} else if (pnSlidebarListener.nodeStr.equals(" Class")) {
			firstFileName = "class";
		}
		File targetFile = null;
	    if (targetFile != null) {
	    	chooser.setSelectedFile(targetFile);
	    } else {
	    	chooser.setSelectedFile(new File(firstFileName + "-export.xls"));
	    }
	    int option = chooser.showSaveDialog(null);
	    if (option == JFileChooser.APPROVE_OPTION) {
	    	targetFile = chooser.getSelectedFile();
	    }
	    if (targetFile != null && targetFile.getAbsolutePath().endsWith(".xls")) {
		    path = targetFile.toString();
	    } else if (option != JFileChooser.CANCEL_OPTION) {
	    	JOptionPane.showMessageDialog(chooser, "Only allow .xls file", "Message", JOptionPane.WARNING_MESSAGE);
	    }

		if (!path.equals("")) {
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				// Students
				if (pnSlidebarListener.nodeStr.equals(" Students")
				|| pnSlidebarListener.nodeStr.equals(" Zoohuy Training Center")
				|| pnSlidebarListener.nodeStr.equals("")) {
					// Sheet name
					HSSFSheet sheet = workbook.createSheet("Students");
					// Row head
					HSSFRow rowhead = sheet.createRow((short) 0);
					rowhead.createCell(0).setCellValue("Student code");
					rowhead.createCell(1).setCellValue("Indentity");
					rowhead.createCell(2).setCellValue("Name");
					rowhead.createCell(3).setCellValue("Gender");
					rowhead.createCell(4).setCellValue("Birthday");
					rowhead.createCell(5).setCellValue("Phone");
					rowhead.createCell(6).setCellValue("Email");
					rowhead.createCell(7).setCellValue("Address");
					rowhead.createCell(8).setCellValue("Job");
					rowhead.createCell(9).setCellValue("Class name");
					rowhead.createCell(10).setCellValue("Point");
					// Row data
					ArrayList<student> studentList = studentDao.getInstance().readAll();
					int count = studentList.size();
					for (int i = 0; i < count; i++) {
						HSSFRow rowData = sheet.createRow((short) i + 1);
						rowData.createCell(0).setCellValue(studentList.get(i).getCode());
						rowData.createCell(1).setCellValue(studentList.get(i).getIndentity());
						rowData.createCell(2).setCellValue(studentList.get(i).getName());
						rowData.createCell(3).setCellValue(studentList.get(i).getGender() == 1 ? "Nam" : "Nữ");
						rowData.createCell(4).setCellValue(studentList.get(i).getBirthday());
						rowData.createCell(5).setCellValue(studentList.get(i).getPhone());
						rowData.createCell(6).setCellValue(studentList.get(i).getEmail());
						rowData.createCell(7).setCellValue(studentList.get(i).getAddress());
						rowData.createCell(8).setCellValue(studentList.get(i).getJob());
						rowData.createCell(9).setCellValue(studentList.get(i).getClassName());
						if (studentList.get(i).getPoint() == -1) {
							rowData.createCell(10).setCellValue("Chưa thi");
						} else {
							rowData.createCell(10).setCellValue(studentList.get(i).getPoint());
						}
					}
				// Advisors
				} else if (pnSlidebarListener.nodeStr.equals(" Advisors")) {
					// Sheet name
					HSSFSheet sheet = workbook.createSheet("Advisors");
					// Row head
					HSSFRow rowhead = sheet.createRow((short) 0);
					rowhead.createCell(0).setCellValue("Advisor code");
					rowhead.createCell(1).setCellValue("Indentity");
					rowhead.createCell(2).setCellValue("Name");
					rowhead.createCell(3).setCellValue("Gender");
					rowhead.createCell(4).setCellValue("Birthday");
					rowhead.createCell(5).setCellValue("Phone");
					rowhead.createCell(6).setCellValue("Email");
					rowhead.createCell(7).setCellValue("Address");
					rowhead.createCell(8).setCellValue("Class name");
					// Row data
					ArrayList<advisor> advisorList = advisorDao.getInstance().readAll();
					int count = advisorList.size();
					for (int i = 0; i < count; i++) {
						HSSFRow rowData = sheet.createRow((short) i + 1);
						rowData.createCell(0).setCellValue(advisorList.get(i).getCode());
						rowData.createCell(1).setCellValue(advisorList.get(i).getIndentity());
						rowData.createCell(2).setCellValue(advisorList.get(i).getName());
						rowData.createCell(3).setCellValue(advisorList.get(i).getGender() == 1 ? "Nam" : "Nữ");
						rowData.createCell(4).setCellValue(advisorList.get(i).getBirthday());
						rowData.createCell(5).setCellValue(advisorList.get(i).getPhone());
						rowData.createCell(6).setCellValue(advisorList.get(i).getEmail());
						rowData.createCell(7).setCellValue(advisorList.get(i).getAddress());
						rowData.createCell(8).setCellValue(advisorList.get(i).getClassName());
					}
				// Class
				} else if (pnSlidebarListener.nodeStr.equals(" Class")) {
					// Sheet name
					HSSFSheet sheet = workbook.createSheet("Class");
					// Row head
					HSSFRow rowhead = sheet.createRow((short) 0);
					rowhead.createCell(0).setCellValue("Class code");
					rowhead.createCell(1).setCellValue("Name");
					rowhead.createCell(2).setCellValue("Start day");
					rowhead.createCell(3).setCellValue("Price");
					// Row data
					ArrayList<classx> classList = classxDao.getInstance().readAll();
					int count = classList.size();
					for (int i = 0; i < count; i++) {
						HSSFRow rowData = sheet.createRow((short) i + 1);
						rowData.createCell(0).setCellValue(classList.get(i).getCode());
						rowData.createCell(1).setCellValue(classList.get(i).getName());
						rowData.createCell(2).setCellValue(classList.get(i).getStartDay());
						rowData.createCell(3).setCellValue(classList.get(i).getPrice());
					}
				}
				FileOutputStream fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}