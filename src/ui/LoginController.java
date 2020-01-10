package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.car_rental.User;
import com.hibernateMethods.CrudMethods;

public class LoginController {
    @FXML private TextField login;
    @FXML private TextField password;
    CrudMethods methods = new CrudMethods();
    User user = new User();

    public void singInPressed(ActionEvent e) throws IOException {
        Alert loginError = new Alert(Alert.AlertType.ERROR);
        loginError.setContentText("Incorrect data, try again!");

        Alert loginSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        loginSuccess.setContentText("You are now logged in!");


        if (login.getText().isEmpty() || password.getText().isEmpty()) {
            loginError.showAndWait();
            return;
        }

        if ((user = methods.logIn(login.getText(), password.getText())) == null) {
            loginError.showAndWait();
        }
        else {
            methods.userStatusUpdate(user, true);
            loginSuccess.showAndWait();
        }
    }
}
