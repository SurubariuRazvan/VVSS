package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

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
    void PaymentWithUnderLowerBoundTableNumber_AddPayment_PaymentIsNotAdded(){
        pizzaService.addPayment(0,PaymentType.Card, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    void PaymentWithLowerBoundTableNumber_AddPayment_PaymentIsAdded(){
        Payment expectedPayment = new Payment(1,PaymentType.Card, 10);

        pizzaService.addPayment(1,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    void PaymentWithUpperBoundTableNumber_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(8,PaymentType.Card, 10);

        pizzaService.addPayment(8,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    void PaymentWithAboveUpperBoundTableNumber_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(9,PaymentType.Card, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    void PaymentWithCardPaymentType_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Card, 10);

        pizzaService.addPayment(1,PaymentType.Card, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    void PaymentWithCashPaymentType_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Cash, 10);

        pizzaService.addPayment(1,PaymentType.Cash, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

    @Test
    void PaymentWithNullPaymentType_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,null, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    void PaymentWithOtherPaymentTypeThanCardOrCash_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,PaymentType.BotswanaCurrency, 10);

        assert(payRepoMock.getAll()).isEmpty();
    }

    @Test
    void PaymentWithUnderLowerBoundAmount_AddPayment_PaymentIsNotAdded() {
        pizzaService.addPayment(1,PaymentType.Cash, -1);

        assert(payRepoMock.getAll()).isEmpty();
    }


    @Test
    void PaymentWithAboveLowerBoundAmount_AddPayment_PaymentIsAdded() {
        Payment expectedPayment = new Payment(1,PaymentType.Cash, 10);

        pizzaService.addPayment(1,PaymentType.Cash, 10);

        assert(payRepoMock.getAll().size()) == 1;
        assert(payRepoMock.getAll().get(0).getTableNumber()) == expectedPayment.getTableNumber();
        assert(payRepoMock.getAll().get(0).getAmount()) == expectedPayment.getAmount();
        assert(payRepoMock.getAll().get(0).getType()) == expectedPayment.getType();
    }

}