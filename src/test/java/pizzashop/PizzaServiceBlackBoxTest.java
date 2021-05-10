package pizzashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PayRepoMock;
import pizzashop.service.PizzaService;

class PizzaServiceBlackBoxTest {
	
	PizzaService pizzaService;
	PayRepoMock payRepoMock;
	
	@BeforeEach
	void setUp() {
		payRepoMock = new PayRepoMock();
		pizzaService = new PizzaService(null, payRepoMock);
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("paymentWithUnderLowerBoundTableNumber")
//	@RepeatedTest(value = 1, name = "{displayName}")
	void paymentWithUnderLowerBoundTableNumber_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(0, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("paymentWithLowerBoundTableNumber")
	void paymentWithLowerBoundTableNumber_AddPayment_PaymentIsAdded() {
		Payment expectedPayment = new Payment(1, PaymentType.Card, 10);
		
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
		assert (payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
		assert (payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("paymentWithUpperBoundTableNumber")
	void paymentWithUpperBoundTableNumber_AddPayment_PaymentIsAdded() {
		Payment expectedPayment = new Payment(8, PaymentType.Card, 10);
		
		pizzaService.addPayment(8, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
		assert (payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
		assert (payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("paymentWithAboveUpperBoundTableNumber")
//	@ParameterizedTest(name = "Table number greater than 8 is not valid.")
//	@ValueSource(ints = {9, 10})
	void paymentWithAboveUpperBoundTableNumber_AddPayment_PaymentIsNotAdded(int tableNo) {
		pizzaService.addPayment(tableNo, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void paymentWithCardPaymentType_AddPayment_PaymentIsAdded() {
		Payment expectedPayment = new Payment(1, PaymentType.Card, 10);
		
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
		assert (payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
		assert (payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
	}
	
	@Test
//	@Tag("PaymentType")
	void paymentWithCashPaymentType_AddPayment_PaymentIsAdded() {
		Payment expectedPayment = new Payment(1, PaymentType.Cash, 10);
		
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
		assert (payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
		assert (payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
	}
	
	@Test
//	@Tag("PaymentType")
	void paymentWithNullPaymentType_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, null, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void paymentWithOtherPaymentTypeThanCardOrCash_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.BotswanaCurrency, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void paymentWithUnderLowerBoundAmount_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, -1);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("BoundAmount")
	void paymentWithAboveLowerBoundAmount_AddPayment_PaymentIsAdded() {
		Payment expectedPayment = new Payment(1, PaymentType.Cash, 10);
		
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
		assert (payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
		assert (payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
	}
}