package model;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class classx {
	private String code, name, startDay;
	private double price;
	
	public classx(String code, String name, String startDay, double price) {
		this.code = code;
		this.name = name;
		this.startDay = startDay;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getStartDay() {
		return startDay;
	}
	public double getPrice() {
		return price;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "code=" + code + ", name=" + name + ", startDay=" + startDay + ", price=" + price;
	}
	
}