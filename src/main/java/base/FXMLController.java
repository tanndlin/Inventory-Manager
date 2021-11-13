/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package base;


import inventory.Inventory;
import inventory.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private BorderPane pane;

    @FXML
    private TextField serialInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField valueInput;
    @FXML
    private TextField searchInput;

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, String> serialColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> valueColumn;

    private InventoryManager inventoryManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryManager = new InventoryManager(new Inventory());

        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        table.setItems(inventoryManager.getInventory().getItems());
    }

    public void onLoad(Stage stage) {
        // Bind properties for scene width && height
        Scene scene = stage.getScene();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

        serialColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));
        nameColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));
        valueColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));
    }

    @FXML
    private void addItem() {
        String serialNumber = serialInput.getText();
        String name = nameInput.getText();
        String value = valueInput.getText();

        try {
            Item item = new Item(serialNumber, name, value);
            inventoryManager.addItem(item);

            serialInput.clear();
            nameInput.clear();
            valueInput.clear();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Not Valid");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void editItem() {

    }

    @FXML
    private void clearAllItems() {
        inventoryManager.clearItems();
    }

    @FXML
    private void saveInventory() {

    }

    @FXML
    private void loadInventory() {

    }

    @FXML
    private void searchItem() {

    }
}
