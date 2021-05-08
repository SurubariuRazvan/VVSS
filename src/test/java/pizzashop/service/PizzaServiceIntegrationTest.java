package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PizzaServiceIntegrationTest {
	
	PaymentRepository payRepo;
	PizzaService pizzaService;
	
	@BeforeEach
	void setUp() {
		payRepo = new PaymentRepository("data/integrationPayments.txt");
		payRepo.writeAll(new ArrayList<>());
		pizzaService = new PizzaService(null, payRepo);
	}
	
	@Test
	void serviceGetAll() {
		assertEquals(pizzaService.getPayments(), Collections.emptyList());
	}
	
	@Tag("BoundTableNumber")
	@DisplayName("PaymentWithUnderLowerBoundTableNumber")
	@RepeatedTest(value = 1, name = "{displayName}")
	void PaymentWithUnderLowerBoundTableNumber_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(0, PaymentType.Card, 10);
		
		assert (payRepo.getAll()).isEmpty();
	}
	
	@Test
	@Tag("BoundTableNumber")
	@DisplayName("PaymentWithLowerBoundTableNumber")
	void PaymentWithLowerBoundTableNumber_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepo.getAll().size()) == 1;
		assert (payRepo.getAll().get(0).getTableNumber()) == 1;
		assert (payRepo.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepo.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
	@Tag("BoundTableNumber")
	@DisplayName("PaymentWithUpperBoundTableNumber")
	void PaymentWithUpperBoundTableNumber_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(8, PaymentType.Card, 10);
		
		assert (payRepo.getAll().size()) == 1;
		assert (payRepo.getAll().get(0).getTableNumber()) == 8;
		assert (payRepo.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepo.getAll().get(0).getAmount()) == 10;
	}
	
	@Tag("BoundTableNumber")
	@DisplayName("PaymentWithAboveUpperBoundTableNumber")
	@ParameterizedTest(name = "Table number greater than 8 is not valid.")
	@ValueSource(ints = {9, 10})
	void PaymentWithAboveUpperBoundTableNumber_AddPayment_PaymentIsNotAdded(int tableNo) {
		pizzaService.addPayment(tableNo, PaymentType.Card, 10);
		
		assert (payRepo.getAll()).isEmpty();
	}
	
	@Test
	@Tag("PaymentType")
	void PaymentWithCardPaymentType_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepo.getAll().size()) == 1;
		assert (payRepo.getAll().get(0).getTableNumber()) == 1;
		assert (payRepo.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepo.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
	@Tag("PaymentType")
	void PaymentWithCashPaymentType_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepo.getAll().size()) == 1;
		assert (payRepo.getAll().get(0).getTableNumber()) == 1;
		assert (payRepo.getAll().get(0).getType()) == PaymentType.Cash;
		assert (payRepo.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
	@Tag("PaymentType")
	void PaymentWithNullPaymentType_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, null, 10);
		
		assert (payRepo.getAll()).isEmpty();
	}
	
	@Test
	@Tag("PaymentType")
	void PaymentWithOtherPaymentTypeThanCardOrCash_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.BotswanaCurrency, 10);
		
		assert (payRepo.getAll()).isEmpty();
	}
	
	@Test
	@Tag("PaymentType")
	void PaymentWithUnderLowerBoundAmount_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, -1);
		
		assert (payRepo.getAll()).isEmpty();
	}
	
	@Test
	@Tag("BoundAmount")
	void PaymentWithAboveLowerBoundAmount_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepo.getAll().size()) == 1;
		assert (payRepo.getAll().get(0).getTableNumber()) == 1;
		assert (payRepo.getAll().get(0).getType()) == PaymentType.Cash;
		assert (payRepo.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
	void F02_TC01() {
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, null);
		
		assert (amount) == 0;
	}
	
	@Test
	void F02_TC02() {
		double amount = pizzaService.getTotalAmount(PaymentType.Cash, new ArrayList<>());
		
		assert (amount) == 0;
	}
}