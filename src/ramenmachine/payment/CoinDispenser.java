package ramenmachine.payment;

public class CoinDispenser implements PaymentMediumDispenser{

	@Override
	public void dispense(int amount) {
		System.out.println("동전 " + amount + "원을 반환합니다.");
	}
}
