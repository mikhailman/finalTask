package by.verishko.kefir.entity;

import java.util.Objects;

public class Location extends Entity {
    private Integer idLocation;
    private String name;
    private String city;

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
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
        return idLocation.equals(location.idLocation) &&
                name.equals(location.name) &&
                city.equals(location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idLocation, name, city);
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
