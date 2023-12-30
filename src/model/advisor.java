package model;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class advisor extends person {
	private String className;
	
	public advisor(String code, String indentity, String name, int gender,
		String birthday, String phone, String email, String address, String className) {
		super(code, indentity, name, gender, birthday, phone, email, address);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String toString() {
		return super.toString() + ", className=" + className;
	}
	
}