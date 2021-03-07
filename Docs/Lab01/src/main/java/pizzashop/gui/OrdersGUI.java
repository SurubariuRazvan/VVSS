package pizzashop.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pizzashop.controller.OrdersGUIController;
import pizzashop.service.PizzaService;

import java.io.IOException;
import java.util.Objects;

public class OrdersGUI implements GUI {
	private final int tableNumber;
	private final PizzaService service;

	public OrdersGUI(int tableNumber, PizzaService service) {
		this.tableNumber = tableNumber;
		this.service = service;
	}

	@Override
	public void show() {
		VBox vBoxOrders = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrdersGUIFXML.fxml"));
			vBoxOrders = loader.load();
			OrdersGUIController ordersCtrl = loader.getController();
			ordersCtrl.setService(service, tableNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(vBoxOrders)) {
			Stage stage = new Stage();
			stage.setTitle("Table" + getTableNumber() + " order form");
			stage.setResizable(false);
			stage.setOnCloseRequest(Event::consume);
			stage.setScene(new Scene(vBoxOrders));
			stage.show();
		}
	}

	public int getTableNumber() {
		return tableNumber;
	}
}
