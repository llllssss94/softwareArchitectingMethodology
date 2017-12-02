package ramenmachine.payment;

public class PaymentMediumReaderFactory {
	
	public PaymentMediumReaderFactory() {
		
	}
	
	public PaymentMediumReader getPaymentMediumReader(String type) {
		switch (type) {
		case "card":
			return new CardReader();
		case "cash":
			return new CashReader();
		case "coin":
			return new CoinReader();
		default:
			return null;
		}
	}
}
