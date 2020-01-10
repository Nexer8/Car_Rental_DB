package ui;
import com.car_rental.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import java.io.IOException;
import java.util.List;
import HibernateFactory.*;
import com.car_rental.User;
import org.hibernate.service.ServiceRegistry;
import com.hibernateMethods.CrudMethods;

public class LoginController {
    @FXML private TextField login;
    @FXML private TextField password;
    CrudMethods methods = new CrudMethods();

    public void singInPressed(ActionEvent e) throws IOException {
        int rc = 0;
        Alert loginError = new Alert(Alert.AlertType.ERROR);
        loginError.setContentText("Incorrect data, try again!");

        Alert loginSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        loginSuccess.setContentText("You are now logged in!");


        if (login.getText().isEmpty() || password.getText().isEmpty()) {
            loginError.showAndWait();
            return;
        }

        if (methods.logIn(login.getText(), password.getText()) != 0) {
            loginError.showAndWait();
        }
        else {
            loginSuccess.showAndWait();
        }

    }
}
