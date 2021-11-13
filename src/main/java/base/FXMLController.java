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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private TextField serialInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField valueInput;
    @FXML
    private TextField searchInput;
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
    }

    public void onLoad(Stage stage){
        // Bind properties for scene width && height
    }

    @FXML
    private void addItem(){

    }

    @FXML
    private void editItem(){

    }

    @FXML
    private void clearAllItems(){

    }

    @FXML
    private void saveInventory(){

    }

    @FXML
    private void loadInventory(){

    }

    @FXML
    private void searchItem(){

    }
}
