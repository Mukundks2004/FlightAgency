package controller.Destinations;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyDestinationsController extends Controller<Destinations>{
    @FXML private TextField nameField;
    @FXML private TextField counField;

    @FXML private Button exitButton;
    @FXML private Button addDestButton;
    @FXML private Button removeButton;

    public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        if (nameField != null && counField != null) {
            nameField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
            counField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        }

    }


    private void updateButtonState() {
        if (addDestButton != null) {
            addDestButton.setDisable(nameField.getText().isEmpty() || counField.getText().isEmpty());
        }
        else {
            removeButton.setDisable(nameField.getText().isEmpty() || counField.getText().isEmpty());
        }
        
    }

    @FXML
    public void handleRemoveDest() {
        try {
            if (model.hasDestination(nameField.getText(), counField.getText())) {
                Destination myHouse = model.destination(nameField.getText(), counField.getText());
                model.removeDestination(myHouse);
                handleClose();
            }
            else {
                ViewLoader.showErrorWindow(new ErrorModel(new ItemNotFoundException(), "destination does not exist"));
            }
        }
        catch (Exception e) {
            ViewLoader.showErrorWindow(new ErrorModel(e, "lol u messed up again"));
        }
    }

    @FXML 
    public void handleAddDest() {
        try {
            model.addDestination(new Destination(nameField.getText(), counField.getText()));
            handleClose();
        }
        catch (DuplicateItemException e) {
            ViewLoader.showErrorWindow(new ErrorModel(new DuplicateItemException(), "destination already does exist"));
        }

    }
}
