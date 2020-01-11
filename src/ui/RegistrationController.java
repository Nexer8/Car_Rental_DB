package ui;

import com.car_rental.Customer;
import com.car_rental.Location;
import com.car_rental.User;
import com.hibernateMethods.CrudMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;


import java.io.IOException;

public class RegistrationController {
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

    public void singUpPressed(ActionEvent e) throws IOException {
        User user = new User();
        Customer customer = new Customer();
        Location location = new Location();
        CrudMethods methods = new CrudMethods();
        Alert signUpError = new Alert(Alert.AlertType.ERROR);
        signUpError.setContentText("Incorrect data, try again!");
        int rc;

        Alert signUpSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        signUpSuccess.setContentText("You can now log in!");

        if (login.getText().isEmpty() || password.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty()
        || email.getText().isEmpty() || phone.getText().isEmpty() || bankAcc.getText().isEmpty()
        || city.getText().isEmpty() || postalCode.getText().isEmpty() || address.getText().isEmpty()) {
            signUpError.showAndWait();
            return;
        }
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setAdmin(false);
        user.setLoginStatus(false);
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setEmail(email.getText());
        customer.setPhoneNumber(phone.getText());
        customer.setBankAccountNumber(bankAcc.getText());
        customer.setDateOfBirth(Date.valueOf(dateOfBirth.getValue()));
//        customer.setCustomerId(user.getUserId());
        location.setCity(city.getText());
        location.setPostalCode(postalCode.getText());
        location.setStreetAddress(address.getText());

        rc = methods.signUp(user, customer, location);
        if (rc == 0) {
            signUpSuccess.showAndWait();
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else {
            signUpError.showAndWait();
        }
    }
}
