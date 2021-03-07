package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Calendar;

public class KitchenGUIController {
	public static ObservableList<String> order = FXCollections.observableArrayList();
	private final Calendar now = Calendar.getInstance();
	@FXML
	public Button cook;
	@FXML
	public Button ready;
	@FXML
	private ListView<String> kitchenOrdersList;
	private Object selectedOrder;
	private String extractedTableNumberString = "";
	private int extractedTableNumberInteger;
	
	public void initialize() {
		//thread for adding data to kitchenOrderList
		Thread addOrders = new Thread(() -> {
			while(true) {
				Platform.runLater(() -> kitchenOrdersList.setItems(order));
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					break;
				}
			}
		});
		
		//starting thread for adding data to kitchenOrderList
		addOrders.setDaemon(true);
		addOrders.start();
		//Controller for Cook Button
		cook.setOnAction(event -> {
			selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
			kitchenOrdersList.getItems().remove(selectedOrder);
			kitchenOrdersList.getItems().add(selectedOrder.toString()
					.concat(" Cooking started at: ").toUpperCase()
					.concat(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE)));
		});
		//Controller for Ready Button
		ready.setOnAction(event -> {
			selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
			kitchenOrdersList.getItems().remove(selectedOrder);
			extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
			extractedTableNumberInteger = Integer.parseInt(extractedTableNumberString);
			System.out.println("--------------------------");
			System.out.println(
					"Table " + extractedTableNumberInteger + " ready at: " + now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
			System.out.println("--------------------------");
		});
	}
}