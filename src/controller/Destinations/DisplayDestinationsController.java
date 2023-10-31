package controller.Destinations;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class DisplayDestinationsController extends Controller<Destinations>{
    
    @FXML private Button exitButton;
    @FXML private TextField countryFilter;
    @FXML private TableView<Destination> destinationsTableView;

    @FXML private void initialize() throws Exception{
        destinationsTableView.setItems(model.getDestinations());
        if (countryFilter != null) {
            countryFilter.textProperty().addListener((observable, oldS, newS) -> cutList(newS));
        }
    }

    private void cutList(String s) {
        ObservableList<Destination> allDests = model.getDestinations();
        ObservableList<Destination> newDests = FXCollections.<Destination>observableArrayList();
        for (Destination d : allDests) {
            if (d.getCountry().toLowerCase().contains(s)) {
                newDests.add(d);
            }
        }
        destinationsTableView.setItems(newDests);
    }

    public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    


}
