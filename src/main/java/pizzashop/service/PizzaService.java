package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

public class PizzaService {
	
	private final MenuRepository menuRepo;
	private final PaymentRepository payRepo;
	
	public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo) {
		this.menuRepo = menuRepo;
		this.payRepo = payRepo;
	}
	
	public List<MenuDataModel> getMenuData() {
		return menuRepo.getMenu();
	}
	
	public void addPayment(int table, PaymentType type, double amount) {
		Payment payment = new Payment(table, type, amount);
		List<String> errors = PaymentValidator.validate(payment);
		if(errors.size() > 0) {
			System.out.println(errors);
		}else{
			payRepo.add(payment);
		}
	}
	
	public double getTotalAmount(PaymentType type, List<Payment> l) {
		double total = 0.0f;

		if (l != null) {
			if (l.size() != 0) {
				int i = 0;
				while (i < l.size()) {
					double ammount = 0;
					if (l.get(i).getType().equals(type)) {
						ammount = l.get(i).getAmount();
					}
					total += ammount;
					i++;
				}
			}
		}
		return total;
	}
	
	public List<Payment> getPayments() {
		return payRepo.getAll();
	}
	
}
