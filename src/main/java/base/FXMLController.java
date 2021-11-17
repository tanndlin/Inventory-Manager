/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package base;


import inventory.Inventory;
import inventory.Item;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import util.ItemValidator;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    private TableColumn<Item, Double> valueColumn;

    private InventoryManager inventoryManager;
    FilteredList<Item> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public void onLoad(Stage stage) {
        // Bind properties for scene width && height
        Scene scene = stage.getScene();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

        serialColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));
        nameColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));
        valueColumn.prefWidthProperty().bind(scene.widthProperty().divide(3));

        inventoryManager = new InventoryManager(new Inventory(), scene);

        filteredList = new FilteredList<>(inventoryManager.getInventory().getItems());
        table.setItems(inventoryManager.getInventory().getItems());
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
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        serialInput.setText(selectedItem.getSerialNumber());
        nameInput.setText(selectedItem.getName());
        valueInput.setText(selectedItem.getValue() + "");

        inventoryManager.removeItem(selectedItem);
    }

    @FXML
    private void clearAllItems() {
        inventoryManager.clearItems();
    }

    @FXML
    private void saveInventory() {
        inventoryManager.save();
    }

    @FXML
    private void loadInventory() {
        inventoryManager.load();
    }

    @FXML
    private void searchItem() {
        if (searchInput.getText().length() == 0) {
            table.setItems(inventoryManager.getInventory().getItems());
            return;
        }

        filteredList.setPredicate(item -> item.matches(searchInput.getText()));

        // Wrap filtered list in sorted list to make it sortable
        SortedList<Item> sorted = new SortedList<>(filteredList);
        table.setItems(sorted);
        sorted.comparatorProperty().bind(table.comparatorProperty());
    }
}
