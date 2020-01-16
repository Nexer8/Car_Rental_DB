package ui;

import com.car_rental.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;



public class AvailableCarsController implements Initializable {
    @FXML private TableView<Car> availableCarsTable;
    @FXML private TableColumn<Car, String> manufacturerColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, Integer> numOfSeatsColumn;
    @FXML private TableColumn<Car, Integer> numOfDoorsColumn;
    @FXML private TableColumn<Car, Double> userRatingColumn;

    MainController mainController;
    ObservableList<Car> selectedCar;

    public ObservableList<Car> getCar() {
        ObservableList<Car> data = FXCollections.observableArrayList();
        for (Car fcar : mainController.foundCars) {
            data.add(fcar);
        }
        return data;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        numOfSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        numOfDoorsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDoors"));
        userRatingColumn.setCellValueFactory(new PropertyValueFactory<>("userRating"));

        availableCarsTable.setItems(getCar());

        availableCarsTable.setOnMouseClicked(e -> {
            selectedCar = availableCarsTable.getSelectionModel().getSelectedItems();
        });
    }

    public void rentPressed(ActionEvent e) {

    }
}
