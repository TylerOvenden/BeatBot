package javaFX;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main10 extends Application {


	Stage window;
	TreeView<String> tree;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("title");
		
		TreeItem<String> root, welcome, okay;
		
		root = new TreeItem<>();
		root.setExpanded(true);
		
		welcome = makeBranch("welcome", root);
		makeBranch("what is this", welcome);
		makeBranch("dcve", welcome);
		makeBranch("done", welcome);
		
		okay = makeBranch("okay", root);
		makeBranch("love", okay);
		makeBranch("test", okay);
		makeBranch("finish", okay);
		
		tree = new TreeView<>(root);
		tree.setShowRoot(false);
		tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> {
				if(newValue != null)
					System.out.println(newValue.getValue());
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().add(tree);
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

	

	}

	public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}





}
