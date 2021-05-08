package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

public class PaymentMock extends Payment {
	private int tableNumber;
	private PaymentType type;
	private double amount;
	
	public PaymentMock(int tableNumber, PaymentType type, double amount) {
		this.tableNumber = tableNumber;
		this.type = type;
		this.amount = amount;
	}
	
	@Override
	public int getTableNumber() {
		return tableNumber;
	}
	
	@Override
	public PaymentType getType() {
		return type;
	}
	
	@Override
	public double getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return tableNumber + "," + type + "," + amount;
	}
}
