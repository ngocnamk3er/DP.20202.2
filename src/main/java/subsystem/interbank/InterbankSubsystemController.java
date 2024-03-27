package subsystem.interbank;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {


	// Để tuân thủ DIP, có thể tạo các interface PayloadConverter và Boundary để InterbankPayloadConverter, InterbankBoundary
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
