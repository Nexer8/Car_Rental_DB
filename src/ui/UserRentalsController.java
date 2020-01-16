package ui;

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

public class UserRentalsController implements Initializable {
    //TODO: change change column
    @FXML
    private TableView<Rental> availableCarsTable;
    @FXML private TableColumn<Rental, String> manufacturerColumn;
    @FXML private TableColumn<Rental, Timestamp> modelColumn;
    @FXML private TableColumn<Rental, Integer> numOfSeatsColumn;
    @FXML private TableColumn<Rental, Timestamp> numOfDoorsColumn;
    @FXML private TableColumn<Rental, Double> userRatingColumn;


    ObservableList<Rental> selectedRental;

    public ObservableList<Rental> getRental() {
        ObservableList<Rental> data = FXCollections.observableArrayList();
        if (MainController.foundRentals == null) return null;
        for (Rental frental : MainController.foundRentals) {
            data.add(frental);
        }
        return data;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("startLocationId"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("startRentalDate"));
        numOfSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("endLocationId"));
        numOfDoorsColumn.setCellValueFactory(new PropertyValueFactory<>("endRentalDate"));
        userRatingColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        availableCarsTable.setItems(getRental());

        availableCarsTable.setOnMouseClicked(e -> {
            selectedRental = availableCarsTable.getSelectionModel().getSelectedItems();
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

