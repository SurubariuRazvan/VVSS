package pizzashop.repository;

import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentRepositoryTestIntegration {
	
	@Test
	void add() {
		PaymentRepository repository = new PaymentRepository("data/nonEmptyPayments.txt");
		int sizeBefore = repository.getAll().size();
		Payment payment = new Payment(0, PaymentType.Cash, 23D);
		repository.add(payment);
		List<Payment> after = repository.getAll();
		assertEquals(sizeBefore + 1, after.size());
		assertTrue(after.contains(payment));
	}
	
	@Test
	void getAll_emptyLine() {
		PaymentRepository repository = new PaymentRepository("data/emptyLinePayments.txt");
		assertNotEquals(repository.getAll(), Collections.emptyList());
	}
	
	@Test
	void getAll_nonEmptyFile() {
		PaymentRepository repository = new PaymentRepository();
		assertNotEquals(repository.getAll(), Collections.emptyList());
	}
	
	@Test
	void getAll_emptyFile() {
		PaymentRepository repository = new PaymentRepository("data/emptyPayments.txt");
		assertEquals(repository.getAll(), Collections.emptyList());
	}
}