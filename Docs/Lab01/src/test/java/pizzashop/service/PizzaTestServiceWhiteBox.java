package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaTestServiceWhiteBox {
	
	PizzaService pizzaService;
	PayRepoMock payRepoMock;
	
	@BeforeEach
	void setUp() {
		payRepoMock = new PayRepoMock();
		pizzaService = new PizzaService(null, payRepoMock);
	}
	
	@Test
	void F02_TC01() {
		List<Payment> payments = null;
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 0;
	}
	
	@Test
	void F02_TC02() {
		List<Payment> payments = new ArrayList<>();
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 0;
	}
	
	@Test
	void F02_TC03() {
		List<Payment> payments = Arrays.asList(new Payment(1, PaymentType.Card, 10));
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 0;
	}
	
	@Test
	void F02_TC04() {
		List<Payment> payments = Arrays.asList(new Payment(1, PaymentType.Cash, 10));
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 10;
	}
	
	@Test
	void F02_TC05() {
		List<Payment> payments = Arrays.asList(new Payment(1, PaymentType.Cash, 10), new Payment(2, PaymentType.Cash, 10));
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 20;
	}
	
	@Test
	void F02_TC06() {
		List<Payment> payments = Arrays.asList(new Payment(1, PaymentType.Cash, 10),
				new Payment(2, PaymentType.Cash, 10),
				new Payment(2, PaymentType.Card, 10));
		
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);
		
		assert (amount) == 20;
	}
}
