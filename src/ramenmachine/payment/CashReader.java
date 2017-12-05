package ramenmachine.payment;

import java.util.Scanner;

public class CashReader implements PaymentMediumReader {
	private Scanner scan;

	@Override
	public Object read() {
		boolean flag = true;
		int value = 0;
		while (flag) {
			try {
				System.out.println("지폐를 투입하세요.");
				scan = new Scanner(System.in);
				String strValue = scan.nextLine();
				value = Integer.parseInt(strValue);

				flag = false;
			} catch (Exception e) {
				
			}
		}
		return value;
	}

}
