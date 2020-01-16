package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.car_rental.User;
import com.hibernateMethods.CrudMethods;
import javafx.stage.Stage;
import org.jboss.jandex.Main;

public class LoginController {
    @FXML private TextField login;
    @FXML private TextField password;
    CrudMethods methods = new CrudMethods();
    public static User user = new User();

    int rc;

    public void singInPressed(ActionEvent e) throws IOException {
        Alert loginError = new Alert(Alert.AlertType.ERROR);
        loginError.setContentText("Incorrect data, try again!");

        Alert loginSuccess = new Alert(Alert.AlertType.INFORMATION);
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
            user.setLoginStatus(true);
            if (rc == -1) loginError.showAndWait();
            else {
                if (user.isAdmin()) {
                    Parent root = FXMLLoader.load(getClass().getResource("fxml/mainAdmin.fxml"));
                    Stage registrationStage = new Stage();
                    registrationStage.setTitle("See your rentals");
                    registrationStage.setResizable(false);
                    registrationStage.setScene(new Scene(root, 600, 600));
                    registrationStage.show();
                } else {
                    loginSuccess.showAndWait();
                }
            }
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
