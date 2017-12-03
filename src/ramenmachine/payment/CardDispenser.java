package ramenmachine.payment;

public class CardDispenser implements PaymentMediumDispenser{

	public void dispense(int amount) {
		System.out.println("카드를 반환합니다.");
	}
}
