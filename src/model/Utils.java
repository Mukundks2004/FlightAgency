package model;

import java.util.LinkedList;

public class Utils {

    //new airlines
    //check duplicates
    public static void addFlightsForDestination(Destination destination, Agency agency) {
        String country = destination.getCountry();
        String[] airlines = {"American Airlines", "QANTAS", "JetStar", "Tiger Airways", "United Airlines", "Egypt Air", "Etihad", "Singapore Airlines", "British Air", "Cathay Dragon"};
        int flightMin = 11;
        int flightMax = 999;
       
        double costMin = 49.99;
        double costMax = 999.99;

        LinkedList<String> countries = new LinkedList<String>();
        for (Destination d : agency.getDestinations().getDestinations()) { countries.add(d.getCountry()); }

        for (String s : countries) {
            try {
                agency.getFlights().addFlight(new Flight(airlines[(0 + (int)(Math.random() * (((airlines.length - 1) - 0) + 1)))], (flightMin + (int)(Math.random() * ((flightMax - flightMin) + 1))), country, s, (costMin + (Math.random() * ((costMax - costMin) + 1)))));
                agency.getFlights().addFlight(new Flight(airlines[(0 + (int)(Math.random() * (((airlines.length - 1) - 0) + 1)))], (flightMin + (int)(Math.random() * ((flightMax - flightMin) + 1))), s, country, (costMin + (Math.random() * ((costMax - costMin) + 1)))));
            }
            catch (Exception ex) { continue; }
        }
    }
}
