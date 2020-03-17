package ui;

import com.car_rental.Car;
import com.hibernateMethods.CrudMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AllCarsController implements Initializable {
    @FXML
    private TableView<Car> availableCarsTable;
    @FXML private TableColumn<Car, String> manufacturerColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, Integer> numOfSeatsColumn;
    @FXML private TableColumn<Car, Integer> numOfDoorsColumn;
    @FXML private TableColumn<Car, Double> userRatingColumn;

    MainController mainController;
    static Car selectedCar;

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
            selectedCar = availableCarsTable.getSelectionModel().getSelectedItem();
        });
    }

    public void editPressed(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/modifyCar.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Modify car");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }
 }
