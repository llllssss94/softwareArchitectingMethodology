package ramenmachine.payment;

import java.util.Scanner;

public class CoinReader implements PaymentMediumReader{

	@Override
	public Object read() {
		boolean flag = true;
		int value = 0;
		while (flag) {
			try {
				System.out.println("동전을 투입하세요.");
				Scanner scan = new Scanner(System.in);
				String strValue = scan.nextLine();
				value = Integer.parseInt(strValue);

				flag = false;
			} catch (Exception e) {
				
			}
		}
		return value;
	}
}
