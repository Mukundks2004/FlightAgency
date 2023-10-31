package model;

import java.text.DecimalFormat;
import javafx.beans.property.*;

public class Flight implements Itinery{
    private StringProperty airline;
    private IntegerProperty flightNo;
    private StringProperty takeoff;
    private StringProperty landing;
    private DoubleProperty cost;

    public Flight(String airline, int flightNo, String takeoff, String landing, double cost) {
        this.airline = new SimpleStringProperty(airline);
        this.flightNo = new SimpleIntegerProperty(flightNo);
        this.takeoff = new SimpleStringProperty(takeoff);
        this.landing = new SimpleStringProperty(landing);
        this.cost = new SimpleDoubleProperty(formatted(cost));
    }

    public String getAirline() {
        return this.airline.get();
    }

    public int getFlightNo() {
        return this.flightNo.get();
    }

    public String getTakeoff() {
        return this.takeoff.get();
    }

    public String getLanding() {
        return this.landing.get();
    }

    public double getCost() {
        return this.cost.get();
    }

    public ReadOnlyStringProperty airlineProperty() {
        return this.airline;
    }

    public ReadOnlyIntegerProperty flightNumberProperty() {
        return this.flightNo;
    }

    public ReadOnlyStringProperty takeoffProperty() {
        return this.takeoff;
    }

    public ReadOnlyStringProperty landingProperty() {
        return this.landing;
    }

    public ReadOnlyDoubleProperty costProperty() {
        return this.cost;
    }

    @Override
    public String toString() {
        return airline.get() + " Flight " + flightNo.get() + " from " + takeoff.get() + " to " + landing.get() + " for " + cost.get();
    }

    //check this matches lab material
    public double formatted(double cost) {
        return Double.parseDouble(new DecimalFormat("##,###.00").format(cost));
    }

}
