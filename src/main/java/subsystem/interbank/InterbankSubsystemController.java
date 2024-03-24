package subsystem.interbank;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/*
* SOLID - Open/closed principle: InterbankPayloadConverter khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do payOrder(), refund() hiện tại chỉ nhận vào CreditCard
*/

/*
 * SOLID - Dependency inversion principle: InterbankPayloadConverter phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
*/

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}

	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
