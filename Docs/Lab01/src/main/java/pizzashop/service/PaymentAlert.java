package pizzashop.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

public class PaymentAlert implements PaymentOperation {
	private final PizzaService service;
	private final String newLineSeparator = "--------------------------";

	public PaymentAlert(PizzaService service) {
		this.service = service;
	}

	public void showPaymentAlert(int tableNumber, double totalAmount) {
		Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
		paymentAlert.setTitle("Payment for Table " + tableNumber);
		paymentAlert.setHeaderText("Total amount: " + totalAmount);
		paymentAlert.setContentText("Please choose payment option");

		ButtonType cardPayment = new ButtonType("Pay by Card");
		ButtonType cashPayment = new ButtonType("Pay Cash");
		ButtonType cancel = new ButtonType("Cancel");

		paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);

		ButtonType result = paymentAlert.showAndWait().orElse(cancel);
		if (result == cardPayment) {
			cardPayment();
			service.addPayment(tableNumber, PaymentType.CARD, totalAmount);
		} else if (result == cashPayment) {
			cashPayment();
			service.addPayment(tableNumber, PaymentType.CASH, totalAmount);
		} else {
			cancelPayment();
		}
	}

	@Override
	public void cardPayment() {
		System.out.println(newLineSeparator);
		System.out.println("Paying by card...");
		System.out.println("Please insert your card!");
		System.out.println("--------------------------");
	}

	@Override
	public void cashPayment() {
		System.out.println("--------------------------");
		System.out.println("Paying cash...");
		System.out.println("Please show the cash...!");
		System.out.println("--------------------------");
	}

	@Override
	public void cancelPayment() {
		System.out.println("--------------------------");
		System.out.println("Payment choice needed...");
		System.out.println("--------------------------");
	}
}
