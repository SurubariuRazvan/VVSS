package pizzashop.service;

import pizzashop.model.Payment;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

public class PayRepoMock extends PaymentRepository {
    List<Payment> payments = new ArrayList<>();
    @Override
    public void add(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getAll() {
        return this.payments;
    }
}
