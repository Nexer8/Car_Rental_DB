package ui;

import com.car_rental.Rental;
import com.hibernateMethods.CrudMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class RentalEditionController {
    @FXML
    TextField cost;
    @FXML TextField pickUpLocation;
    @FXML DatePicker departureDate;
    @FXML TextField dropOffLocation;
    @FXML DatePicker dropOffDate;

    public void submitButtonPressed(ActionEvent e) throws IOException {
        Rental rental = new Rental();
        CrudMethods methods = new CrudMethods();

        Alert dataEditError = new Alert(Alert.AlertType.ERROR);
        dataEditError.setContentText("Incorrect data, try again!");

        String textRegex = "[a-zA-Z]+";
        String valueRegex = "[0-9]+([,.][0-9]{0,2})";

        Alert dataEditSuccess = new Alert(Alert.AlertType.INFORMATION);
        dataEditSuccess.setContentText("Rental data edited correctly!");

        if (cost.getText().isEmpty() && !cost.getText().matches(valueRegex)
                && pickUpLocation.getText().isEmpty() && !pickUpLocation.getText().matches(textRegex)
                && departureDate.getValue() == null
                && dropOffLocation.getText().isEmpty() && !dropOffLocation.getText().matches(textRegex)
                && dropOffDate.getValue() == null) {
            dataEditError.showAndWait();
            return;
        }

        if (!cost.getText().isEmpty() || !cost.getText().matches(valueRegex)) {
            rental.setCost(Double.valueOf(cost.getText()));
        } else {
            rental.setCost(-1);
        }
        rental.setStartLocationId(methods.getLocationIdFromCity(pickUpLocation.getText()));
        if (departureDate.getValue() != null) {
            rental.setStartRentalDate(new Timestamp(Date.valueOf(departureDate.getValue()).getTime()));
        } else {
            rental.setStartRentalDate(null);
        }
        rental.setEndLocationId(methods.getLocationIdFromCity(dropOffLocation.getText()));
        if (dropOffDate.getValue() != null) {
            rental.setEndRentalDate(new Timestamp(Date.valueOf(dropOffDate.getValue()).getTime()));
        } else {
            rental.setEndRentalDate(null);
        }
        rental.setRentalId(AllRentalsController.selectedRental.getRentalId());

        int rc = methods.rentalDataUpdate(rental);

        if (rc == 1) {
            dataEditSuccess.showAndWait();
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else {
            dataEditError.showAndWait();
        }
    }
}
