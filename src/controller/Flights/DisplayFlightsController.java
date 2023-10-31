package controller.Flights;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class DisplayFlightsController extends Controller<Flights>{
    @FXML private Button exitButton;
    @FXML private TextField countryFilter;
    @FXML private TableView<Flight> flightsTv;

    @FXML private void initialize() {
        flightsTv.setItems(model.getFlights());
        if (countryFilter != null) {
            countryFilter.textProperty().addListener((observable, oldS, newS) -> cutList(newS));
        }
    }

    private void cutList(String s) {
        ObservableList<Flight> allFlights = model.getFlights();
        ObservableList<Flight> newDests = FXCollections.<Flight>observableArrayList();
        for (Flight f : allFlights) {
            if (f.getTakeoff().toLowerCase().contains(s) || f.getLanding().toLowerCase().contains(s)) {
                newDests.add(f);
            }
        }
        flightsTv.setItems(newDests);
    }

    public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
