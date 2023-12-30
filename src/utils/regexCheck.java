package utils;
import java.util.regex.*;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class regexCheck {
	private static Pattern pattern;
	private static Matcher matcher;
	private static String studentCodeRGX = "^ZH-HV\\d+";
	private static String advisorCodeRGX = "^ZH-GV\\d+";
	private static String indentityRGX = "^0\\d{11}$";
	private static String birthdayRGX = "\\b(0?[1-9]|[12]\\d|3[01])[\\/\\-.](0?[1-9]|[12]\\d|3[01])[\\/\\-.](\\d{2}|\\d{4})\\b";
	private static String phoneRGX = "^0\\d{9}$";
	private static String emailRGX = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
	private static String passwordRGX = "^.{6,}$";
	
	public static boolean validateStudentField(String studentCodeTF, String indentityTF,
		String birthdayTF, String phoneTF, String emailTF) {
		if (!validateStudentCode(studentCodeTF)) {
	    	toast.showMsg(null, "Alert", "Student code is incorrect format!", "warning");
	    	return true;
		} else if (!validateIndentity(indentityTF)) {
	    	toast.showMsg(null, "Alert", "Indentity id is incorrect format!", "warning");
	    	return true;
		} else if (!validateBirthday(birthdayTF)) {
	    	toast.showMsg(null, "Alert", "Birthday is incorrect format!", "warning");
	    	return true;
		} else if (!validatePhone(phoneTF)) {
	    	toast.showMsg(null, "Alert", "Phone is incorrect format!", "warning");
	    	return true;
		} else if (!validateEmail(emailTF)) {
	    	toast.showMsg(null, "Alert", "Email is incorrect format!", "warning");
	    	return true;
		}
		return false;
	}
	
	public static boolean validateAdvisorField(String advisorCodeTF, String indentityTF,
		String birthdayTF, String phoneTF, String emailTF) {
		if (!validateAdvisorCode(advisorCodeTF)) {
	    	toast.showMsg(null, "Alert", "Advisor code is incorrect format!", "warning");
	    	return true;
		} else if (!validateIndentity(indentityTF)) {
	    	toast.showMsg(null, "Alert", "Indentity id is incorrect format!", "warning");
	    	return true;
		} else if (!validateBirthday(birthdayTF)) {
	    	toast.showMsg(null, "Alert", "Birthday is incorrect format!", "warning");
	    	return true;
		} else if (!validatePhone(phoneTF)) {
	    	toast.showMsg(null, "Alert", "Phone is incorrect format!", "warning");
	    	return true;
		} else if (!validateEmail(emailTF)) {
	    	toast.showMsg(null, "Alert", "Email is incorrect format!", "warning");
	    	return true;
		}
		return false;
	}
	
	public static boolean validateLoginField(String emailTF, String passwordTF) {
		if (!validateEmail(emailTF)) {
	    	toast.showMsg(null, "Alert", "Email is incorrect format!", "warning");
	    	return true;
		} else if (!validatePassword(passwordTF)) {
	    	toast.showMsg(null, "Alert", "Password min 6 characters!", "warning");
	    	return true;
		}
		return false;
	}
	
	public static boolean validateRegisterField(String emailTF, String passwordTF,
		String indentityTF, String birthdayTF, String phoneTF) {
		if (!validateEmail(emailTF)) {
	    	toast.showMsg(null, "Alert", "Email is incorrect format!", "warning");
	    	return true;
		} else if (!validatePassword(passwordTF)) {
	    	toast.showMsg(null, "Alert", "Password min 6 characters!", "warning");
	    	return true;
		} else if (!validateIndentity(indentityTF)) {
	    	toast.showMsg(null, "Alert", "Indentity id is incorrect format!", "warning");
	    	return true;
		} else if (!validateBirthday(birthdayTF)) {
	    	toast.showMsg(null, "Alert", "Birthday is incorrect format!", "warning");
	    	return true;
		} else if (!validatePhone(phoneTF)) {
	    	toast.showMsg(null, "Alert", "Phone is incorrect format!", "warning");
	    	return true;
		}
		return false;
	}
	
	public static boolean validateStudentCode(String str) {
		pattern = Pattern.compile(studentCodeRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validateAdvisorCode(String str) {
		pattern = Pattern.compile(advisorCodeRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validateIndentity(String str) {
		pattern = Pattern.compile(indentityRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validateBirthday(String str) {
		pattern = Pattern.compile(birthdayRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validatePhone(String str) {
		pattern = Pattern.compile(phoneRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validateEmail(String str) {
		pattern = Pattern.compile(emailRGX, Pattern.CASE_INSENSITIVE);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}
	
	public static boolean validatePassword(String str) {
		pattern = Pattern.compile(passwordRGX);
	    matcher = pattern.matcher(str);
	    if (!matcher.find()) {
		    return false;
	    }
	    return true;
	}

}