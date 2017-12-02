package ramenmachine.hw;

public class HWIngredient implements HWInterface, Dispensor {
	private String hid;
	private boolean status = false;
	private String name;
	
	public HWIngredient(String hid, String name) {
		this.hid = hid;
		this.name = name;
	}
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status){
			status = true;
			System.out.println("재료("+name+")통을 켭니다");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status){
			status = false;
			System.out.println("재료("+name+")통을 끕니다");
		}
	}

	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public String getHWId() {
		// TODO Auto-generated method stub
		return hid;
	}

	@Override
	public void dispense(int n) {
		// TODO Auto-generated method stub

	}
}
