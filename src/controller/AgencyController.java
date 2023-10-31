package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class AgencyController extends Controller<Agency>{

    @FXML private Button flightButton;
    @FXML private Button destinationButton;
    @FXML private Button tripButton;
    @FXML private Button exitButton;

    @FXML private Label welcomeMessage;

    @FXML public void initialize() {
        welcomeMessage.setText("Hi " + model.getLoggedInUser().getName() + ", welcome to the Prog2 Travel Agency");
    }

    @FXML public void exploreFlights() throws Exception{
        Stage flightsStage = new Stage();
        flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model.getFlights(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", flightsStage);
    }
    @FXML public void exploreDestinations() throws Exception{
        Stage destStage = new Stage();
        destStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", destStage);
    }
    @FXML public void bookTrip() throws Exception{
        Stage tripStage = new Stage();
        tripStage.getIcons().add(new Image("/image/trip_icon.png"));
        ViewLoader.showStage(new Trip(model), "/view/Trip/BookTripView.fxml", "Book a Trip", tripStage);
    }
    @FXML public void handleExit() {Platform.exit();}

}
