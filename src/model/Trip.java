package model;

import java.util.LinkedList;
import javafx.collections.*;
import model.Exceptions.*;

public class Trip {

    private Flights flights;
    private Destinations destinations;
    private Agency agency;

    public Trip(Agency agency) {
        this.agency = agency;
        this.flights = new Flights(agency);
        this.destinations = new Destinations(agency);
    }

    public void addConnectingFlights() throws DuplicateItemException, InsufficientDestinationsException {
        if (destinations.getDestinations().size() <= 1) { throw new InsufficientDestinationsException(); }
        flights.getFlights().clear();
        Destination currDestination = null;
        Destination nextDestination = null;
        for (int i = 0; i < destinations.getDestinations().size(); i++) {
            currDestination = destinations.getDestinations().get(i);
            if (i == destinations.getDestinations().size() - 1) { return; }
            nextDestination = destinations.getDestinations().get(i + 1);
            if (currDestination.equals(nextDestination) || currDestination.getCountry().equals(nextDestination.getCountry())) { throw new DuplicateItemException(); }
            for (Flight f : agency.getFlights().getFlights()) {
                if (f.getTakeoff().equals(currDestination.getCountry()) && f.getLanding().equals(nextDestination.getCountry())) {
                    try { flights.addFlight(f); }
                    catch (DuplicateItemException ex) { throw new DuplicateItemException(); }
                    break;
                }
            }         
        }
    }

    public ObservableList<Itinery> getItinery() {
        LinkedList<Itinery> objects = new LinkedList<Itinery>();
        for (int i = 0; i < destinations.getDestinations().size(); i++) {
            objects.add(destinations.getDestinations().get(i)); 
            if (i < flights.getFlights().size()) { objects.add(flights.getFlights().get(i)); }
        }
        return FXCollections.<Itinery>observableArrayList(objects);
    }

    public Flights getFlights() {
        return this.flights;
    }

    public Destinations getDestinations() {
        return this.destinations;
    }

    public Agency getAgency() {
        return this.agency;
    }
}
