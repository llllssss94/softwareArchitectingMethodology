package ramenmachine.payment;

import java.util.Scanner;

public class CardReader implements PaymentMediumReader {

	public Object read() {
		System.out.println("카드를 투입하세요.");
		Scanner scan = new Scanner(System.in);
		String strValue = scan.nextLine();
		return strValue;
	}
}
