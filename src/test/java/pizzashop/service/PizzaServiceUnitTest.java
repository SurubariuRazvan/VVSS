package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PizzaServiceUnitTest {
	
	PaymentRepository payRepoMock;
	MenuRepository menuRepoMock;
	PizzaService pizzaService;
	
	@BeforeEach
	void setUp() {
		payRepoMock = mock(PaymentRepository.class);
		
		List<Payment> payments = new ArrayList<>();
		when(payRepoMock.getAll()).thenReturn(payments);
		
		doAnswer((Answer<Void>) invocation -> {
			Object[] args = invocation.getArguments();
			Payment payment = (Payment) args[0];
			payments.add(payment);
			return null;
		}).when(payRepoMock).add(Mockito.any());
		
		menuRepoMock = mock(MenuRepository.class);
		
		List<MenuDataModel> menu = new ArrayList<>();
		when(menuRepoMock.getMenu()).thenReturn(menu);
		
		pizzaService = new PizzaService(menuRepoMock, payRepoMock);
	}
	
	@Test
	void serviceGetAll() {
		List<MenuDataModel> menuData = pizzaService.getMenuData();
		List<Payment> payments = pizzaService.getPayments();
		
		assertEquals(menuData, Collections.emptyList());
		assertEquals(payments, Collections.emptyList());
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("PaymentWithUnderLowerBoundTableNumber")
//	@RepeatedTest(value = 1, name = "{displayName}")
	void PaymentWithUnderLowerBoundTableNumber_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(0, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("PaymentWithLowerBoundTableNumber")
	void PaymentWithLowerBoundTableNumber_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == 1;
		assert (payRepoMock.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepoMock.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("PaymentWithUpperBoundTableNumber")
	void PaymentWithUpperBoundTableNumber_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(8, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == 8;
		assert (payRepoMock.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepoMock.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
//	@Tag("BoundTableNumber")
//	@DisplayName("PaymentWithAboveUpperBoundTableNumber")
//	@ParameterizedTest(name = "Table number greater than 8 is not valid.")
//	@ValueSource(ints = {9, 10})
	void PaymentWithAboveUpperBoundTableNumber_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(9, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void PaymentWithCardPaymentType_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Card, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == 1;
		assert (payRepoMock.getAll().get(0).getType()) == PaymentType.Card;
		assert (payRepoMock.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
//	@Tag("PaymentType")
	void PaymentWithCashPaymentType_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == 1;
		assert (payRepoMock.getAll().get(0).getType()) == PaymentType.Cash;
		assert (payRepoMock.getAll().get(0).getAmount()) == 10;
	}
	
	@Test
//	@Tag("PaymentType")
	void PaymentWithNullPaymentType_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, null, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void PaymentWithOtherPaymentTypeThanCardOrCash_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.BotswanaCurrency, 10);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("PaymentType")
	void PaymentWithUnderLowerBoundAmount_AddPayment_PaymentIsNotAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, -1);
		
		assert (payRepoMock.getAll()).isEmpty();
	}
	
	@Test
//	@Tag("BoundAmount")
	void PaymentWithAboveLowerBoundAmount_AddPayment_PaymentIsAdded() {
		pizzaService.addPayment(1, PaymentType.Cash, 10);
		
		assert (payRepoMock.getAll().size()) == 1;
		assert (payRepoMock.getAll().get(0).getTableNumber()) == 1;
		assert (payRepoMock.getAll().get(0).getType()) == PaymentType.Cash;
		assert (payRepoMock.getAll().get(0).getAmount()) == 10;
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