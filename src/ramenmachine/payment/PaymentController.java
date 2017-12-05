package ramenmachine.payment;

public class PaymentController {
	private PaymentMediumDispenserFactory dispenserFactory = new PaymentMediumDispenserFactory();
	private PaymentMediumReaderFactory readerFactory = new PaymentMediumReaderFactory();
	
	private PaymentMediumDispenser cardDispenser;
	private PaymentMediumDispenser cashDispenser;
	private PaymentMediumDispenser coinDispenser;
	
	private PaymentMediumReader cardReader;
	private PaymentMediumReader cashReader;
	private PaymentMediumReader coinReader;
	
	public PaymentController() {
		cardDispenser = dispenserFactory.getPaymentMediumDispenser("card");
		cashDispenser = dispenserFactory.getPaymentMediumDispenser("cash");
		coinDispenser = dispenserFactory.getPaymentMediumDispenser("coin");
		
		cardReader = readerFactory.getPaymentMediumReader("card");
		cashReader = readerFactory.getPaymentMediumReader("cash");
		coinReader = readerFactory.getPaymentMediumReader("coin");
	}
	
	
	public void handlePayment(String type, int amount) {
		switch(type) {
		case "cash":
			int cashValue = 0;
			int coinValue = 0;
			int rechargeValue= 0;
			cashValue = (int)cashReader.read();
			
			while(cashValue + coinValue <= amount) {
				coinValue += (int)coinReader.read();
				if(cashValue + coinValue >= amount) 
					break;
				System.out.println("현재 투입하신 금액은 " + cashValue + coinValue + "입니다.");
				cashValue += (int)cashReader.read();
			}
			
			rechargeValue = cashValue + coinValue - amount;
			System.out.println("현금 "+amount+ " 만큼 결제합니다.");
			cashDispenser.dispense(rechargeValue/1000 * 1000);
			coinDispenser.dispense(rechargeValue%1000);	
			break;
		case "card":
			cardReader.read();
			System.out.println("카드에서 "+ amount+" 만큼 결제합니다.");
			cardDispenser.dispense(0);
			break;
		default:
			break;
		}
	}
}
