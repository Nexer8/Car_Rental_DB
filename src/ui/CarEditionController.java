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

import java.io.IOException;

public class CarEditionController {

    @FXML private TextField manufacturerField;
    @FXML private TextField modelField;
    @FXML private TextField classField;
    @FXML private TextField numberOfSeatsField;
    @FXML private TextField numberOfDoorsField;
    @FXML private TextField dailyRentalCostField;
    @FXML private TextField trunkCapacityField;
    @FXML private TextField productionYearField;
    @FXML private TextField colorField;
    @FXML private TextField powerField;
    @FXML private TextField transmissionField;
    @FXML private CheckBox archivedCheckBox;

    public void submitButtonPressed(ActionEvent e) throws IOException {
        Car car = new Car();
        CrudMethods methods = new CrudMethods();

        Alert dataEditError = new Alert(Alert.AlertType.ERROR);
        dataEditError.setContentText("Incorrect data, try again!");

        String textRegex = "[a-zA-Z]+";
        String classRegex = "[A-F]";
        String numOfRegex = "[2-7]+";
        String valueRegex = "[0-9]+([,.][0-9]{0,2})";
        String yearRegex = "\\d{4}";

        Alert dataEditSuccess = new Alert(Alert.AlertType.INFORMATION);
        dataEditSuccess.setContentText("Car data edited correctly!");

        if (manufacturerField.getText().isEmpty() && !manufacturerField.getText().matches(textRegex)
                && modelField.getText().isEmpty()
                && classField.getText().isEmpty() && !classField.getText().matches(classRegex)
                && numberOfSeatsField.getText().isEmpty() && !numberOfSeatsField.getText().matches(numOfRegex)
                && numberOfDoorsField.getText().isEmpty() && !numberOfDoorsField.getText().matches(numOfRegex)
                && trunkCapacityField.getText().isEmpty() && !trunkCapacityField.getText().matches(valueRegex)
                && dailyRentalCostField.getText().isEmpty() && !dailyRentalCostField.getText().matches(valueRegex)
                && productionYearField.getText().isEmpty() && !productionYearField.getText().matches(yearRegex)
                && colorField.getText().isEmpty() && !colorField.getText().matches(textRegex)
                && powerField.getText().isEmpty() && !powerField.getText().matches(valueRegex)
                && transmissionField.getText().isEmpty() && !transmissionField.getText().matches(textRegex)) {
            dataEditError.showAndWait();
            return;
        }

        car.setManufacturer(manufacturerField.getText());
        car.setModel(modelField.getText());
        car.setClazz(classField.getText());
        if (!numberOfSeatsField.getText().isEmpty() && numberOfSeatsField.getText().matches(numOfRegex)) {
            car.setNumberOfSeats(Integer.valueOf(numberOfSeatsField.getText()));
        } else {
            car.setNumberOfSeats(-1);
        }
        if (!numberOfDoorsField.getText().isEmpty() && numberOfDoorsField.getText().matches(numOfRegex)) {
            car.setNumberOfDoors(Integer.valueOf(numberOfDoorsField.getText()));
        } else {
            car.setNumberOfDoors(-1);
        }
        if (!trunkCapacityField.getText().isEmpty() && trunkCapacityField.getText().matches(valueRegex)) {
            car.setTrunkCapacity(Double.valueOf(trunkCapacityField.getText()));
        } else {
            car.setTrunkCapacity(-1);
        }
        if (!dailyRentalCostField.getText().isEmpty() && dailyRentalCostField.getText().matches(valueRegex)) {
            car.setDailyRentalCost(Double.valueOf(dailyRentalCostField.getText()));
        } else {
            car.setDailyRentalCost(-1);
        }
        if (!productionYearField.getText().isEmpty() && productionYearField.getText().matches(yearRegex)) {
            car.setProductionYear(Integer.valueOf(productionYearField.getText()));
        } else {
            car.setProductionYear(-1);
        }
        car.setColor(colorField.getText());
        if (!powerField.getText().isEmpty() && powerField.getText().matches(valueRegex)) {
            car.setPower(Double.valueOf(powerField.getText()));
        } else {
            car.setPower(-1);
        }
        car.setTransmission(transmissionField.getText());
        car.setArchived(archivedCheckBox.isSelected());
        car.setCarId(AllCarsController.selectedCar.getCarId());

        int rc = methods.carDataUpdate(car);

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
