package pizzashop.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PaymentTestUnit {
	static Payment payment;
	
	@BeforeAll
	static void setUp() {
		payment = new Payment(0, PaymentType.BotswanaCurrency, 2);
	}
	
	@Test
	void constructor() {
		try {
			new Payment();
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void getTableNumber() {
		assertEquals(payment.getTableNumber(), 0);
	}
	
	@Test
	void getType() {
		assertEquals(payment.getType(), PaymentType.BotswanaCurrency);
	}
	
	@Test
	void getAmount() {
		assertEquals(payment.getAmount(), 2);
	}
	
	@Test
	void testToString() {
		assertEquals(payment.toString(), payment.getTableNumber() + "," + payment.getType() + "," + payment.getAmount());
	}
}