package pizzashop.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import pizzashop.controller.MainGUIController;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;

import java.io.IOException;
import java.util.Objects;

public class MainGUI implements GUI {
	private final Stage stage;
	private final PizzaService service;

	public MainGUI(Stage stage, PizzaService service) {
		this.stage = stage;
		this.service = service;
	}

	@Override
	public void show() {
		Parent box = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainFXML.fxml"));
			box = loader.load();
			MainGUIController ctrl = loader.getController();
			ctrl.setService(service);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(box)) {
			stage.setTitle("PizeriaX");
			stage.setResizable(false);
			stage.setAlwaysOnTop(false);
			stage.setOnCloseRequest(event -> {
				Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit the Main window?", ButtonType.YES,
				                            ButtonType.NO);
				ButtonType result = exitAlert.showAndWait().orElse(ButtonType.NO);
				if (result == ButtonType.YES) {
					System.out.println("Incasari cash: " + service.getTotalAmount(PaymentType.CASH));
					System.out.println("Incasari card: " + service.getTotalAmount(PaymentType.CARD));
					stage.close();
				} else {
					event.consume();
				}
			});
			stage.setScene(new Scene(box));
			stage.show();
		}
	}
}
