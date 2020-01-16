package ui;

import com.car_rental.Car;
import com.car_rental.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AllRentalsController implements Initializable {
    //TODO: change change column
    @FXML private TableView<Rental> availableRentalsTable;
    @FXML private TableColumn<Rental, String> pickUpLoc;
    @FXML private TableColumn<Rental, Timestamp> pickUpDate;
    @FXML private TableColumn<Rental, Integer> dropOffLoc;
    @FXML private TableColumn<Rental, Timestamp> dropOffDate;
    @FXML private TableColumn<Rental, Double> price;

    ObservableList<Rental> selectedRental;

    public ObservableList<Rental> getRental() {
        ObservableList<Rental> data = FXCollections.observableArrayList();
        for (Rental frental : MainController.foundRentals) {
            data.add(frental);
        }
        return data;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pickUpLoc.setCellValueFactory(new PropertyValueFactory<>("startLocationId"));
        pickUpDate.setCellValueFactory(new PropertyValueFactory<>("startRentalDate"));
        dropOffLoc.setCellValueFactory(new PropertyValueFactory<>("endLocationId"));
        dropOffDate.setCellValueFactory(new PropertyValueFactory<>("endRentalDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("cost"));

        availableRentalsTable.setItems(getRental());

        availableRentalsTable.setOnMouseClicked(e -> {
            selectedRental = availableRentalsTable.getSelectionModel().getSelectedItems();
        });
    }

    public void editPressed() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/modifyRental.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Modify rental");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

}
