package ramenmachine.payment;

public class CashDispenser implements PaymentMediumDispenser{

	@Override
	public void dispense(int amount) {
		System.out.println("지폐 " + amount +"원을 반환합니다.");
	}
}
