package model;

import javafx.beans.property.*;

public class Destination implements Itinery {
    
    private StringProperty name;
    private StringProperty country;

    public Destination(String name, String country) {
        this.name = new SimpleStringProperty(name);
        this.country = new SimpleStringProperty(country);
    }

    public String getName() {
        return name.get();
    }
    
    public String getCountry() {
        return country.get();
    }

    public ReadOnlyStringProperty nameProperty() {
        return this.name;
    }

    public ReadOnlyStringProperty countryProperty() {
        return this.country;
    }

    @Override
    public String toString() {
        return name.get() + " in " + country.get();
    }
}
