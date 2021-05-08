package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Calendar;

public class KitchenGUIController {
	public final static ObservableList<String> orderList = FXCollections.observableArrayList();
	@FXML
	public Button cook;
	@FXML
	public Button ready;
	@FXML
	private ListView<String> kitchenOrdersList;
	
	public void initialize() {
		orderList.addListener((ListChangeListener<String>) c -> Platform.runLater(() -> kitchenOrdersList.setItems(orderList)));
		
		//Controller for Cook Button
		cook.setOnAction(event -> {
			Calendar now = Calendar.getInstance();
			int selectedOrderIndex = kitchenOrdersList.getSelectionModel().getSelectedIndex();
			
			if (selectedOrderIndex != -1) {
				String order = kitchenOrdersList.getItems().remove(selectedOrderIndex);
				kitchenOrdersList.getItems().add((order + " Cooking started at: ").toUpperCase()
						+ now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
			}
		});
		//Controller for Ready Button
		ready.setOnAction(event -> {
			Calendar now = Calendar.getInstance();
			int selectedOrderIndex = kitchenOrdersList.getSelectionModel().getSelectedIndex();
			
			if (selectedOrderIndex != -1) {
				String order = kitchenOrdersList.getItems().remove(selectedOrderIndex);
				int extractedTableNumberInteger = Integer.parseInt(order.subSequence(5, 6).toString());
				System.out.println("--------------------------");
				System.out.println("Table " + extractedTableNumberInteger + " ready at: "
						+ now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
				System.out.println("--------------------------");
			}
		});
	}
}