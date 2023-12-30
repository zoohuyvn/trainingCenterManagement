package view.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import bases.variables;
import controller.pnStudentListener;
import controller.tableStudentListener;
import dao.advisorDao;
import dao.authorizationDao;
import dao.classxDao;
import dao.studentDao;
import model.classx;
import model.student;
import utils.regexCheck;
import utils.resizeTableColumnWidth;
import utils.toast;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnStudent extends JPanel {
	// Init splitPane
	JSplitPane splitPane;
	Dimension minimumSize;
	
	// Init pnForm
	JLabel pnFormTitle;
	JPanel pnForm;
	JSplitPane formSplitPane;
	Dimension minimumSize2;
	JPanel pnFormInput, pnFormBtn;
	
	// Init pnFormInput
	JPanel pnFormInputSearch, pnFormInputText;
	
	// Init pnFormInputSearch
	JLabel pnFormInputSearchTitle;
	JComboBox<String> pnFormInputSearchCbb;
	JTextField pnFormInputSearchTextField;
	JButton pnFormInputSearchBtn;
	
	// Init pnFormInputText
	JPanel pnFITLeft, pnFITRight,
	pnFITLeftLabel, pnFITRightLabel,
	pnFITLeftTextField, pnFITRightTextField;
	ArrayList<classx> classFields;
	JTextField studentCodeTextField, indentityTextField, fullnameTextField;
	JRadioButton maleRB, femaleRB;
	ButtonGroup groupGenderRadio;
	JTextField birthdayTextField, phoneTextField,
	emailTextField, addressTextField, jobTextField;
	JComboBox<String> classCbb;
	JTextField pointTextField;
	JPanel pnGenderRadio;
	
	// Init pnFormBtn
	JButton pnFBCreate, pnFBUpdate, pnFBDelete,
	pnFBClear, pnFBReset;
	
	// Init pnTable
	JLabel pnTableTitle;
	JPanel pnTable;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrp;
	ArrayList<student> studentList;
	
	public pnStudent() {
		this.setLayout(new BorderLayout());
		ActionListener acl = new pnStudentListener(this);
		
		// splitPane
		pnForm = new JPanel(new BorderLayout());
		pnForm.setBackground(Color.decode(variables.primaryColor));
		pnTable = new JPanel(new BorderLayout());
		pnTable.setBackground(Color.decode(variables.primaryColor));
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnForm, pnTable);
		splitPane.setDividerLocation(380);
		splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		splitPane.setEnabled(false);
		minimumSize = new Dimension(380, 0);
		pnForm.setMinimumSize(minimumSize);
		pnTable.setMinimumSize(minimumSize);
		
		// pnForm
	    pnFormTitle = new JLabel("Student management");
		pnFormTitle.setForeground(Color.white);
		pnFormTitle.setFont(new Font("Roboto", Font.BOLD, 16));
	    pnFormTitle.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 0));
		pnFormInput = new JPanel(new BorderLayout());
		pnFormBtn = new JPanel(new GridLayout(5, 1, 0, 22));
		pnFormBtn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		formSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnFormInput, pnFormBtn);
		formSplitPane.setDividerLocation(900);
		formSplitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		formSplitPane.setEnabled(false);
		minimumSize2 = new Dimension(900, 0);
		pnForm.setMinimumSize(minimumSize2);
		pnTable.setMinimumSize(minimumSize2);
		
		// pnFormInput
		pnFormInputSearch = new JPanel(new FlowLayout());
		pnFormInputSearch.setBackground(Color.white);
		pnFormInputSearch.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		pnFormInputText = new JPanel(new GridLayout(1, 2, 20, 0));
		pnFormInputText.setBackground(Color.white);
		pnFormInputText.setBorder(BorderFactory.createEmptyBorder(15, 15, 30, 15));
		
		// pnFormInputSearch
		pnFormInputSearchTitle = new JLabel("Filter  ");
		pnFormInputSearchTitle.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/filter.png")));
		String filterFields[] = { "No filter", "Student code", "CCCD", "Fullname", "Gender",
								"Birthday", "Phone", "Email", "Address", "Job", "Class name", "Point" };
		pnFormInputSearchCbb = new JComboBox<String>(filterFields);
		pnFormInputSearchCbb.setFont(new Font("Roboto", Font.PLAIN, 17));
		pnFormInputSearchCbb.setFocusable(false);
		pnFormInputSearchTextField = new JTextField(15);
		pnFormInputSearchTextField.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn = new JButton(" Search");
		pnFormInputSearchBtn.setBackground(Color.white);
		pnFormInputSearchBtn.setFocusable(false);
		pnFormInputSearchBtn.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/search.png")));
		pnFormInputSearch.add(pnFormInputSearchTitle);
		pnFormInputSearch.add(pnFormInputSearchCbb);
		pnFormInputSearch.add(pnFormInputSearchTextField);
		pnFormInputSearch.add(pnFormInputSearchBtn);
		
		// pnFormInputText
		pnFITLeft = new JPanel(new BorderLayout());
		pnFITRight = new JPanel(new BorderLayout());
		pnFITLeftLabel = new JPanel(new GridLayout(6, 1, 0, 10));
		pnFITRightLabel = new JPanel(new GridLayout(6, 1, 0, 10));
		pnFITLeftTextField = new JPanel(new GridLayout(6, 1, 0, 10));
		pnFITRightTextField = new JPanel(new GridLayout(6, 1, 0, 10));
		pnFITLeftLabel.add(new JLabel("Student code    "));
		pnFITLeftLabel.add(new JLabel("Indentity id    "));
		pnFITLeftLabel.add(new JLabel("Fullname    "));
		pnFITLeftLabel.add(new JLabel("Gender    "));
		pnFITLeftLabel.add(new JLabel("Birthday    "));
		pnFITLeftLabel.add(new JLabel("Phone    "));
		pnFITRightLabel.add(new JLabel("Email    "));
		pnFITRightLabel.add(new JLabel("Address    "));
		pnFITRightLabel.add(new JLabel("Job    "));
		pnFITRightLabel.add(new JLabel("Class    "));
		pnFITRightLabel.add(new JLabel("Point    "));
		studentCodeTextField = new JTextField();
		indentityTextField = new JTextField();
		fullnameTextField = new JTextField();
		studentCodeTextField.setMargin(new Insets(0, 8, 0, 8));
		indentityTextField.setMargin(new Insets(0, 8, 0, 8));
		fullnameTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITLeftTextField.add(studentCodeTextField);
		pnFITLeftTextField.add(indentityTextField);
		pnFITLeftTextField.add(fullnameTextField);
		maleRB = new JRadioButton("Male");
		femaleRB = new JRadioButton("Female");
		maleRB.setFocusable(false);
		femaleRB.setFocusable(false);
		groupGenderRadio = new ButtonGroup();
		groupGenderRadio.add(maleRB);
		groupGenderRadio.add(femaleRB);
		pnGenderRadio = new JPanel(new FlowLayout(ABORT));
		pnGenderRadio.add(maleRB);
		pnGenderRadio.add(femaleRB);
		pnFITLeftTextField.add(pnGenderRadio);
		birthdayTextField = new JTextField();
		phoneTextField = new JTextField();
		birthdayTextField.setMargin(new Insets(0, 8, 0, 8));
		phoneTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITLeftTextField.add(birthdayTextField);
		pnFITLeftTextField.add(phoneTextField);
		emailTextField = new JTextField();
		addressTextField = new JTextField();
		jobTextField = new JTextField();
		emailTextField.setMargin(new Insets(0, 8, 0, 8));
		addressTextField.setMargin(new Insets(0, 8, 0, 8));
		jobTextField.setMargin(new Insets(0, 8, 0, 8));
		classFields = classxDao.getInstance().readAll();
		classCbb = new JComboBox<String>();
		classCbb.setFocusable(false);
		classCbb.addItem("Choose class");
		for (classx classItem : classFields) {
			classCbb.addItem(classItem.getName());
		}
		pointTextField = new JTextField();
		pointTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITRightTextField.add(emailTextField);
		pnFITRightTextField.add(addressTextField);
		pnFITRightTextField.add(jobTextField);
		pnFITRightTextField.add(classCbb);
		pnFITRightTextField.add(pointTextField);
		pnFITLeft.add(pnFITLeftLabel, BorderLayout.WEST);
		pnFITLeft.add(pnFITLeftTextField, BorderLayout.CENTER);
		pnFITRight.add(pnFITRightLabel, BorderLayout.WEST);
		pnFITRight.add(pnFITRightTextField, BorderLayout.CENTER);
		pnFormInputText.add(pnFITLeft);
		pnFormInputText.add(pnFITRight);
		pnFITLeftLabel.setBackground(Color.white);
		pnFITLeftTextField.setBackground(Color.white);
		pnGenderRadio.setBackground(Color.white);
		maleRB.setBackground(Color.white);
		femaleRB.setBackground(Color.white);
		pnFITRightLabel.setBackground(Color.white);
		pnFITRightTextField.setBackground(Color.white);
		
		// pnFormInput
		pnFormInput.add(pnFormInputSearch, BorderLayout.NORTH);
		pnFormInput.add(pnFormInputText, BorderLayout.CENTER);
		
		// pnFormBtn
		pnFBCreate = new JButton(" Add");
		pnFBUpdate = new JButton(" Update");
		pnFBDelete = new JButton(" Delete");
		pnFBClear = new JButton(" Clear form");
		pnFBReset = new JButton(" Reset data");
		pnFBCreate.setBackground(Color.white);
		pnFBUpdate.setBackground(Color.white);
		pnFBDelete.setBackground(Color.white);
		pnFBClear.setBackground(Color.white);
		pnFBReset.setBackground(Color.white);
		pnFBCreate.setFocusable(false);
		pnFBUpdate.setFocusable(false);
		pnFBDelete.setFocusable(false);
		pnFBClear.setFocusable(false);
		pnFBReset.setFocusable(false);
		pnFBCreate.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/plus.png")));
		pnFBUpdate.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/update.png")));
		pnFBDelete.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/trash.png")));
		pnFBClear.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/earse.png")));
		pnFBReset.setIcon(new ImageIcon(pnStudent.class.getResource("/assets/icons/refresh.png")));
		pnFormBtn.setBackground(Color.white);
		pnFormBtn.add(pnFBCreate);
		pnFormBtn.add(pnFBUpdate);
		pnFormBtn.add(pnFBDelete);
		pnFormBtn.add(pnFBClear);
		pnFormBtn.add(pnFBReset);
		
		// pnTable
	    pnTableTitle = new JLabel("Student list");
		pnTableTitle.setForeground(Color.white);
		pnTableTitle.setFont(new Font("Roboto", Font.BOLD, 16));
	    pnTableTitle.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 0));
		dtm = new DefaultTableModel();
		String tableLabels[] = { "Student code", "Indentity id", "Fullname", "Gender",
								"Birthday", "Phone", "Email", "Address", "Job", "Class name", "Point" };
		for (String tableLabelText : tableLabels) {
			dtm.addColumn(tableLabelText);
        }
	    table = new JTable(dtm);
	    table.setRowHeight(24);
	    table.setGridColor(Color.decode(variables.lightGray));
	    table.getTableHeader().setOpaque(false);
	    table.getTableHeader().setBackground(Color.decode(variables.primaryColorLight));
	    table.setRowHeight(29);
	    scrp = new JScrollPane(table);
	    tableStudentListener tsl = new tableStudentListener(this, table);
	    table.addMouseListener(tsl);
	    
	    // Get data from database
	    studentList = studentDao.getInstance().readAll();
	    renderData(dtm, studentList);
		
		// Add pnStudentListener
		pnFormInputSearchBtn.addActionListener(acl);
		pnFBCreate.addActionListener(acl);
		pnFBUpdate.addActionListener(acl);
		pnFBDelete.addActionListener(acl);
		pnFBClear.addActionListener(acl);
		pnFBReset.addActionListener(acl);
		
		// Add component
	    pnForm.add(pnFormTitle, BorderLayout.NORTH);
		pnForm.add(formSplitPane, BorderLayout.CENTER);
		pnTable.add(pnTableTitle, BorderLayout.NORTH);
		pnTable.add(scrp, BorderLayout.CENTER);
		this.add(splitPane);
	}
	
	public void renderData(DefaultTableModel dtm, ArrayList<student> list) {
		dtm.setRowCount(0);
		int count = list.size();
	    for (int i = 0; i < count; i++) {
	    	double point = ((student) list.get(i)).getPoint();
	    	String className = ((student) list.get(i)).getClassName();
	    	if (className.equals("Chưa đăng ký")) {
	    		point = -1;
	    	}
	    	dtm.addRow(new String[] {
	    		((student) list.get(i)).getCode(),
	    		((student) list.get(i)).getIndentity(),
	    		((student) list.get(i)).getName(),
	    		((student) list.get(i)).getGender() == 1 ? "Nam" : "Nữ",
	    		((student) list.get(i)).getBirthday(),
	    		((student) list.get(i)).getPhone(),
	    		((student) list.get(i)).getEmail(),
	    		((student) list.get(i)).getAddress(),
	    		((student) list.get(i)).getJob(),
	    		className,
	    		point != -1 ? point+"" : "Chưa thi"
	    	});
	    }
	    resizeTableColumnWidth.rsz(table);
	}
	
	public void tableDataToForm(String code, String indentity, String name, int gender, String birthday,
								String phone, String email, String address, String job, String className, String point) {
		// Disable 2 JTextField can't be update
		studentCodeTextField.setEnabled(false);
		indentityTextField.setEnabled(false);
		// Disable add button
		pnFBCreate.setEnabled(false);
		
		studentCodeTextField.setText(code);
		indentityTextField.setText(indentity);
		fullnameTextField.setText(name);
		if (gender == 1) {
			maleRB.setSelected(true);
		} else {
			femaleRB.setSelected(true);
		}
		birthdayTextField.setText(birthday);
		phoneTextField.setText(phone);
		emailTextField.setText(email);
		addressTextField.setText(address);
		jobTextField.setText(job);
		if (className.equals("Chưa đăng ký")) {
			classCbb.setSelectedIndex(0);
		} else {
			classCbb.setSelectedItem(className);
		}
		if (point.equals("Chưa thi")) {
			pointTextField.setText("");
		} else {
			pointTextField.setText(point);
		}
	}
	
	public void search() {
		int searchFilter = pnFormInputSearchCbb.getSelectedIndex();
		String searchData = pnFormInputSearchTextField.getText().trim();
		if (searchData.equals("")) {
			toast.showMsg(this, "Alert", "Search can't left blank!", "warning");
		} else {
			ArrayList<student> searchStudentList = studentDao.getInstance().search(searchFilter, searchData);
			dtm.setRowCount(0);
			renderData(dtm, searchStudentList);
		}
	}
	
	public void add() {
		student newStudent = null;
		int gender = 2;
		double point = -1;
		boolean pointNAN = false;
		// Validate form blank
		if (!pointTextField.getText().trim().equals("")) {
			try {
				point = Double.parseDouble(pointTextField.getText());
			} catch (NumberFormatException e) {
				pointNAN = true;
			}
		}
		if (studentCodeTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Student code can't left blank!", "warning");
		} else if (indentityTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Indentity id can't left blank!", "warning");
		} else if (fullnameTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Name can't left blank!", "warning");
		} else if (!maleRB.isSelected() && !femaleRB.isSelected()) {
			toast.showMsg(this, "Alert", "Gender can't left blank!", "warning");
		} else if (birthdayTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Birthday can't left blank!", "warning");
		} else if (phoneTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Phone can't left blank!", "warning");
		} else if (emailTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Email can't left blank!", "warning");
		} else if (addressTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Address can't left blank!", "warning");
		} else if (jobTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Job can't left blank!", "warning");
		} else if (pointNAN) {
			toast.showMsg(this, "Error", "Point can't be letters or special characters!", "error");
		} else if (!pointTextField.getText().trim().equals("") && (point < 0 || point > 10)) {
			toast.showMsg(this, "Alert", "Point value allow from 0 to 10!", "warning");
		} else if (regexCheck.validateStudentField(studentCodeTextField.getText(), indentityTextField.getText(), birthdayTextField.getText(),
				phoneTextField.getText(), emailTextField.getText())) {
		} else {
			String code = studentCodeTextField.getText().trim();
			String indentity = indentityTextField.getText().trim();
			String name = fullnameTextField.getText().trim();
			if (maleRB.isSelected()) {
				gender = 1;
			} else if (femaleRB.isSelected()) {
				gender = 0;
			}
			String birthday = birthdayTextField.getText().trim();
			String phone = phoneTextField.getText().trim();
			String email = emailTextField.getText().trim();
			String address = addressTextField.getText().trim();
			String job = jobTextField.getText().trim();
			String className = classCbb.getSelectedItem().toString();
			newStudent = new student(code, indentity, name, gender, birthday, phone, email, address, job, className, point);
		}
		if (newStudent != null) {
			// Check if exist in database
			boolean studentExist = false;
			// Maybe don't need...
		    studentList = studentDao.getInstance().readAll();
			for (student student : studentList) {
				if (student.getCode().equals(newStudent.getCode())
					|| student.getIndentity().equals(newStudent.getIndentity())
					|| student.getPhone().equals(newStudent.getPhone())
					|| student.getEmail().equals(newStudent.getEmail())) {
					studentExist = true;
					toast.showMsg(this, "Alert", "Student already exist in database!", "error");
					break;
				}
			}
			if (!studentExist) {
				ArrayList<String> advisorIdList = advisorDao.getInstance().readAllIndentity();
				for (String indentity : advisorIdList) {
					if (indentity.equals(newStudent.getIndentity())) {
						studentExist = true;
						toast.showMsg(this, "Alert", "Student already exist in database!", "error");
						break;
					}
				}
			}
			if (!studentExist) {
				ArrayList<String> phoneList = advisorDao.getInstance().readAllPhone();
				for (String phone : phoneList) {
					if (phone.equals(newStudent.getPhone())) {
						studentExist = true;
						toast.showMsg(this, "Alert", "Student already exist in database!", "error");
						break;
					}
				}
			}
			if (!studentExist) {
				ArrayList<String> emailList = authorizationDao.getInstance().readAllEmail();
				for (String email : emailList) {
					if (email.equals(newStudent.getEmail())) {
						studentExist = true;
						toast.showMsg(this, "Alert", "Student already exist in database!", "error");
						break;
					}
				}
			}
			// If not exist -> add to database and render new data
			if (!studentExist) {
				int resultChange = studentDao.getInstance().create(newStudent, "123456");
				if (resultChange != 2 && resultChange != 3) {
					toast.showMsg(this, "Alert", "Add student failure!", "error");
				} else {
					ArrayList<student> newStudentList = studentDao.getInstance().readAll();
					renderData(dtm, newStudentList);
				}
			}
		}
	}
	
	public void update() {
		student editStudent = null;
		String code = studentCodeTextField.getText();
		String indentity = indentityTextField.getText();
		String name = null;
		int gender = 2;
		String birthday = null, phone = null, email = null, address = null, job = null, className = null;
		double point = -1;
		boolean pointNAN = false;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			// Validate form blank
			if (!pointTextField.getText().trim().equals("")) {
				try {
					point = Double.parseDouble(pointTextField.getText());
				} catch (NumberFormatException e) {
					pointNAN = true;
				}
			}
			if (fullnameTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Name can't left blank!", "warning");
			} else if (!maleRB.isSelected() && !femaleRB.isSelected()) {
				toast.showMsg(this, "Alert", "Gender can't left blank!", "warning");
			} else if (birthdayTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Birthday can't left blank!", "warning");
			} else if (phoneTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Phone can't left blank!", "warning");
			} else if (emailTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Email can't left blank!", "warning");
			} else if (addressTextField.getText().equals("")) {
				toast.showMsg(this, "Alert", "Address can't left blank!", "warning");
			} else if (jobTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Job can't left blank!", "warning");
			} else if (pointNAN) {
				toast.showMsg(this, "Error", "Point can't be letters or special characters!", "error");
			} else if (!pointTextField.getText().trim().equals("") && (point < 0 || point > 10)) {
				toast.showMsg(this, "Alert", "Point value allow from 0 to 10!", "warning");
			} else if (regexCheck.validateStudentField(studentCodeTextField.getText(), indentityTextField.getText(), birthdayTextField.getText(),
					phoneTextField.getText(), emailTextField.getText())) {
			} else {
				name = fullnameTextField.getText();
				if (maleRB.isSelected()) {
					gender = 1;
				} else if (femaleRB.isSelected()) {
					gender = 0;
				}
				birthday = birthdayTextField.getText().trim();
				phone = phoneTextField.getText().trim();
				email = emailTextField.getText().trim();
				address = addressTextField.getText().trim();
				job = jobTextField.getText().trim();
				className = classCbb.getSelectedItem().toString();
				editStudent = new student(code, indentity, name, gender, birthday, phone, email, address, job, className, point);
			}
			if (editStudent != null) {
				// Check if not have a change
	        	boolean studentNotChange = false;
	    	    studentList = studentDao.getInstance().readAll();
	        	for (student student : studentList) {
	        		if (student.getName().equals(name) &&
	        			student.getGender() == gender &&
	        			student.getBirthday().equals(birthday) &&
	        			student.getPhone().equals(phone) &&
	        			student.getEmail().equals(email) &&
	        			student.getAddress().equals(address) &&
	        			student.getJob().equals(job) &&
	        			(student.getClassName().equals(className) || (student.getClassName().equals("Chưa đăng ký") && className.equals("Choose class"))) &&
	        			student.getPoint() == point
	        		) {
	        			studentNotChange = true;
	        			toast.showMsg(this, "Alert", "There are no change with student", "warning");
	        			break;
	        		}
	        	}
	        	// If have a change -> update in database and render new data
	        	if (!studentNotChange) {
	        		int resultChange = studentDao.getInstance().update(editStudent);
	        		if (resultChange != 2 && resultChange != 3 & resultChange != 1) {
	        			toast.showMsg(this, "Alert", "Update student failure!", "error");
	        		} else {
	        			ArrayList<student> newStudentList = studentDao.getInstance().readAll();
	        			renderData(dtm, newStudentList);
	        		}
	        	}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select student to update!", "warning");
		}
	}
	
	public void delete() {
		student deleteStudent = null;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String code = table.getValueAt(selectedRow, 0).toString();
			String email = table.getValueAt(selectedRow, 6).toString();
			deleteStudent = new student(code, "", "", 2, "", "", email, "", "", "", -1);
			if (deleteStudent != null) {
				boolean isConfirm = toast.showCfm(this, "Alert", "Are you sure to delete this student?");
				if (isConfirm) {
					int resultChange = studentDao.getInstance().delete(deleteStudent);
					if (resultChange != 2) {
						toast.showMsg(this, "Alert", "Delete student failure!", "error");
					} else {
						ArrayList<student> newStudentList = studentDao.getInstance().readAll();
						renderData(dtm, newStudentList);
					}
				}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select student to delete!", "warning");
		}
	}
	
	public void clearForm() {
		// Enable back for JTextField can't be update
		studentCodeTextField.setEnabled(true);
		indentityTextField.setEnabled(true);
		// Enable add button
		pnFBCreate.setEnabled(true);
		// Search
		pnFormInputSearchCbb.setSelectedIndex(0);
		pnFormInputSearchTextField.setText("");
		// Form
		studentCodeTextField.setText("");
		indentityTextField.setText("");
		fullnameTextField.setText("");
		groupGenderRadio.clearSelection();
		birthdayTextField.setText("");
		phoneTextField.setText("");
		emailTextField.setText("");
		addressTextField.setText("");
		jobTextField.setText("");
		classCbb.setSelectedIndex(0);
		pointTextField.setText("");
	}
	
	public void resetData() {
		clearForm();
		dtm.setRowCount(0);
		// Render new class field
		classFields = classxDao.getInstance().readAll();
		classCbb.removeAllItems();
		classCbb.addItem("Choose class");
		for (classx classItem : classFields) {
			classCbb.addItem(classItem.getName());
		}
		// Get new student list
		studentList = studentDao.getInstance().readAll();
		renderData(dtm, studentList);
	}
	
}