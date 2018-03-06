package javaFX;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main11 extends Application {


	Stage window;
	Scene scene;
	Button button;
	ListView<String> listView;
	TableView<Product> table;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		
		TableColumn<Product, String> nameColumn = new TableColumn<>("name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Product, Double> priceColumn = new TableColumn<>("price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<Product, Double> quantityColumn = new TableColumn<>("quantity");
		quantityColumn.setMinWidth(100);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		table = new TableView<>();
		table.setItems(getProduct());
		table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
		VBox vBox = new VBox(10);
		
		vBox.getChildren().addAll();
		
		scene = new Scene(vBox);
		window.setScene(scene);
		window.show();

	

	}

	public ObservableList<Product> getProduct(){
		ObservableList<Product> products = FXCollections.observableArrayList();
		products.add(new Product("Laptop",859.00,20));
		products.add(new Product("Bouncy BALL",3.33,12));
		products.add(new Product("EWF",29.01,76));
		products.add(new Product("DVD",15.23,12));
		products.add(new Product("Food",1.21,13));
		return products;
	}



}
