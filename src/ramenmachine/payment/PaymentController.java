package ramenmachine.payment;

public class PaymentController {
	PaymentMediumDispenserFactory dispenserFactory = new PaymentMediumDispenserFactory();
	PaymentMediumReaderFactory readerFactory = new PaymentMediumReaderFactory();
	
	PaymentMediumDispenser cardDispenser;
	PaymentMediumDispenser cashDispenser;
	PaymentMediumDispenser coinDispenser;
	
	PaymentMediumReader cardReader;
	PaymentMediumReader cashReader;
	PaymentMediumReader coinReader;
	
	public PaymentController() {
		cardDispenser = dispenserFactory.getPaymentMediumDispenser("card");
		cashDispenser = dispenserFactory.getPaymentMediumDispenser("cash");
		coinDispenser = dispenserFactory.getPaymentMediumDispenser("coin");
		
		cardReader = readerFactory.getPaymentMediumReader("card");
		cashReader = readerFactory.getPaymentMediumReader("cash");
		coinReader = readerFactory.getPaymentMediumReader("coin");
	}
	
	
	public void handlePayment(String type, int amount) {
		int cash = 0;
		switch(type) {
		case "cash":
			int cashValue = 0;
			cashValue = (int)cashReader.read();
			int coinValue = 0;
			coinValue = (int)coinReader.read();
			while(cashValue + coinValue <= amount) {
				coinValue += (int)coinReader.read();
				if(cashValue + coinValue >= amount) 
					break;
				cashValue += (int)cashReader.read();
			}
			
			int rechargeValue = cashValue + coinValue - amount;
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
