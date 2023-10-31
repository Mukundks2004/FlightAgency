package controller.Flights;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class ExploreFlightsController extends Controller<Flights>{
    @FXML private Button viewFlightsButton;
    @FXML private Button viewFlightsCountryButton;
    @FXML private Button addFlightButton;
    @FXML private Button removeFlightButton;
    @FXML private Button exitButton;

    @FXML private Label welcomeMessage;

    @FXML public void initialize() {
        welcomeMessage.setText("Hi " + model.getAgency().getLoggedInUser().getName() + ", welcome to the Flights section");
    }

    @FXML public void viewAllFlights() throws Exception{
        Stage flightsStage = new Stage();
        flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/DisplayFlightsView.fxml", "Display Flights", flightsStage);
    }
    @FXML public void viewFlightsCountry() throws Exception{
        Stage flightsStage = new Stage();
        flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", flightsStage);
    }
    @FXML public void addFlight() throws Exception{
        Stage flightsStage = new Stage();
        flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/AddFlightView.fxml", "Add Flight", flightsStage);
    }
    @FXML public void removeFlight() throws Exception{
        Stage flightsStage = new Stage();
        flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Flights/RemoveFlightView.fxml", "Remove Flight", flightsStage);
    }

    @FXML public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}
