package ramenmachine.model.bean;

public class Ingredient {
	private int num;
	private String name;
	private int price;
	private String type;
	private String sensorId;
	private String hwId;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public String getHwId() {
		return hwId;
	}
	public void setHwId(String hwId) {
		this.hwId = hwId;
	} 
}
