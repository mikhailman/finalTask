package by.verishko.kefir.entity;

import java.util.Objects;

public class Location extends Entity {
    private String name;
    private String city;

    public Location() {
    }

    public Location(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) &&
                Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, city);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
