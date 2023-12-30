package model;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class person {
	private String code, indentity, name;
	private int gender;
	private String birthday, phone, email, address;
	
	public person(String code, String indentity, String name, int gender,
		String birthday, String phone, String email, String address) {
		this.code = code;
		this.indentity = indentity;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getCode() {
		return code;
	}
	public String getIndentity() {
		return indentity;
	}
	public String getName() {
		return name;
	}
	public int getGender() {
		return gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public void setIndentity(String indentity) {
		this.indentity = indentity;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "code=" + code + ", indentity=" + indentity + ", name=" + name + ", gender=" + gender
				+ ", birthday=" + birthday + ", phone=" + phone + ", email=" + email +  ", address=" + address;
	}
	
}