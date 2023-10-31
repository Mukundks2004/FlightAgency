package controller.Trip;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;


public class BookTripController extends Controller<Trip>{

    @FXML private Button addDestBtn;
    @FXML private Button removeDestBtn;
    @FXML private Button addConFlightsBtn;
    @FXML private Button viewTripBtn;
    @FXML private Button closeTripBtn;

    @FXML private Label welcomeMessage;

    @FXML public void initialize() {
        welcomeMessage.setText("Hi " + model.getAgency().getLoggedInUser().getName() + ", welcome to the Trip section");
    }

    @FXML public void addDest() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/trip_icon.png"));
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination to Trip", destsStage);
    }

    @FXML public void removeDest() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/trip_icon.png"));
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination from Trip", destsStage);
    }
    
    @FXML public void addConFlights() throws Exception{
        try {
            model.addConnectingFlights();
        }
        catch (DuplicateItemException e) {
            ViewLoader.showErrorWindow(new ErrorModel(new DuplicateItemException(), "duplicate dests"));
        }
        catch (InsufficientDestinationsException e) {
            ViewLoader.showErrorWindow(new ErrorModel(new InsufficientDestinationsException(), "not enough dests"));
        }    
    }

    @FXML public void viewTrip() throws Exception{
        Stage destsStage = new Stage();
        destsStage.getIcons().add(new Image("/image/trip_icon.png"));
        ViewLoader.showStage(model, "/view/Trip/DisplayTripView.fxml", "Display Trip", destsStage);
    }

    @FXML public void handleClose() {
        Stage stage = (Stage) closeTripBtn.getScene().getWindow();
        stage.close();
    }

}
