package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML private Button singUpButton;
    @FXML private Button singInButton;

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

}
