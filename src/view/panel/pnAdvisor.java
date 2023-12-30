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
import controller.pnAdvisorListener;
import controller.tableAdvisorListener;
import dao.advisorDao;
import dao.authorizationDao;
import dao.classxDao;
import dao.studentDao;
import model.advisor;
import model.classx;
import utils.regexCheck;
import utils.resizeTableColumnWidth;
import utils.toast;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnAdvisor extends JPanel {
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
	JTextField advisorCodeTextField, indentityTextField, fullnameTextField;
	JRadioButton maleRB, femaleRB;
	ButtonGroup groupGenderRadio;
	JTextField birthdayTextField, phoneTextField,
	emailTextField, addressTextField;
	JComboBox<String> classCbb;
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
	ArrayList<advisor> advisorList;
	
	public pnAdvisor() {
		this.setLayout(new BorderLayout());
		ActionListener acl = new pnAdvisorListener(this);
		
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
	    pnFormTitle = new JLabel("Advisor management");
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
		pnFormInputText.setBorder(BorderFactory.createEmptyBorder(15, 15, 70, 15));
		
		// pnFormInputSearch
		pnFormInputSearchTitle = new JLabel("Filter  ");
		pnFormInputSearchTitle.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/filter.png")));
		String filterFields[] = { "No filter", "Advisor code", "CCCD", "Fullname", "Gender",
								"Birthday", "Phone", "Email", "Address", "Class name" };
		pnFormInputSearchCbb = new JComboBox<String>(filterFields);
		pnFormInputSearchCbb.setFont(new Font("Roboto", Font.PLAIN, 17));
		pnFormInputSearchCbb.setFocusable(false);
		pnFormInputSearchTextField = new JTextField(15);
		pnFormInputSearchTextField.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn = new JButton(" Search");
		pnFormInputSearchBtn.setBackground(Color.white);
		pnFormInputSearchBtn.setFocusable(false);
		pnFormInputSearchBtn.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/search.png")));
		pnFormInputSearch.add(pnFormInputSearchTitle);
		pnFormInputSearch.add(pnFormInputSearchCbb);
		pnFormInputSearch.add(pnFormInputSearchTextField);
		pnFormInputSearch.add(pnFormInputSearchBtn);
		
		// pnFormInputText
		pnFITLeft = new JPanel(new BorderLayout());
		pnFITRight = new JPanel(new BorderLayout());
		pnFITLeftLabel = new JPanel(new GridLayout(5, 1, 0, 10));
		pnFITRightLabel = new JPanel(new GridLayout(5, 1, 0, 10));
		pnFITLeftTextField = new JPanel(new GridLayout(5, 1, 0, 10));
		pnFITRightTextField = new JPanel(new GridLayout(5, 1, 0, 10));
		pnFITLeftLabel.add(new JLabel("Advisor code    "));
		pnFITLeftLabel.add(new JLabel("Indentity id    "));
		pnFITLeftLabel.add(new JLabel("Fullname    "));
		pnFITLeftLabel.add(new JLabel("Gender    "));
		pnFITLeftLabel.add(new JLabel("Birthday    "));
		pnFITRightLabel.add(new JLabel("Phone    "));
		pnFITRightLabel.add(new JLabel("Email    "));
		pnFITRightLabel.add(new JLabel("Address    "));
		pnFITRightLabel.add(new JLabel("Class    "));
		advisorCodeTextField = new JTextField();
		indentityTextField = new JTextField();
		fullnameTextField = new JTextField();
		advisorCodeTextField.setMargin(new Insets(0, 8, 0, 8));
		indentityTextField.setMargin(new Insets(0, 8, 0, 8));
		fullnameTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITLeftTextField.add(advisorCodeTextField);
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
		birthdayTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITLeftTextField.add(birthdayTextField);
		phoneTextField = new JTextField();
		emailTextField = new JTextField();
		addressTextField = new JTextField();
		phoneTextField.setMargin(new Insets(0, 8, 0, 8));
		emailTextField.setMargin(new Insets(0, 8, 0, 8));
		addressTextField.setMargin(new Insets(0, 8, 0, 8));
		classFields = classxDao.getInstance().readAll();
		classCbb = new JComboBox<String>();
		classCbb.setFocusable(false);
		classCbb.addItem("Choose class");
		for (classx classItem : classFields) {
			classCbb.addItem(classItem.getName());
		}
		pnFITRightTextField.add(phoneTextField);
		pnFITRightTextField.add(emailTextField);
		pnFITRightTextField.add(addressTextField);
		pnFITRightTextField.add(classCbb);
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
		pnFBCreate.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/plus.png")));
		pnFBUpdate.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/update.png")));
		pnFBDelete.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/trash.png")));
		pnFBClear.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/earse.png")));
		pnFBReset.setIcon(new ImageIcon(pnAdvisor.class.getResource("/assets/icons/refresh.png")));
		pnFormBtn.setBackground(Color.white);
		pnFormBtn.add(pnFBCreate);
		pnFormBtn.add(pnFBUpdate);
		pnFormBtn.add(pnFBDelete);
		pnFormBtn.add(pnFBClear);
		pnFormBtn.add(pnFBReset);
		
		// pnTable
	    pnTableTitle = new JLabel("Advisor list");
		pnTableTitle.setForeground(Color.white);
		pnTableTitle.setFont(new Font("Roboto", Font.BOLD, 16));
	    pnTableTitle.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 0));
		dtm = new DefaultTableModel();
		String tableLabels[] = { "Advisor code", "Indentity id", "Fullname", "Gender",
								"Birthday", "Phone", "Email", "Address", "Class name" };
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
	    tableAdvisorListener tsl = new tableAdvisorListener(this, table);
	    table.addMouseListener(tsl);
	    
	    // Get data from database
	    advisorList = advisorDao.getInstance().readAll();
	    renderData(dtm, advisorList);
		
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
	
	public void renderData(DefaultTableModel dtm, ArrayList<advisor> list) {
		dtm.setRowCount(0);
		int count = list.size();
	    for (int i = 0; i < count; i++) {
	    	dtm.addRow(new String[] {
	    		((advisor) list.get(i)).getCode(),
	    		((advisor) list.get(i)).getIndentity(),
	    		((advisor) list.get(i)).getName(),
	    		((advisor) list.get(i)).getGender() == 1 ? "Nam" : "Nữ",
	    		((advisor) list.get(i)).getBirthday(),
	    		((advisor) list.get(i)).getPhone(),
	    		((advisor) list.get(i)).getEmail(),
	    		((advisor) list.get(i)).getAddress(),
	    		((advisor) list.get(i)).getClassName()
	    	});
	    }
	    resizeTableColumnWidth.rsz(table);
	}
	
	public void tableDataToForm(String code, String indentity, String name, int gender,
		String birthday, String phone, String email, String address, String className) {
		// Disable 2 JTextField can't be update
		advisorCodeTextField.setEnabled(false);
		indentityTextField.setEnabled(false);
		// Disable add button
		pnFBCreate.setEnabled(false);
		
		advisorCodeTextField.setText(code);
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
		if (className.equals("Chưa đăng ký")) {
			classCbb.setSelectedIndex(0);
		} else {
			classCbb.setSelectedItem(className);
		}
	}
	
	public void search() {
		int searchFilter = pnFormInputSearchCbb.getSelectedIndex();
		String searchData = pnFormInputSearchTextField.getText().trim();
		if (searchData.equals("")) {
			toast.showMsg(this, "Alert", "Search can't left blank!", "warning");
		} else {
			ArrayList<advisor> searchAdvisorList = advisorDao.getInstance().search(searchFilter, searchData);
			dtm.setRowCount(0);
			renderData(dtm, searchAdvisorList);
		}
	}
	
	public void add() {
		advisor newAdvisor = null;
		int gender = 2;
		// Validate form blank
		if (advisorCodeTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Advisor code can't left blank!", "warning");
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
		} else if (regexCheck.validateAdvisorField(advisorCodeTextField.getText(), indentityTextField.getText(),
				birthdayTextField.getText(), phoneTextField.getText(), emailTextField.getText())) {
		} else {
			String code = advisorCodeTextField.getText().trim();
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
			String className = classCbb.getSelectedItem().toString();
			newAdvisor = new advisor(code, indentity, name, gender, birthday, phone, email, address, className);
		}
		if (newAdvisor != null) {
			// Check if exist in database
			boolean advisorExist = false;
			// Maybe don't need...
		    advisorList = advisorDao.getInstance().readAll();
			for (advisor advisor : advisorList) {
				if (advisor.getCode().equals(newAdvisor.getCode())
					|| advisor.getIndentity().equals(newAdvisor.getIndentity())
					|| advisor.getPhone().equals(newAdvisor.getPhone())
					|| advisor.getEmail().equals(newAdvisor.getEmail())) {
					advisorExist = true;
					toast.showMsg(this, "Alert", "Advisor already exist in database!", "error");
					break;
				}
			}
			if (!advisorExist) {
				ArrayList<String> studentIdList = studentDao.getInstance().readAllIndentity();
				for (String indentity : studentIdList) {
					if (indentity.equals(newAdvisor.getIndentity())) {
						advisorExist = true;
						toast.showMsg(this, "Alert", "Advisor already exist in database!", "error");
						break;
					}
				}
			}
			if (!advisorExist) {
				ArrayList<String> phoneList = studentDao.getInstance().readAllPhone();
				for (String phone : phoneList) {
					if (phone.equals(newAdvisor.getPhone())) {
						advisorExist = true;
						toast.showMsg(this, "Alert", "Advisor already exist in database!", "error");
						break;
					}
				}
			}
			if (!advisorExist) {
				ArrayList<String> emailList = authorizationDao.getInstance().readAllEmail();
				for (String email : emailList) {
					if (email.equals(newAdvisor.getEmail())) {
						advisorExist = true;
						toast.showMsg(this, "Alert", "Advisor already exist in database!", "error");
						break;
					}
				}
			}
			// If not exist -> add to database and render new data
			if (!advisorExist) {
				int resultChange = advisorDao.getInstance().create(newAdvisor);
				if (resultChange != 2 && resultChange != 3) {
					toast.showMsg(this, "Alert", "Add advisor failure!", "error");
				} else {
					ArrayList<advisor> newAdvisorList = advisorDao.getInstance().readAll();
					renderData(dtm, newAdvisorList);
				}
			}
		}
	}
	
	public void update() {
		advisor editAdvisor = null;
		String code = advisorCodeTextField.getText();
		String indentity = indentityTextField.getText();
		String name = null;
		int gender = 2;
		String birthday = null, phone = null, email = null, address = null, className = null;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			// Validate form blank
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
			} else if (regexCheck.validateAdvisorField(advisorCodeTextField.getText(), indentityTextField.getText(),
					birthdayTextField.getText(), phoneTextField.getText(), emailTextField.getText())) {
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
				className = classCbb.getSelectedItem().toString();
				editAdvisor = new advisor(code, indentity, name, gender, birthday, phone, email, address, className);
			}
			if (editAdvisor != null) {
				// Check if not have a change
	        	boolean advisorNotChange = false;
	    	    advisorList = advisorDao.getInstance().readAll();
	        	for (advisor advisor : advisorList) {
	        		if (advisor.getName().equals(name) &&
	        			advisor.getGender() == gender &&
	        			advisor.getBirthday().equals(birthday) &&
	        			advisor.getPhone().equals(phone) &&
	        			advisor.getEmail().equals(email) &&
	        			advisor.getAddress().equals(address) &&
	        			(advisor.getClassName().equals(className) || (advisor.getClassName().equals("Chưa đăng ký") && className.equals("Choose class")))
	        		) {
	        			advisorNotChange = true;
	        			toast.showMsg(this, "Alert", "There are no change with advisor", "warning");
	        			break;
	        		}
	        	}
	        	// If have a change -> update in database and render new data
	        	if (!advisorNotChange) {
	        		int resultChange = advisorDao.getInstance().update(editAdvisor);
	        		if (resultChange != 2 && resultChange != 3 & resultChange != 1) {
	        			toast.showMsg(this, "Alert", "Update advisor failure!", "error");
	        		} else {
	        			ArrayList<advisor> newAdvisorList = advisorDao.getInstance().readAll();
	        			renderData(dtm, newAdvisorList);
	        		}
	        	}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select advisor to update!", "warning");
		}
	}
	
	public void delete() {
		advisor deleteAdvisor = null;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String code = table.getValueAt(selectedRow, 0).toString();
			String email = table.getValueAt(selectedRow, 6).toString();
			deleteAdvisor = new advisor(code, "", "", 2, "", "", email, "", "");
			if (deleteAdvisor != null) {
				boolean isConfirm = toast.showCfm(this, "Alert", "Are you sure to delete this advisor?");
				if (isConfirm) {
					int resultChange = advisorDao.getInstance().delete(deleteAdvisor);
					if (resultChange != 2) {
						toast.showMsg(this, "Alert", "Delete advisor failure!", "error");
					} else {
						ArrayList<advisor> newAdvisorList = advisorDao.getInstance().readAll();
						renderData(dtm, newAdvisorList);
					}
				}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select advisor to delete!", "warning");
		}
	}
	
	public void clearForm() {
		// Enable back for JTextField can't be update
		advisorCodeTextField.setEnabled(true);
		indentityTextField.setEnabled(true);
		// Enable add button
		pnFBCreate.setEnabled(true);
		// Search
		pnFormInputSearchCbb.setSelectedIndex(0);
		pnFormInputSearchTextField.setText("");
		// Form
		advisorCodeTextField.setText("");
		indentityTextField.setText("");
		fullnameTextField.setText("");
		groupGenderRadio.clearSelection();
		birthdayTextField.setText("");
		phoneTextField.setText("");
		emailTextField.setText("");
		addressTextField.setText("");
		classCbb.setSelectedIndex(0);
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
		// Get new advisor list
		advisorList = advisorDao.getInstance().readAll();
		renderData(dtm, advisorList);
	}
	
}