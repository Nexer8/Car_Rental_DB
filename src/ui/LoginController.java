package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.car_rental.User;
import com.hibernateMethods.CrudMethods;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField login;
    @FXML private TextField password;
    CrudMethods methods = new CrudMethods();
    User user = new User();
    int rc;

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
            rc = methods.userStatusUpdate(user, true);
            if (rc == -1) loginError.showAndWait();
            else {
                loginSuccess.showAndWait();
            }
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
