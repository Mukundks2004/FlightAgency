package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.application.Platform;
import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency>{
    
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML public void handleLogin() throws Exception{
        boolean incorrectLogin = true;
        for (Administrator admin : model.getAdministrators().getAdministrators()) {
            if (admin.getLogin().equals(usernameField.getText()) && admin.getPassword().equals(passwordField.getText())) {
                incorrectLogin = false;
                model.setLoggedInUser(admin);

                Stage agencyStage = new Stage();
                agencyStage.getIcons().add(new Image("/image/agency_icon.png"));
                ViewLoader.showStage(model, "/view/AgencyView.fxml", "Prog2 Travel Agency", agencyStage);

                Stage stageToClose = (Stage) usernameField.getScene().getWindow();
                stageToClose.close();
            }
        }
        if (incorrectLogin) {
            ViewLoader.showErrorWindow(new ErrorModel(new InvalidCredentialsException(), "bad credentials"));
        }
    }
    @FXML public void handleExit() {
        Platform.exit();
    }

    public final Agency getAgency() { return model; }
}
