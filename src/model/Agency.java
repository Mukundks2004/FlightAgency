package model;

public class Agency {

    private Administrator loggedInUser;
    private Destinations destinations;
    private Flights flights;
    private Administrators admins;

    public Agency() {
        this.admins = new Administrators();
        this.destinations = new Destinations(this);
        this.flights = new Flights(this);

        admins.insertDummyData();
        destinations.insertDummyData();
    }

    public Administrator getLoggedInUser() {
        return loggedInUser;
    }

    public Flights getFlights() {
        return this.flights;
    }

    public Destinations getDestinations() {
        return this.destinations;
    }

    public Administrators getAdministrators() {
        return this.admins;
    }

    public void setLoggedInUser(Administrator administrator) {
        this.loggedInUser = administrator;
    }
}
