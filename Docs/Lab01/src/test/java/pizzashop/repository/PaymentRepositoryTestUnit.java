package pizzashop.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PaymentRepositoryTestUnit {
	
	@Test
	void add() {
		PaymentRepository repository = new PaymentRepository("data/nonEmptyPayments.txt");
		int sizeBefore = repository.getAll().size();
		
		Payment paymentMock = Mockito.mock(Payment.class);
		when(paymentMock.getTableNumber()).thenReturn(0);
		when(paymentMock.getType()).thenReturn(PaymentType.Cash);
		when(paymentMock.getAmount()).thenReturn(23D);
		when(paymentMock.toString()).thenReturn(0 + "," + PaymentType.Cash + "," + 23D);
		
		repository.add(paymentMock);
		List<Payment> after = repository.getAll();
		assertEquals(sizeBefore + 1, after.size());
		assertTrue(after.contains(paymentMock));
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