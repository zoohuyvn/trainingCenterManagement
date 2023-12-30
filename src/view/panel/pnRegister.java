package view.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bases.variables;
import dao.advisorDao;
import dao.authorizationDao;
import dao.studentDao;
import model.student;
import utils.regexCheck;
import utils.toast;
import view.authorizationView;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnRegister extends JPanel {
	// boxForm init
	JPanel boxForm;

	// titleRegister init
	JLabel titleRegister;

	// boxEmail init
	JPanel boxEmail;
	JLabel emailLabel;
	JTextField emailTF;

	// boxPasswordAndPasswordAgain init
	JPanel boxPasswordAndPasswordAgain;

	// boxPassword init
	JPanel boxPasword;
	JLabel passwordLabel;
	JPasswordField passwordTF;

	// boxPasswordAgain init
	JPanel boxPaswordAgain;
	JLabel passwordLabelAgain;
	JPasswordField passwordTFAgain;

	// boxIndentityAndName
	JPanel boxIndentityAndName;

	// boxIndentity init
	JPanel boxIndentity;
	JLabel indentityLabel;
	JTextField indentityTF;

	// boxName init
	JPanel boxName;
	JLabel nameLabel;
	JTextField fullnameTF;

	// boxGender init
	JPanel boxGender;
	JLabel genderLabel;
	JRadioButton maleRB, femaleRB;
	ButtonGroup groupGenderRadio;
	JPanel pnGenderRadio;

	// boxBirthdayAndPhone
	JPanel boxBirthdayAndPhone;

	// boxBirthday init
	JPanel boxBirthday;
	JLabel birthdayLabel;
	JTextField birthdayTF;

	// boxPhone init
	JPanel boxPhone;
	JLabel phoneLabel;
	JTextField phoneTF;

	// boxAddressAndJob
	JPanel boxAddressAndJob;

	// boxAddress init
	JPanel boxAddress;
	JLabel addressLabel;
	JTextField addressTF;

	// boxJob init
	JPanel boxJob;
	JLabel jobLabel;
	JTextField jobTF;

	// boxBtn init
	JPanel boxBtn;
	public JButton btnRegister = new JButton("REGISTER");
	JPanel yetHaveAccount;
	JLabel yetHaveAccountLabel;
	public JButton btnLoginNow = new JButton("Login now.");

	public pnRegister() {
		this.setLayout(new BorderLayout());

		// BoxForm
		boxForm = new JPanel(new GridLayout(10, 1));
		boxForm.setBorder(BorderFactory.createEmptyBorder(0, 60, -135, 60));
		boxForm.setBackground(Color.white);

		// titleRegister
		titleRegister = new JLabel("Register new account", SwingConstants.CENTER);
		titleRegister.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		titleRegister.setFont(new Font("Roboto", Font.PLAIN, 26));
		titleRegister.setForeground(Color.decode(variables.primaryColor));

		// boxEmail
		boxEmail = new JPanel(new GridLayout(2, 1));
		boxEmail.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));
		boxEmail.setBackground(Color.white);
		emailLabel = new JLabel("Email");
		emailTF = new JTextField();
		emailTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		emailTF.setMargin(new Insets(0, 8, 0, 8));
		boxEmail.add(emailLabel);
		boxEmail.add(emailTF);

		// boxPasswordAndPasswordAgain
		boxPasswordAndPasswordAgain = new JPanel(new GridLayout(1, 2, 14, 0));
		boxPasswordAndPasswordAgain.setBackground(Color.white);
		boxPasswordAndPasswordAgain.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));

		// boxPassword
		boxPasword = new JPanel(new GridLayout(2, 1));
		boxPasword.setBackground(Color.white);
		passwordLabel = new JLabel("Password");
		passwordTF = new JPasswordField();
		passwordTF.setMargin(new Insets(0, 8, 0, 8));
		boxPasword.add(passwordLabel);
		boxPasword.add(passwordTF);

		// boxPasswordAgain
		boxPaswordAgain = new JPanel(new GridLayout(2, 1));
		boxPaswordAgain.setBackground(Color.white);
		passwordLabelAgain = new JLabel("Retype password");
		passwordTFAgain = new JPasswordField();
		passwordTFAgain.setMargin(new Insets(0, 8, 0, 8));
		boxPaswordAgain.add(passwordLabelAgain);
		boxPaswordAgain.add(passwordTFAgain);
		
		boxPasswordAndPasswordAgain.add(boxPasword);
		boxPasswordAndPasswordAgain.add(boxPaswordAgain);

		// boxIndentityAndName
		boxIndentityAndName = new JPanel(new GridLayout(1, 2, 14, 0));
		boxIndentityAndName.setBackground(Color.white);
		boxIndentityAndName.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));

		// boxIndentity
		boxIndentity = new JPanel(new GridLayout(2, 1));
		boxIndentity.setBackground(Color.white);
		indentityLabel = new JLabel("Indentity id");
		indentityTF = new JTextField();
		indentityTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		indentityTF.setMargin(new Insets(0, 8, 0, 8));
		boxIndentity.add(indentityLabel);
		boxIndentity.add(indentityTF);

		// boxName
		boxName = new JPanel(new GridLayout(2, 1));
		boxName.setBackground(Color.white);
		nameLabel = new JLabel("Fullname");
		fullnameTF = new JTextField();
		fullnameTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		fullnameTF.setMargin(new Insets(0, 8, 0, 8));
		boxName.add(nameLabel);
		boxName.add(fullnameTF);
		
		boxIndentityAndName.add(boxIndentity);
		boxIndentityAndName.add(boxName);

		// boxGender
		boxGender = new JPanel(new GridLayout(2, 1));
		boxGender.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));
		boxGender.setBackground(Color.white);
		genderLabel = new JLabel("Gender");
		maleRB = new JRadioButton("Male");
		femaleRB = new JRadioButton("Female");
		maleRB.setFocusable(false);
		maleRB.setBackground(Color.white);
		femaleRB.setFocusable(false);
		femaleRB.setBackground(Color.white);
		groupGenderRadio = new ButtonGroup();
		groupGenderRadio.add(maleRB);
		groupGenderRadio.add(femaleRB);
		pnGenderRadio = new JPanel(new FlowLayout(ABORT));
		pnGenderRadio.setBackground(Color.white);
		pnGenderRadio.add(maleRB);
		pnGenderRadio.add(femaleRB);
		boxGender.add(genderLabel);
		boxGender.add(pnGenderRadio);

		// boxBirthdayAndPhone
		boxBirthdayAndPhone = new JPanel(new GridLayout(1, 2, 14, 0));
		boxBirthdayAndPhone.setBackground(Color.white);
		boxBirthdayAndPhone.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));

		// boxBirthday
		boxBirthday = new JPanel(new GridLayout(2, 1));
		boxBirthday.setBackground(Color.white);
		birthdayLabel = new JLabel("Birthday");
		birthdayTF = new JTextField();
		birthdayTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		birthdayTF.setMargin(new Insets(0, 8, 0, 8));
		boxBirthday.add(birthdayLabel);
		boxBirthday.add(birthdayTF);

		// boxPhone
		boxPhone = new JPanel(new GridLayout(2, 1));
		boxPhone.setBackground(Color.white);
		phoneLabel = new JLabel("Phone");
		phoneTF = new JTextField();
		phoneTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		phoneTF.setMargin(new Insets(0, 8, 0, 8));
		boxPhone.add(phoneLabel);
		boxPhone.add(phoneTF);
		
		boxBirthdayAndPhone.add(boxBirthday);
		boxBirthdayAndPhone.add(boxPhone);

		// boxAddressAndJob
		boxAddressAndJob = new JPanel(new GridLayout(1, 2, 14, 0));
		boxAddressAndJob.setBackground(Color.white);
		boxAddressAndJob.setBorder(BorderFactory.createEmptyBorder(-10, 0, 10, 0));

		// boxAddress
		boxAddress = new JPanel(new GridLayout(2, 1));
		boxAddress.setBackground(Color.white);
		addressLabel = new JLabel("Address");
		addressTF = new JTextField();
		addressTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		addressTF.setMargin(new Insets(0, 8, 0, 8));
		boxAddress.add(addressLabel);
		boxAddress.add(addressTF);

		// boxJob
		boxJob = new JPanel(new GridLayout(2, 1));
		boxJob.setBackground(Color.white);
		jobLabel = new JLabel("Job");
		jobTF = new JTextField();
		jobTF.setFont(new Font("Roboto", Font.PLAIN, 16));
		jobTF.setMargin(new Insets(0, 8, 0, 8));
		boxJob.add(jobLabel);
		boxJob.add(jobTF);
		
		boxAddressAndJob.add(boxAddress);
		boxAddressAndJob.add(boxJob);

		// boxBtn
		boxBtn = new JPanel(new GridLayout(2, 1, 0, 10));
		boxBtn.setBorder(BorderFactory.createEmptyBorder(10, 0, -20, 0));
		boxBtn.setBackground(Color.white);
		boxBtn.setPreferredSize(new Dimension(300, 30));
		btnRegister.setBackground(Color.white);
		btnRegister.setFocusable(false);
		btnRegister.setFont(new Font("Roboto", Font.BOLD, 16));
		btnRegister.setForeground(Color.white);
		btnRegister.setBackground(Color.decode(variables.primaryColor));
		btnRegister.setOpaque(true);
		btnRegister.setBorderPainted(false);
		yetHaveAccount = new JPanel();
		yetHaveAccount.setBorder(BorderFactory.createEmptyBorder(-8, 0, 0, 0));
		yetHaveAccount.setBackground(Color.white);
		yetHaveAccountLabel = new JLabel("Already have an account?");
		btnLoginNow.setBackground(Color.white);
		btnLoginNow.setFocusable(false);
		btnLoginNow.setBorderPainted(false);
		btnLoginNow.setForeground(Color.decode(variables.primaryColor));
		yetHaveAccount.add(yetHaveAccountLabel, BorderLayout.NORTH);
		yetHaveAccount.add(btnLoginNow, BorderLayout.CENTER);
		boxBtn.add(btnRegister);
		boxBtn.add(yetHaveAccount);

		// Add component
		boxForm.add(titleRegister);
		boxForm.add(boxEmail);
		boxForm.add(boxPasswordAndPasswordAgain);
		boxForm.add(boxIndentityAndName);
		boxForm.add(boxGender);
		boxForm.add(boxBirthdayAndPhone);
		boxForm.add(boxAddressAndJob);
		boxForm.add(boxBtn);
		this.add(boxForm, BorderLayout.CENTER);
	}
	
	public void createRegister() {
		student newStudent = null;
		int gender = 2;
		String passwordStr = new String(passwordTF.getPassword()).trim();
		String passwordStrAgain = new String(passwordTFAgain.getPassword()).trim();
		if (emailTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Email can't left blank!", "warning");
		} else if (passwordStr.equals("")) {
			toast.showMsg(null, "Alert", "Password can't left blank!", "warning");
		} else if (passwordStrAgain.equals("")) {
			toast.showMsg(null, "Alert", "Please retype password!", "warning");
		} else if (!passwordStr.equals(passwordStrAgain)) {
			toast.showMsg(null, "Alert", "Re-entered password is incorrect!", "warning");
		} else if (indentityTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Indentity id can't left blank!", "warning");
		} else if (fullnameTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Name can't left blank!", "warning");
		} else if (!maleRB.isSelected() && !femaleRB.isSelected()) {
			toast.showMsg(null, "Alert", "Gender can't left blank!", "warning");
		} else if (birthdayTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Birthday can't left blank!", "warning");
		} else if (phoneTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Phone can't left blank!", "warning");
		} else if (addressTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Address can't left blank!", "warning");
		} else if (jobTF.getText().trim().equals("")) {
			toast.showMsg(null, "Alert", "Job can't left blank!", "warning");
		} else if (regexCheck.validateRegisterField(emailTF.getText(), passwordStr,
				indentityTF.getText(), birthdayTF.getText(), phoneTF.getText())) {
		} else {
			String code = studentDao.getInstance().getNewStudentCode();
			String indentity = indentityTF.getText().trim();
			String name = fullnameTF.getText().trim();
			if (maleRB.isSelected()) {
				gender = 1;
			} else if (femaleRB.isSelected()) {
				gender = 0;
			}
			String birthday = birthdayTF.getText().trim();
			String phone = phoneTF.getText().trim();
			String email = emailTF.getText().trim();
			String address = addressTF.getText().trim();
			String job = jobTF.getText().trim();
			newStudent = new student(code, indentity, name, gender, birthday, phone, email, address, job, "Choose class", -1);
		}
		if (newStudent != null) {
			// Check if exist in database
			boolean studentExist = false;
			// Maybe don't need...
		    ArrayList<student> studentList = studentDao.getInstance().readAll();
			for (student student : studentList) {
				if (student.getCode().equals(newStudent.getCode())
					|| student.getIndentity().equals(newStudent.getIndentity())
					|| student.getPhone().equals(newStudent.getPhone())
					|| student.getEmail().equals(newStudent.getEmail())) {
					studentExist = true;
					toast.showMsg(null, "Alert", "Student already exist in database!", "error");
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
				int resultChange = studentDao.getInstance().create(newStudent, passwordStr);
				if (resultChange != 2) {
					toast.showMsg(null, "Alert", "Register failure!", "error");
				} else {
					toast.showMsg(null, "Noti", "Register successfully, you can login now!", "success");
					authorizationView.showPnLogin();
				}
			}
		}
	}
	
}