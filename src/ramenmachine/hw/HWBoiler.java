package ramenmachine.hw;

public class HWBoiler implements HWInterface {

	private boolean status = false;
	private String hid = "";
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status){
			status = true;
			System.out.println("보일러를 켭니다");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status){
			status = false;
			System.out.println("보일러를 끕니다");
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

}
