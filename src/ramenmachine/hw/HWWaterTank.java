package ramenmachine.hw;

public class HWWaterTank implements HWInterface, Dispensor {

	private boolean status = false;
	private String hid = "HID011";
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status){
			status = true;
			System.out.println("물통의 전원을 켭니다. (전원이 들어옴)");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status){
			status = false;
			System.out.println("물통의 전원을 끕니다. (전원을 끔)");
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
		System.out.println("물 "+n+"ml를 붓습니다.");
		// TODO Auto-generated method stub
	}
}
