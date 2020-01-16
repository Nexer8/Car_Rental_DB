package ui;

import com.car_rental.Car;
import com.car_rental.Rental;
import com.hibernateMethods.CrudMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;



public class AvailableCarsController implements Initializable {
    @FXML private TableView<Car> availableCarsTable;
    @FXML private TableColumn<Car, String> manufacturerColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, Integer> numOfSeatsColumn;
    @FXML private TableColumn<Car, Integer> numOfDoorsColumn;
    @FXML private TableColumn<Car, Double> userRatingColumn;

    ObservableList<Car> selectedCar;
    CrudMethods methods = new CrudMethods();

    public ObservableList<Car> getCar() {
        ObservableList<Car> data = FXCollections.observableArrayList();
        for (Car fcar : MainController.foundCars) {
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
        Alert okError = new Alert(Alert.AlertType.ERROR);
        okError.setContentText("You have to log in first");
        int rc;

        Alert rentalError = new Alert(Alert.AlertType.ERROR);
        rentalError.setContentText("Could not create a new rental");

        Alert rentalSucces = new Alert(Alert.AlertType.INFORMATION);
        rentalSucces.setContentText("You have successfully rented a car!");
        if (!LoginController.user.isLoginStatus()) {
            okError.showAndWait();
            return;
        }

        Rental rental = new Rental();
        rental.setCarId(selectedCar.get(0).getCarId());
        rental.setEndRentalDate(new Timestamp(MainController.auxDropOffDate.getTime()));
        rental.setStartRentalDate(new Timestamp(MainController.auxDepartureDate.getTime()));
        rental.setCustomerId(LoginController.user.getUserId());
        rental.setStartLocationId(MainController.auxPickUpLocationId);
        rental.setEndLocationId(MainController.auxDropOffLocationId);
        rental.setCost(selectedCar.get(0).getDailyRentalCost());

        rc = methods.createRental(rental);
        if (rc == 0) {
            rentalSucces.showAndWait();
        }
        else {
            rentalError.showAndWait();
        }

    }
}
