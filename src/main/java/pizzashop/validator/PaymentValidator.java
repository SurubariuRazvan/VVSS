package pizzashop.validator;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class PaymentValidator {

    public static List<String> validate(Payment payment) {
        List<String> errors = new ArrayList<>();

        int tableNumber = payment.getTableNumber();
        PaymentType type = payment.getType();
        double amount = payment.getAmount();

        if (tableNumber < 1 || tableNumber > 8) {
            errors.add("Invalid table number.");
        }

        if(!PaymentType.Cash.equals(type) && !PaymentType.Card.equals(type)){
            errors.add("Invalid payment type.");
        }

        if (amount < 0) {
            errors.add("Invalid amount number.");
        }

        return errors;
    }
}