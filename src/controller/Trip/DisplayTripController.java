package controller.Trip;

import javafx.collections.ListChangeListener;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.util.InputMismatchException;

import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.ErrorModel;

public class DisplayTripController extends Controller<Trip>{
    
    @FXML private ListView<Itinery> thingsLv;
    @FXML private Button viewIndField;
    @FXML private Button exitButton;
    @FXML private TextField countryFilter;

    @FXML public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML public void viewInd() throws Exception{
        
        Destinations myDests = new Destinations(model.getAgency());
        Flights myF = new Flights(model.getAgency());
        try {
            for (Itinery i : thingsLv.getSelectionModel().getSelectedItems()) {
                if (i instanceof Destination) {
                    myDests.addDestination((Destination)i);
                } 
                else if (i instanceof Flight) {
                    myF.addFlight((Flight)i);
                }
            }
        }
        catch (Exception e) {
            ViewLoader.showErrorWindow(new ErrorModel(e, "failed to cast"));
        }
        if (myDests.getDestinations().size() > 0 && myF.getFlights().size() > 0) {
            ViewLoader.showErrorWindow(new ErrorModel(new InputMismatchException(), "cant show both"));
        }

        else if (myDests.getDestinations().size() > 0) {
            Stage destsStage = new Stage();
            destsStage.getIcons().add(new Image("/image/destinations_icon.png"));
            ViewLoader.showStage(myDests, "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", destsStage);
        }
        else if (myF.getFlights().size() > 0) {
            Stage destsStage = new Stage();
            destsStage.getIcons().add(new Image("/image/flights_icon.png"));
            ViewLoader.showStage(myF, "/view/Flights/DisplayFlightsView.fxml", "Display Flights", destsStage);
        }

       

    }

    @FXML public void initialize() {
        thingsLv.setItems(model.getItinery());
        thingsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        model.getDestinations().getDestinations().addListener(new ListChangeListener<Itinery>() {
            @Override
            public void onChanged(Change<? extends Itinery> change) {
                while (change.next()) {
                    if (change.wasAdded() || change.wasRemoved()) {
                        MksUpdate();
                        break;
                    }
                }
            }
        });

        model.getFlights().getFlights().addListener(new ListChangeListener<Itinery>() {
            @Override
            public void onChanged(Change<? extends Itinery> change) {
                while (change.next()) {
                    if (change.wasAdded() || change.wasRemoved()) {
                        MksUpdate();
                        break;
                    }
                }
            }
        });
    }

    public void MksUpdate() {
        thingsLv.setItems(model.getItinery());
    }
}
