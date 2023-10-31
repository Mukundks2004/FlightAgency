package model;

import java.util.LinkedList;

import javafx.collections.*;
import model.Exceptions.*;

public class Destinations {

    private ObservableList<Destination> destinations;
    private Agency agency;

    public Destinations(Agency agency) {
        this.agency = agency;
        this.destinations = FXCollections.<Destination>observableArrayList();
    }

    public Destinations(ObservableList<Itinery> itinery) {
        this.destinations = FXCollections.<Destination>observableArrayList();
        for (Itinery i : itinery) { this.destinations.add((Destination)i); }
    }

    public ObservableList<Destination> getDestinations() {
        return this.destinations;
    }

    public Agency getAgency() {
        return this.agency;
    }

    public void addDestination(Destination destination) throws DuplicateItemException {
        if (hasDestination(destination.getName(), destination.getCountry())) { throw new DuplicateItemException(); }
        this.destinations.add(destination);
    }

    public void removeDestination(Destination destination) throws ItemNotFoundException {
        if (!hasDestination(destination.getName(), destination.getCountry())) { throw new ItemNotFoundException(); }
        this.destinations.remove(destination);
    }

    public boolean hasDestination(String name, String country) {
        for (Destination d : destinations) {
            if (d.getName().equals(name) && d.getCountry().equals(country)) { return true; }
        }
        return false;
    }

    //rename
    public Destination destination(String name, String country) throws ItemNotFoundException {
        if (!hasDestination(name, country)) { throw new ItemNotFoundException(); }
        for (Destination d : destinations) { if (d.getName().equals(name) && d.getCountry().equals(country)) { return d; } }
        return null;
    }

    public ObservableList<Destination> getFilteredDestinations(String country) {
        LinkedList<Destination> filtered = new LinkedList<Destination>();
        for (Destination d : destinations) { if (d.getCountry().toLowerCase().contains(country.toLowerCase()) || d.getCountry().toLowerCase().contains(country.toLowerCase())) { filtered.add(d); } }
        return FXCollections.<Destination>observableArrayList(filtered);
    }

    public void insertDummyData() {
        destinations.add(new Destination("Eiffel Tower", "France"));
        destinations.add(new Destination("Opera House", "Australia"));
        destinations.add(new Destination("Uluru", "Australia"));
        destinations.add(new Destination("Machu Picchu", "Peru"));
        destinations.add(new Destination("Great Pyramids", "Egypt"));
        destinations.add(new Destination("Niagara Falls", "Canada"));
        for (Destination d : destinations) {
            Utils.addFlightsForDestination(d, agency);
        }
    }
}
