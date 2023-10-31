package model;

import java.util.LinkedList;
import javafx.collections.*;
import model.Exceptions.*;

public class Flights {

    private ObservableList<Flight> flights;
    private Agency agency;

    public Flights(Agency agency) {
        this.agency = agency;
        flights = FXCollections.<Flight>observableArrayList();
    }

    public Flights(ObservableList<Itinery> itinery) {
        this.flights = FXCollections.<Flight>observableArrayList();
        for (Itinery i : itinery) { this.flights.add((Flight)i); }
    }

    public ObservableList<Flight> getFlights() {
        return this.flights;
    }

    public Agency getAgency() {
        return this.agency;
    }

    public void addFlight(Flight flight) throws DuplicateItemException {
        if (hasFlight(flight.getTakeoff(), flight.getLanding())) { throw new DuplicateItemException(); }
        this.flights.add(flight);
    }

    public void removeFlight(Flight flight) throws ItemNotFoundException {
        if (!hasFlight(flight.getTakeoff(), flight.getLanding())) { throw new ItemNotFoundException(); }
        this.flights.remove(flight);
    }

    public boolean hasFlight(String takeoff, String landing) {
        for (Flight f : this.flights) { if (f.getTakeoff().equals(takeoff) && f.getLanding().equals(landing)) { return true; } }
        return false;
    }

    public Flight getFlight(String takeoff, String landing) throws ItemNotFoundException {
        for (Flight f : this.flights) { if (f.getTakeoff().equals(takeoff) && f.getLanding().equals(landing)) { return f; } }
        throw new ItemNotFoundException();
    }

    public ObservableList<Flight> getFilteredFlights(String country) {
        LinkedList<Flight> filtered = new LinkedList<Flight>();
        for (Flight f : flights) { if (f.getLanding().toLowerCase().contains(country.toLowerCase()) || f.getTakeoff().toLowerCase().contains(country.toLowerCase())) { filtered.add(f); } }
        return FXCollections.<Flight>observableArrayList(filtered);
    }

    public double getTotalCost() {
        double cost = 0;
        for (Flight f : flights) { cost += f.getCost(); }
        return cost;
    }
}
