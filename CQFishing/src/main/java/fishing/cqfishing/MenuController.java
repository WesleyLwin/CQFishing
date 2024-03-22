package fishing.cqfishing;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class MenuController implements Initializable {

    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private Button btnSearchByName;
    
    @FXML
    private Button btnSearchByPhone;
    
    @FXML
    private Button btnClear;
    
    @FXML
    private Button btnExit;
    
    @FXML
    private MenuButton SortByName;
    
    @FXML
    private MenuItem sortAtoZ;
    
    @FXML
    private MenuItem sortZtoA;
    
    @FXML
    private MenuButton SortByDate;
    
    @FXML
    private MenuItem earliest;
    
    @FXML
    private MenuItem latest;
    
    @FXML
    private Button btnSortByDate;
    
    @FXML
    private ListView<String> listViewFishers;
    
    private Fisher [] fisher;
    
    private DataHandler data;
    
    private boolean isListCleared = false;
    
    private void clearList(){
        ObservableList<String> elements = listViewFishers.getItems();
        elements.clear();
    }
    
    @FXML
    void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit? Are you sure?"); 
        alert.showAndWait().ifPresent(response -> { 
            if (response == ButtonType.OK) { 
            Platform.exit(); 
        } 
        }); 
    }
    
    //Method to display fisher list in the list view
    private void displayFishers(){
            fisher = data.getAllFisher();
            
            ObservableList<String> elements = FXCollections.observableArrayList();
            
            for(Fisher fish: fisher){
                    elements.add(fish.getID() + ", " + fish.getName() + ", " + fish.getPhone() + ", " + fish.getDate() + ", " + fish.getWeight());
            }
            listViewFishers.setItems(elements);

    }
    
    private void displayFishersNames(){
            fisher = data.getAllFisher();
            
            ObservableList<String> elements = FXCollections.observableArrayList();
            
            for(Fisher fish: fisher){
                    elements.add(fish.getName() + ", " + fish.getID() + ", " + fish.getPhone() + ", " + fish.getDate() + ", " + fish.getWeight());
            }
            listViewFishers.setItems(elements);

    }
    
    private void displayFishersDates(){
        
            fisher = data.getAllFisher();
            
            ObservableList<String> elements = FXCollections.observableArrayList();
            
            for(Fisher fish: fisher){
                    elements.add(fish.getDate() + ", " + fish.getName() + ", " + fish.getID() + ", " + fish.getPhone() + ", " + fish.getWeight());
            }
            listViewFishers.setItems(elements);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = App.getDataHandler();
        displayFishers();
    }  
    
    @FXML
    private void clearButtonAction(ActionEvent event){
        
        if(isListCleared){
            displayFishers();
            btnClear.setText("Clear");
        }
        
        else {
            clearList();
            btnClear.setText("Display");
        }
        
        isListCleared = !isListCleared;
    }
    
    @FXML
    private void handleSortByNameAscendingAction (ActionEvent event) throws Exception{
        data.sortByNameAscending();
        displayFishersNames();
    }
    
    @FXML
    private void handleSortByNameDescendingAction (ActionEvent event) throws Exception{
        data.sortByNameDescending();
        displayFishersNames();
    }
    
    @FXML
    private void handleSortByDateEarliest (ActionEvent event) throws Exception{
        data.sortByDateAscending();
        displayFishersDates();
    }
    
    @FXML
    private void handleSortByDateLatest (ActionEvent event) throws Exception{
        data.sortByDateDescending();
        displayFishersDates();
    }
    
    @FXML
    private void handleSearchByName (ActionEvent event) throws Exception{
        String name = txtName.getText().trim();
        if (!name.isEmpty()) {
            Collection<Fisher> fishers = data.searchByName(name);
            if (fisher != null) {
                displayResults(fishers);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Search Result", "No fisher found with the name: " + name);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Search", "Please enter a name to search.");
        }
        
        txtName.clear();
    }
    
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void displayResults(Collection<Fisher> fishers){
        ObservableList<String> elements = FXCollections.observableArrayList();
        
        for(Fisher fisher: fishers){
            elements.add(fisher.getName()+ ", " + fisher.getID() + ", " + fisher.getPhone() + ", " + fisher.getDate() + ", " + fisher.getWeight());
        }
        
        listViewFishers.setItems(elements);
    }
    
    @FXML
    private void handleSearchByPhone (ActionEvent event) throws Exception{
        String phone = txtPhone.getText().trim();
        if (!phone.isEmpty()) {
            Collection<Fisher> fishers = data.searchByPhone(phone);
            if (fisher != null) {
                displayResults(fishers);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Search Result", "No fisher found with the name: " + phone);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Search", "Please enter a name to search.");
        }
        
        txtPhone.clear();
    }
    
}
