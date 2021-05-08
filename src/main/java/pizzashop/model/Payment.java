package pizzashop.model;

public class Payment {

	private int tableNumber;
	private PaymentType type;
	private double amount;
	
	public Payment() {
	}
	
	public Payment(int tableNumber, PaymentType type, double amount) {
		this.tableNumber = tableNumber;
		this.type = type;
		this.amount = amount;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public PaymentType getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return tableNumber + "," + type + "," + amount;
	}
}
