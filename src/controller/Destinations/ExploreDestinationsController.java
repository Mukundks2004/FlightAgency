package controller.Destinations;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class ExploreDestinationsController extends Controller<Destinations>{

    @FXML private Button viewDestinationsButton;
    @FXML private Button viewFilteredDestinationsButton;
    @FXML private Button addDestinationButton;
    @FXML private Button removeDestinationButton;
    @FXML private Button exitButton;

    @FXML private Label welcomeMessage;

    @FXML public void initialize() {
        welcomeMessage.setText("Hi " + model.getAgency().getLoggedInUser().getName() + ", welcome to the Destinations section");
    }

    @FXML public void viewAllDestinations() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", destsStage);
    }
    @FXML public void viewFilteredDestinations() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", destsStage);
    }
    @FXML public void addDestination() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/AddDestinationView.fxml", "Add Destination", destsStage);
    }
    @FXML public void removeDestination() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model, "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", destsStage);
    }

    @FXML public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
   
}
