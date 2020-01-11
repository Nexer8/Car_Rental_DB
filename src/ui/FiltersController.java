package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FiltersController {
    @FXML
    public TextField markField;
    @FXML
    public TextField userRatingField;
    @FXML
    public TextField manufactureField;
    @FXML
    public TextField numberOfSeatsField;
    @FXML
    public TextField productionYearField;

    static public String mark;
    static public String manufacture;
    static public String userRating;
    static public String numberOfSeats;
    static public String produtcionYear;

    public void okPressed(ActionEvent e) {
        mark = markField.getText();
        userRating = userRatingField.getText();
        manufacture = manufactureField.getText();
        numberOfSeats = numberOfSeatsField.getText();
        produtcionYear = productionYearField.getText();

        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void closePressed(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
