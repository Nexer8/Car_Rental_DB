package com.car_rental;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Location {
    private int locationId;
    private String streetAddress;
    private String city;
    private String postalCode;

    @Id
    @Column(name = "location_id")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "street_address")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId &&
                Objects.equals(streetAddress, location.streetAddress) &&
                Objects.equals(city, location.city) &&
                Objects.equals(postalCode, location.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, streetAddress, city, postalCode);
    }
}
