package controller.Flights;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Flights>{
    @FXML private TextField airlineField;
    @FXML private TextField fnumberField;
    @FXML private TextField takeoffField;
    @FXML private TextField landingField;
    @FXML private TextField costField;

    @FXML private Button exitButton;
    @FXML private Button addButton;
    @FXML private Button removeButton;

    public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        if (airlineField != null && fnumberField != null && takeoffField != null && landingField != null && costField != null) {
            airlineField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
            fnumberField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
            takeoffField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
            landingField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
            costField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        }
        if (takeoffField != null && landingField != null && costField == null) {
            takeoffField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState2());
            landingField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState2());
        }
    }

    private void updateButtonState() {
        addButton.setDisable(airlineField.getText().isEmpty() || fnumberField.getText().isEmpty() || takeoffField.getText().isEmpty() || landingField.getText().isEmpty() || costField.getText().isEmpty());
    }

    private void updateButtonState2() {
        removeButton.setDisable(takeoffField.getText().isEmpty() || landingField.getText().isEmpty());
    }

    @FXML
    public void handleRemoveFlight() {
        try {
            if (model.hasFlight(takeoffField.getText(), landingField.getText())) {
                Flight myFlight = model.getFlight(takeoffField.getText(), landingField.getText());
                model.removeFlight(myFlight);
                handleClose();
            }
            else {
                ViewLoader.showErrorWindow(new ErrorModel(new ItemNotFoundException(), "flight does not exist"));
            }
        }
        catch (Exception e) {
            ViewLoader.showErrorWindow(new ErrorModel(e, "lol u messed up"));
        }
    }

    public void handleNewFlight() {
        boolean success = true;
        try {
            Double.parseDouble(costField.getText());
        }
        catch (NumberFormatException e) {
            success = false;
            ViewLoader.showErrorWindow(new ErrorModel(new NumberFormatException(), "bad cost"));
        }
        try {
            Integer.parseInt(fnumberField.getText());
        }
        catch (NumberFormatException e) {
            success = false;
            ViewLoader.showErrorWindow(new ErrorModel(new NumberFormatException(), "bad flight number"));
        }
        if (success) {
            try {
                model.addFlight(new Flight(airlineField.getText(), Integer.parseInt(fnumberField.getText()), takeoffField.getText(), landingField.getText(), Double.parseDouble(costField.getText())));
            }
            catch (DuplicateItemException e) {
                success = false;
                ViewLoader.showErrorWindow(new ErrorModel(new DuplicateItemException(), "flight alr exists"));
            }
            
        }

        if (success) {
            handleClose();
        }
    }
}
