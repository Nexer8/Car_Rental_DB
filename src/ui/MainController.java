package ui;

import com.car_rental.*;
import com.hibernateMethods.CrudMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MainController {
    @FXML private Button signUpButton;
    @FXML private Button signInButton;
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
    @FXML private MenuButton account;

    @FXML private Button editButton;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField bankAcc;
    @FXML private DatePicker dateOfBirth;
    @FXML private TextField city;
    @FXML private TextField postalCode;
    @FXML private TextField address;

    static String auxManufacturer;
    static String auxModel;
    static int auxNumberOfSeats;
    static int auxNumberOfDoors;
    static double auxUserRating;
    static List<Car> foundCars = new ArrayList<>();
    static List<Rental> foundRentals = new ArrayList<>();
    static Date auxDepartureDate;
    static Date auxDropOffDate;
    static int auxPickUpLocationId;
    static int auxDropOffLocationId;

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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/registration.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Sing Up");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void signInPressed(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Stage logInStage = new Stage();
        logInStage.setTitle("Sign In");
        logInStage.setResizable(false);
        logInStage.setScene(new Scene(root, 600, 600));
        logInStage.showAndWait();

        if (LoginController.user.isLoginStatus() && !LoginController.user.isAdmin()) {
            signUpButton.setVisible(false);
            signInButton.setVisible(false);
            account.setVisible(true);
        }
    }

    public void signOutPressed(ActionEvent e) throws IOException {
        int rc;
        Alert signOutSuccess = new Alert(Alert.AlertType.INFORMATION);
        signOutSuccess.setContentText("You have logged out!");
        Alert signOutError = new Alert(Alert.AlertType.ERROR);
        if (LoginController.user != null && LoginController.user.isLoginStatus()) {
            rc = methods.userStatusUpdate(LoginController.user, false);
            if (rc != 1) {
                signOutError.showAndWait();
            }
            else {
                LoginController.user.setLoginStatus(false);

                if (LoginController.user.isAdmin()) {
                    final Node source = (Node) e.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();

                    stage.close();
                }
                else {
                    signUpButton.setVisible(true);
                    signInButton.setVisible(true);
                    account.setVisible(false);
                }
                signOutSuccess.showAndWait();
            }
        }
        else {
            signOutError.setContentText("You have to sign in first!");
            signOutError.showAndWait();
        }
    }

    public void editAccountPressed(ActionEvent e) throws IOException {
        Alert signOutError = new Alert(Alert.AlertType.ERROR);
        if (LoginController.user != null && LoginController.user.isLoginStatus()) {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/editAccount.fxml"));
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Edit account");
            registrationStage.setResizable(false);
            registrationStage.setScene(new Scene(root, 600, 600));
            registrationStage.show();
        }
        else {
            signOutError.setContentText("You have to sign in first!");
            signOutError.showAndWait();
        }
    }

    public void editButtonPressed(ActionEvent e) throws IOException {
        User user = new User();
        Customer customer = new Customer();
        Location location = new Location();
        CrudMethods methods = new CrudMethods();
        Alert dataEditError = new Alert(Alert.AlertType.ERROR);
        dataEditError.setContentText("Incorrect data, try again!");

        String phoneRegex = "^\\d{9}$";
        String bankAccRegex = "^\\d{32}$";
        String textRegex = "[a-zA-Z]+";
        String emailRegex = "^(.+)@(.+)$";
        String postalCodeRegex = "^[0-9]{5}(?:-[0-9]{4})?$";

        Alert dataEditSuccess = new Alert(Alert.AlertType.INFORMATION);
        dataEditSuccess.setContentText("Account data edited correctly!");

        if (login.getText().isEmpty() && !login.getText().matches(textRegex) && password.getText().isEmpty()
                && firstName.getText().isEmpty() && !firstName.getText().matches(textRegex)
                && lastName.getText().isEmpty() && !lastName.getText().matches(textRegex)
                && email.getText().isEmpty() && !email.getText().matches(emailRegex)
                && phone.getText().isEmpty() && !phone.getText().matches(phoneRegex)
                && bankAcc.getText().isEmpty() && !bankAcc.getText().matches(bankAccRegex)
                && dateOfBirth.getValue() == null
                && city.getText().isEmpty() && !city.getText().matches(textRegex)
                && postalCode.getText().isEmpty() && !postalCode.getText().matches(postalCodeRegex)
                && address.getText().isEmpty()) {
            dataEditError.showAndWait();
            return;
        }
        user.setUserId(LoginController.user.getUserId());
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setAdmin(false);
        user.setLoginStatus(false);

        customer.setCustomerId(LoginController.user.getUserId());
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setEmail(email.getText());
        customer.setPhoneNumber(phone.getText());
        customer.setBankAccountNumber(bankAcc.getText());
        if (dateOfBirth.getValue() == null) customer.setDateOfBirth(null);
        else {
            customer.setDateOfBirth(Date.valueOf(dateOfBirth.getValue()));
        }

        int lrc = -1;
        if (!city.getText().isEmpty() && city.getText().matches(textRegex)
                && !postalCode.getText().isEmpty() && postalCode.getText().matches(postalCodeRegex)
                && !address.getText().isEmpty()) {
            location.setCity(city.getText());
            location.setPostalCode(postalCode.getText());
            location.setStreetAddress(address.getText());
            lrc = methods.createLocation(location);
        }

        customer.setLocationId(methods.getLocationIdFromCity(city.getText()));

        int urc = methods.userDataUpdate(user);
        int crc = methods.customerDataUpdate(customer);

        if (urc == 1 || crc == 1 || lrc == 0) {
            dataEditSuccess.showAndWait();
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else {
            dataEditError.showAndWait();
        }
    }

    public void myRentalsPressed(ActionEvent e) throws IOException {
        Alert signOutError = new Alert(Alert.AlertType.ERROR);
        Alert noRentalsFoundError = new Alert(Alert.AlertType.ERROR);
        if (LoginController.user != null && LoginController.user.isLoginStatus()) {

            foundRentals = methods.getRentalsFromUser(LoginController.user);

            if (foundRentals == null) {
                noRentalsFoundError.showAndWait();
                return;
            }

            Parent root = FXMLLoader.load(getClass().getResource("fxml/rentalsForUser.fxml"));
            Stage registrationStage = new Stage();
            registrationStage.setTitle("See your rentals");
            registrationStage.setResizable(false);
            registrationStage.setScene(new Scene(root, 600, 600));
            registrationStage.show();
        }
        else {
            signOutError.setContentText("You have to sign in first!");
            signOutError.showAndWait();
        }
    }

    public void showAllCarsPressed(ActionEvent e) throws IOException {
        foundCars = methods.getCars();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/allCars.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("All cars");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void showAllRentalsPressed(ActionEvent e) throws IOException {
        foundRentals = methods.getRentals();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/allRentals.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("All rentals");
        registrationStage.setResizable(false);
        registrationStage.setScene(new Scene(root, 600, 600));
        registrationStage.show();
    }

    public void addFilters(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/filters.fxml"));
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Set filters");
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


            auxDepartureDate = Date.valueOf(departureDate.getValue());
            auxDropOffDate = Date.valueOf(dropOffDate.getValue());

            rental.setStartRentalDate(new Timestamp(Date.valueOf(departureDate.getValue()).getTime()));
            rental.setEndRentalDate(new Timestamp(Date.valueOf(dropOffDate.getValue()).getTime()));

            pickUpLocId = methods.getLocationIdFromCity(pickUpLocation.getText());
            dropOffLocId = methods.getLocationIdFromCity(dropOffLocation.getText());

            auxPickUpLocationId = pickUpLocId;
            auxDropOffLocationId = dropOffLocId;

            if (pickUpLocId == -1 || dropOffLocId == -1) {
                submitError.showAndWait();
                return;
            }

            car.setLocationId(pickUpLocId);
            car.setManufacturer(auxManufacturer);
            car.setModel(auxModel);
            car.setNumberOfSeats(auxNumberOfSeats);
            car.setNumberOfDoors(auxNumberOfDoors);
            car.setUserRating(auxUserRating);
            car.setArchived(false);

            foundCars = methods.searchForCars(car, pickUpLoc, rental);

            Parent root = FXMLLoader.load(getClass().getResource("fxml/carsForUser.fxml"));
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Available cars");
            registrationStage.setResizable(false);
            registrationStage.setScene(new Scene(root, 600, 600));
            registrationStage.showAndWait();
        }
    }
}
