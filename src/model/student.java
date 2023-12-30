package model;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class student extends person {
	private String job, className;
	private double point;
	
	public student(String code, String indentity, String name, int gender, String birthday,
		String phone, String email, String address, String job, String className, double point) {
		super(code, indentity, name, gender, birthday, phone, email, address);
		this.job = job;
		this.className = className;
		this.point = point;
	}
	
	public String getJob() {
		return job;
	}
	public String getClassName() {
		return className;
	}
	public double getPoint() {
		return point;
	}

	public void setJob(String job) {
		this.job = job;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setPoint(double point) {
		this.point = point;
	}

	public String toString() {
		return super.toString() + ", job=" + job + ", className=" + className + ", point=" + point;
	}
	
}