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
	
	public void handlePayment(PaymentObject object) {
		switch(object.getType()) {
		case "cash":
			cashReader.read(object);
			
			cashDispenser.dispense(object);
			coinDispenser.dispense(object);
			break;
		case "card":
			cardReader.read(object);
			
			cardDispenser.dispense(object);
			break;
		case "coin":
			coinReader.read(object);
			
			coinDispenser.dispense(object);
			break;
		}
	}
}
