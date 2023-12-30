package view.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import bases.variables;
import controller.pnClassKeyboardListener;
import controller.pnClassListener;
import controller.tableClassListener;
import dao.classxDao;
import model.classx;
import utils.convertPrice;
import utils.regexCheck;
import utils.resizeTableColumnWidth;
import utils.toast;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnClass extends JPanel {
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
	JTextField classCodeTextField, nameTextField,
	startDayTextField;
	public JTextField priceTextField;

	// Init pnFormBtn
	JButton pnFBCreate, pnFBUpdate, pnFBDelete,
	pnFBClear, pnFBReset;

	// Init pnTable
	JLabel pnTableTitle;
	JPanel pnTable;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrp;
	ArrayList<classx> classList;

	public pnClass() {
		this.setLayout(new BorderLayout());
		ActionListener acl = new pnClassListener(this);
		pnClassKeyboardListener aclKey = new pnClassKeyboardListener(this);

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
		pnFormTitle = new JLabel("Class management");
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
		pnFormInputText.setBorder(BorderFactory.createEmptyBorder(15, 15, 205, 15));

		// pnFormInputSearch
		pnFormInputSearchTitle = new JLabel("Filter  ");
		pnFormInputSearchTitle.setIcon(new ImageIcon(pnClass.class.getResource("/assets/icons/filter.png")));
		String filterFields[] = { "No filter", "Class code", "Name", "Start day", "Price" };
		pnFormInputSearchCbb = new JComboBox<String>(filterFields);
		pnFormInputSearchCbb.setFont(new Font("Roboto", Font.PLAIN, 17));
		pnFormInputSearchCbb.setFocusable(false);
		pnFormInputSearchTextField = new JTextField(15);
		pnFormInputSearchTextField.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn = new JButton(" Search");
		pnFormInputSearchBtn.setBackground(Color.white);
		pnFormInputSearchBtn.setFocusable(false);
		pnFormInputSearchBtn.setMargin(new Insets(4, 8, 4, 8));
		pnFormInputSearchBtn.setIcon(new ImageIcon(pnClass.class.getResource("/assets/icons/search.png")));
		pnFormInputSearch.add(pnFormInputSearchTitle);
		pnFormInputSearch.add(pnFormInputSearchCbb);
		pnFormInputSearch.add(pnFormInputSearchTextField);
		pnFormInputSearch.add(pnFormInputSearchBtn);

		// pnFormInputText
		pnFITLeft = new JPanel(new BorderLayout());
		pnFITRight = new JPanel(new BorderLayout());
		pnFITLeftLabel = new JPanel(new GridLayout(2, 1, 0, 10));
		pnFITRightLabel = new JPanel(new GridLayout(2, 1, 0, 10));
		pnFITLeftTextField = new JPanel(new GridLayout(2, 1, 0, 10));
		pnFITRightTextField = new JPanel(new GridLayout(2, 1, 0, 10));
		pnFITLeftLabel.add(new JLabel("Class code    "));
		pnFITLeftLabel.add(new JLabel("Name    "));
		classCodeTextField = new JTextField();
		nameTextField = new JTextField();
		classCodeTextField.setMargin(new Insets(0, 8, 0, 8));
		nameTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITLeftTextField.add(classCodeTextField);
		pnFITLeftTextField.add(nameTextField);
		pnFITRightLabel.add(new JLabel("Start day    "));
		pnFITRightLabel.add(new JLabel("Price    "));
		startDayTextField = new JTextField();
		priceTextField = new JTextField();
		startDayTextField.setMargin(new Insets(0, 8, 0, 8));
		priceTextField.setMargin(new Insets(0, 8, 0, 8));
		pnFITRightTextField.add(startDayTextField);
		pnFITRightTextField.add(priceTextField);
		pnFITLeft.add(pnFITLeftLabel, BorderLayout.WEST);
		pnFITLeft.add(pnFITLeftTextField, BorderLayout.CENTER);
		pnFITRight.add(pnFITRightLabel, BorderLayout.WEST);
		pnFITRight.add(pnFITRightTextField, BorderLayout.CENTER);
		pnFormInputText.add(pnFITLeft);
		pnFormInputText.add(pnFITRight);
		pnFITLeftLabel.setBackground(Color.white);
		pnFITLeftTextField.setBackground(Color.white);
		pnFITRightLabel.setBackground(Color.white);
		pnFITRightTextField.setBackground(Color.white);
		
		// Keyboard listener
		priceTextField.addKeyListener(aclKey);

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
		pnTableTitle = new JLabel("Class list");
		pnTableTitle.setForeground(Color.white);
		pnTableTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		pnTableTitle.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 0));
		dtm = new DefaultTableModel();
		String tableLabels[] = { "Class code", "Name", "Start day", "Price" };
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
		tableClassListener tsl = new tableClassListener(this, table);
		table.addMouseListener(tsl);
		// Get data from database
		classList = classxDao.getInstance().readAll();
		renderData(dtm, classList);

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

	public void renderData(DefaultTableModel dtm, ArrayList<classx> list) {
		dtm.setRowCount(0);
		int count = list.size();
		for (int i = 0; i < count; i++) {
			String priceCurrency = convertPrice.toCurrency(((classx) list.get(i)).getPrice());
			dtm.addRow(new String[] {
				((classx) list.get(i)).getCode(),
				((classx) list.get(i)).getName(),
				((classx) list.get(i)).getStartDay(),
				priceCurrency
			});
		}
		resizeTableColumnWidth.rsz(table);
	}

	public void tableDataToForm(String code, String name, String startDay, String price) {
		// Disable 2 JTextField can't be update
		classCodeTextField.setEnabled(false);
		// Disable add button
		pnFBCreate.setEnabled(false);

		classCodeTextField.setText(code);
		nameTextField.setText(name);
		startDayTextField.setText(startDay);
		priceTextField.setText(price);
	}

	public void search() {
		int searchFilter = pnFormInputSearchCbb.getSelectedIndex();
		String searchData = pnFormInputSearchTextField.getText().trim();
		if (searchData.equals("")) {
			toast.showMsg(this, "Alert", "Search can't left blank!", "warning");
		} else {
			ArrayList<classx> searchClassList = classxDao.getInstance().search(searchFilter, searchData);
			dtm.setRowCount(0);
			renderData(dtm, searchClassList);
		}
	}

	public void add() {
		classx newClass = null;
		boolean priceNAN = false;
		double price = 0;
		try {
			price = convertPrice.toNumber2(priceTextField.getText());
		} catch (Exception ex) {
			priceNAN = true;
		}
		// Validate form blank
		if (classCodeTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Class code can't left blank!", "warning");
		} else if (nameTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Name can't left blank!", "warning");
		} else if (startDayTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Start day can't left blank!", "warning");
		} else if (priceTextField.getText().trim().equals("")) {
			toast.showMsg(this, "Alert", "Price can't left blank!", "warning");
		} else if (!regexCheck.validateBirthday(startDayTextField.getText())) {
		} else if (priceNAN) {
			toast.showMsg(this, "Error", "Price can't be letters or special characters!", "error");
		} else {
			String code = classCodeTextField.getText().trim();
			String name = nameTextField.getText().trim();
			String startDay = startDayTextField.getText().trim();
			newClass = new classx(code, name, startDay, price);
		}
		if (newClass != null) {
			// Check if exist in database
			boolean classExist = false;
			// Maybe don't need...
			classList = classxDao.getInstance().readAll();
			for (classx classx : classList) {
				if (classx.getCode().equals(newClass.getCode())
					|| classx.getName().equals(newClass.getName())
					|| classx.getStartDay().equals(newClass.getStartDay())) {
					classExist = true;
					toast.showMsg(this, "Alert", "Class already exist in database!", "error");
					break;
				}
			}
			// If not exist -> add to database and render new data
			if (!classExist) {
				int resultChange = classxDao.getInstance().create(newClass);
				if (resultChange != 1) {
					toast.showMsg(this, "Alert", "Add class failure!", "error");
				} else {
					ArrayList<classx> newClassList = classxDao.getInstance().readAll();
					renderData(dtm, newClassList);
				}
			}
		}
	}

	public void update() {
		classx editClass = null;
		boolean priceNAN = false;
		String code = classCodeTextField.getText();
		String name = null, startDay = null;
		double price = 0;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			// Validate form blank
			try {
				price = convertPrice.toNumber2(priceTextField.getText());
			} catch (Exception ex) {
				priceNAN = true;
			}
			if (nameTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Name can't left blank!", "warning");
			} else if (startDayTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Start day can't left blank!", "warning");
			} else if (priceTextField.getText().trim().equals("")) {
				toast.showMsg(this, "Alert", "Price can't left blank!", "warning");
			} else if (!regexCheck.validateBirthday(startDayTextField.getText())) {
			} else if (priceNAN) {
				toast.showMsg(this, "Error", "Price can't be letters or special characters!", "error");
			} else {
				name = nameTextField.getText();
				startDay = startDayTextField.getText().trim();
				editClass = new classx(code, name, startDay, price);
			}
			if (editClass != null) {
				// Check if not have a change
				boolean classNotChange = false;
				classList = classxDao.getInstance().readAll();
				for (classx classx : classList) {
					if (classx.getName().equals(name) &&
						classx.getStartDay().equals(startDay) &&
						classx.getPrice() == price) {
						classNotChange = true;
						toast.showMsg(this, "Alert", "There are no change with class", "warning");
						break;
					}
				}
				// If have a change -> update in database and render new data
				if (!classNotChange) {
					int resultChange = classxDao.getInstance().update(editClass);
					if (resultChange != 1) {
						toast.showMsg(this, "Alert", "Update class failure!", "error");
					} else {
						ArrayList<classx> newClassList = classxDao.getInstance().readAll();
						renderData(dtm, newClassList);
					}
				}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select class to update!", "warning");
		}
	}

	public void delete() {
		classx deleteClass = null;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String code = table.getValueAt(selectedRow, 0).toString();
			deleteClass = new classx(code, "", "", 0);
			if (deleteClass != null) {
				boolean isConfirm = toast.showCfm(this, "Alert", "Are you sure to delete this class?");
				if (isConfirm) {
					int resultChange = classxDao.getInstance().delete(deleteClass);
					if (resultChange != 1) {
						toast.showMsg(this, "Alert", "Delete class failure!", "error");
					} else {
						ArrayList<classx> newClassList = classxDao.getInstance().readAll();
						renderData(dtm, newClassList);
					}
				}
			}
		} else {
			toast.showMsg(this, "Alert", "Please select class to delete!", "warning");
		}
	}
	
	public void clearForm() {
		// Enable back for JTextField can't be update
		classCodeTextField.setEnabled(true);
		// Enable add button
		pnFBCreate.setEnabled(true);
		// Search
		pnFormInputSearchCbb.setSelectedIndex(0);
		pnFormInputSearchTextField.setText("");
		// Form
		classCodeTextField.setText("");
		nameTextField.setText("");
		startDayTextField.setText("");
		priceTextField.setText("");
	}

	public void resetData() {
		clearForm();
		dtm.setRowCount(0);
		classList = classxDao.getInstance().readAll();
		renderData(dtm, classList);
	}
	
	public void formatCurrency(JTextField textField) {
        String text = textField.getText().replaceAll("[^\\d]", "");

        try {
            long number = Long.parseLong(text);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedText = decimalFormat.format(number);

            textField.setText(formattedText);
        } catch (NumberFormatException ignored) {
            //
        }
    }

}