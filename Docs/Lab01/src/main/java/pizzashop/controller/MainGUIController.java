package pizzashop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pizzashop.gui.OrdersGUI;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

public class MainGUIController {
	
	private PizzaService service;
	@FXML
	private Button table1;
	@FXML
	private Button table2;
	@FXML
	private Button table3;
	@FXML
	private Button table4;
	@FXML
	private Button table5;
	@FXML
	private Button table6;
	@FXML
	private Button table7;
	@FXML
	private Button table8;
	@FXML
	private MenuItem help;
	
	public MainGUIController() {
	
	}
	
	public void setService(PizzaService service) {
		this.service = service;
		tableHandlers();
	}
	
	private void tableHandlers() {
		List<OrdersGUI> tableOrders = new ArrayList<>(8);
		for (int i = 0; i < 8; i++) {
			tableOrders.add(new OrdersGUI(i + 1, service));
		}
		
		table1.setOnAction(event -> tableOrders.get(0).show());
		table2.setOnAction(event -> tableOrders.get(1).show());
		table3.setOnAction(event -> tableOrders.get(2).show());
		table4.setOnAction(event -> tableOrders.get(3).show());
		table5.setOnAction(event -> tableOrders.get(4).show());
		table6.setOnAction(event -> tableOrders.get(5).show());
		table7.setOnAction(event -> tableOrders.get(6).show());
		table8.setOnAction(event -> tableOrders.get(7).show());
	}
	
	public void initialize() {
		help.setOnAction((ActionEvent event) -> {
			Stage stage = new Stage();
			
			stage.setTitle("How to use..");
			final Group rootGroup = new Group();
			final Scene scene = new Scene(rootGroup, 600, 250);
			final Text text1 = new Text(25, 25,
					"1. Click on a Table button - a table order form will pop up. " + System.lineSeparator()
							+ System.lineSeparator() +
							"2.Choose a Menu item from the menu list, choose Quantity from drop down list, " + System.lineSeparator()
							+ "press Add to order button and Click on the Menu list (Repeat)" + System.lineSeparator()
							+ System.lineSeparator() +
							"3.Press Place order button, the order will appear in the Kitchen window" + System.lineSeparator()
							+ System.lineSeparator() +
							"4.On the Kitchen window Click on the order in the Orders List and Press the Cook button, " + System
							.lineSeparator()
							+ "then after Click on the order in the Orders list and then on the Ready button" + System.lineSeparator()
							+ System.lineSeparator() +
							"5.On the Table order form press the Order served button, at the end press" + System.lineSeparator()
							+ "the Pay order button " + System.lineSeparator()
			);
			
			text1.setFont(Font.font(java.awt.Font.SERIF, 15));
			rootGroup.getChildren().add(text1);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		});
	}
}
