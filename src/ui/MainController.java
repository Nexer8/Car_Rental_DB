package ui;

import com.car_rental.Car;
import com.car_rental.Location;
import com.car_rental.Rental;
import com.hibernateMethods.CrudMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MainController {
    @FXML private Button singUpButton;
    @FXML private Button singInButton;
    @FXML private Button submit;
    @FXML private DatePicker departureDate;
    @FXML private DatePicker dropOffDate;
    @FXML private TextField pickUpLocation;
    @FXML private TextField dropOffLocation;
    @FXML private TextField manufacturer;
    @FXML private TextField model;
    @FXML private TextField numberOfSeats;
    @FXML private TextField numberOfDoors;
    @FXML private TextField userRating;

    String auxManufacturer;
    String auxModel;
    int auxNumberOfSeats;
    int auxNumberOfDoors;
    double auxUserRating;

    CrudMethods methods = new CrudMethods();

    public void okPressed(ActionEvent e) {
        String sdNumberRegex = "[0-9]+";
        String userRatingRegex = "[0-5]+";
        String manufacturerRegex = "[a-zA-Z]+";

        Alert okError = new Alert(Alert.AlertType.ERROR);
        okError.setContentText("Incorrect data, try again!");

        if (!manufacturer.getText().isEmpty() && !manufacturer.getText().matches(manufacturerRegex) ||
                !numberOfSeats.getText().isEmpty() && !numberOfSeats.getText().matches(sdNumberRegex) ||
                !numberOfDoors.getText().isEmpty() && !numberOfDoors.getText().matches(sdNumberRegex) ||
                !userRating.getText().isEmpty() && !userRating.getText().matches(userRatingRegex)) {
            okError.showAndWait();
        }

        else {
            if (!manufacturer.getText().isEmpty() && manufacturer.getText().matches(manufacturerRegex)) auxManufacturer = manufacturer.getText();
            if (!model.getText().isEmpty()) auxModel = model.getText();
            if (!numberOfSeats.getText().isEmpty() && numberOfSeats.getText().matches(sdNumberRegex)) auxNumberOfSeats = Integer.parseInt(numberOfSeats.getText());
            if (!numberOfDoors.getText().isEmpty() && numberOfDoors.getText().matches(sdNumberRegex)) auxNumberOfDoors = Integer.parseInt(numberOfDoors.getText());
            if (!userRating.getText().isEmpty() && userRating.getText().matches(userRatingRegex)) auxUserRating = Double.valueOf(userRating.getText());

            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    public void closePressed(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void singUpPressed(ActionEvent e) throws IOException {
        System.out.println("Rejestracja");

        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Rejestracja");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void singInPressed(ActionEvent e) throws IOException {
        System.out.println("Logowanie");

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Login");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void addFilters(ActionEvent e) throws IOException {
        System.out.println("Podaj filtry");

        Parent root = FXMLLoader.load(getClass().getResource("filters.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Podaj filtry");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void submitPressed(ActionEvent e) throws IOException {
        Car car = new Car();
        Location pickUpLoc = new Location();
        Location dropOffLoc = new Location();
        Rental rental = new Rental();
        int pickUpLocId, dropOffLocId;

        Alert submitError = new Alert(Alert.AlertType.ERROR);
        submitError.setContentText("Incorrect data, try again!");
        if (pickUpLocation.getText().isEmpty() || dropOffLocation.getText().isEmpty() || departureDate.getValue() == null || dropOffDate == null) {
            submitError.showAndWait();
        }

        else {
            pickUpLoc.setCity(pickUpLocation.getText());
            dropOffLoc.setCity(dropOffLocation.getText());

            rental.setStartRentalDate(new Timestamp(Date.valueOf(departureDate.getValue()).getTime()));
            rental.setEndRentalDate(new Timestamp(Date.valueOf(dropOffDate.getValue()).getTime()));

            pickUpLocId = methods.getLocationIdFromCity(pickUpLocation.getText());
            dropOffLocId = methods.getLocationIdFromCity(dropOffLocation.getText());

            if (pickUpLocId == -1 || dropOffLocId == -1) submitError.showAndWait();

            car.setLocationId(pickUpLocId);
            car.setManufacturer(auxManufacturer);
            car.setModel(auxModel);
            car.setNumberOfSeats(auxNumberOfSeats);
            car.setNumberOfDoors(auxNumberOfDoors);
            car.setUserRating(auxUserRating);
            car.setArchived(false);

            // TODO: Wywołaj nowe okno i wywołaj funkcję, która wyświetli dane samochodów pobrane w funkcji poniżej
            List<Car> cars = methods.searchForCars(car, pickUpLoc, rental);
        }
    }
}
