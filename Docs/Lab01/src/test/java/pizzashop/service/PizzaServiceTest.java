package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    PizzaService pizzaService;
    PayRepoMock payRepoMock;
    @BeforeEach
    void setUp() {
        payRepoMock = new PayRepoMock();
        pizzaService = new PizzaService(null, payRepoMock);
    }

    @Test
    @Tag("BoundTableNumber")
    @DisplayName("PaymentWithUnderLowerBoundTableNumber")
    @RepeatedTest(value = 1, name="{displayName}")
    void PaymentWithUnderLowerBoundTableNumber_AddPayment_PaymentIsNotAdded(){
        pizzaService.addPayment(0,PaymentType.Card, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    @Tag("BoundTableNumber")
    @DisplayName("PaymentWithLowerBoundTableNumber")
    void PaymentWithLowerBoundTableNumber_AddPayment_PaymentIsAdded(){
        Payment expectedPayment = new Payment(1,PaymentType.Card, 10);

        pizzaService.addPayment(1,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    @Tag("BoundTableNumber")
    @DisplayName("PaymentWithUpperBoundTableNumber")
    void PaymentWithUpperBoundTableNumber_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(8,PaymentType.Card, 10);

        pizzaService.addPayment(8,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Tag("BoundTableNumber")
    @DisplayName("PaymentWithAboveUpperBoundTableNumber")
    @ParameterizedTest(name = "Table number greater than 8 is not valid.")
    @ValueSource(ints = {9,10})
    void PaymentWithAboveUpperBoundTableNumber_AddPayment_PaymentIsNotAdded(int tableNo) {
        pizzaService.addPayment(tableNo,PaymentType.Card, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    @Tag("PaymentType")
    void PaymentWithCardPaymentType_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Card, 10);

        pizzaService.addPayment(1,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    @Tag("PaymentType")
    void PaymentWithCashPaymentType_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Cash, 10);

        pizzaService.addPayment(1,PaymentType.Cash, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    @Tag("PaymentType")
    void PaymentWithNullPaymentType_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,null, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    @Tag("PaymentType")
    void PaymentWithOtherPaymentTypeThanCardOrCash_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,PaymentType.BotswanaCurrency, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    @Tag("PaymentType")
    void PaymentWithUnderLowerBoundAmount_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,PaymentType.Cash, -1);

        assert(payRepoMock.getAll()).isEmpty();
    }


    @Test
    @Tag("BoundAmount")
    void PaymentWithAboveLowerBoundAmount_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Cash, 10);

        pizzaService.addPayment(1,PaymentType.Cash, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    void F02_TC01() {
        List<Payment> payments = null;

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 0;
    }

    @Test
    void F02_TC02() {
        List<Payment> payments = new ArrayList<>();

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 0;
    }

    @Test
    void F02_TC03() {
        List<Payment> payments = Arrays.asList(new Payment(1,PaymentType.Card,10));

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 0;
    }

    @Test
    void F02_TC04() {
        List<Payment> payments = Arrays.asList(new Payment(1,PaymentType.Cash,10));

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 10;
    }

    @Test
    void F02_TC05() {
        List<Payment> payments = Arrays.asList(new Payment(1,PaymentType.Cash,10), new Payment(2,PaymentType.Cash,10));

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 20;
    }

    @Test
    void F02_TC06() {
        List<Payment> payments = Arrays.asList(new Payment(1,PaymentType.Cash,10),
                new Payment(2,PaymentType.Cash,10),
                new Payment(2,PaymentType.Card,10));

        double amount = pizzaService.getTotalAmount(PaymentType.Cash, payments);

        assert(amount) == 20;
    }
}