package pizzashop;

import javafx.application.Application;
import javafx.stage.Stage;
import pizzashop.gui.KitchenGUI;
import pizzashop.gui.MainGUI;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		MenuRepository repoMenu = new MenuRepository();
		PaymentRepository payRepo = new PaymentRepository();
		PizzaService service = new PizzaService(repoMenu, payRepo);

		MainGUI mainGUI = new MainGUI(primaryStage, service);
		mainGUI.show();

		KitchenGUI kitchenGUI = new KitchenGUI();
		kitchenGUI.show();
	}
}
