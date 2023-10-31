package controller.Error;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.Exceptions.ErrorModel;

//HI HOW ARE YOU ARE YOU HAVING A GOOD DAY?
//I WANT TO PROGRAM BUT I CANT CONCENTRATE LOL I KEEP THINKING ABOUT THINGS :P
//I HOPE YOU FIND HAPPINESS!

public class ErrorController extends Controller<ErrorModel>{
    @FXML private Label errorName;
    @FXML private Label errorInfo;
    @FXML private Button closeButton;

    @FXML public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML public void initialize() {
        errorName.setText(model.getException().getClass().getSimpleName());
        errorInfo.setText(model.getMessage());
    }
}
