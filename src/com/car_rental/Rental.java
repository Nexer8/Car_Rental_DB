package com.car_rental;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Rental {
    private int rentalId;
    private Timestamp created;
    private double cost;
    private Timestamp startRentalDate;
    private Timestamp endRentalDate;
    private int carId;
    private int startLocationId;
    private int endLocationId;
    private int customerId;

    @Id
    @Column(name = "rental_id")
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "start_rental_date")
    public Timestamp getStartRentalDate() {
        return startRentalDate;
    }

    public void setStartRentalDate(Timestamp startRentalDate) {
        this.startRentalDate = startRentalDate;
    }

    @Basic
    @Column(name = "end_rental_date")
    public Timestamp getEndRentalDate() {
        return endRentalDate;
    }

    public void setEndRentalDate(Timestamp endRentalDate) {
        this.endRentalDate = endRentalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return rentalId == rental.rentalId &&
                Double.compare(rental.cost, cost) == 0 &&
                Objects.equals(created, rental.created) &&
                Objects.equals(startRentalDate, rental.startRentalDate) &&
                Objects.equals(endRentalDate, rental.endRentalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, created, cost, startRentalDate, endRentalDate);
    }

    @Basic
    @Column(name = "car_id")
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) { this.carId = carId; }

    @Basic
    @Column(name = "start_location_id")
    public int getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(int startLocationId) {
        this.startLocationId = startLocationId;
    }

    @Basic
    @Column(name = "end_location_id")
    public int getEndLocationId() {
        return endLocationId;
    }

    public void setEndLocationId(int endLocationId) {
        this.endLocationId = endLocationId;
    }

    @Basic
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
