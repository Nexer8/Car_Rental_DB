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
    @FXML private TextField manufacture;
    @FXML private TextField model;
    @FXML private TextField numberOfSeats;
    @FXML private TextField numberOfDoors;
    @FXML private TextField userRating;

    CrudMethods methods = new CrudMethods();

    public void okPressed(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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

            car.setManufacturer(manufacture.getText());
            car.setModel(model.getText());
            car.setNumberOfSeats(Integer.parseInt(numberOfSeats.getText()));
            car.setNumberOfDoors(Integer.parseInt(numberOfDoors.getText()));
            car.setUserRating(Double.valueOf(userRating.getText()));
            car.setArchived(false);

            // TODO: Wywołaj nowe okno i wywołaj funkcję, która wyświetli dane samochodów pobrane w funkcji poniżej
            List<Car> cars = methods.searchForCars(car, pickUpLoc, rental);
        }
    }
}
