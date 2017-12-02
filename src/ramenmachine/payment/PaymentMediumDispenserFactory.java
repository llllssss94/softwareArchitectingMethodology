package ramenmachine.payment;

public class PaymentMediumDispenserFactory {
	public PaymentMediumDispenser getPaymentMediumDispenser(String type) {
		switch(type) {
		case "cash":
			return new CashDispenser();
		case "coin":
			return new CoinDispenser();
		default:
			return null;
		}
	}
}
