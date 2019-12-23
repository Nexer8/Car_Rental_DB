package com.car_rental;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Car {
    private int carId;
    private String manufacturer;
    private String model;
    private String clazz;
    private int numberOfSeats;
    private int numberOfDoors;
    private double dailyRentalCost;
    private double trunkCapacity;
    private int productionYear;
    private String color;
    private double power;
    private String transmission;
    private Double userRating;
    private boolean archived;

    @Id
    @Column(name = "car_id")
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "class")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Basic
    @Column(name = "number_of_seats")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Basic
    @Column(name = "number_of_doors")
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Basic
    @Column(name = "daily_rental_cost")
    public double getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(double dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    @Basic
    @Column(name = "trunk_capacity")
    public double getTrunkCapacity() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(double trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }

    @Basic
    @Column(name = "production_year")
    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "power")
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Basic
    @Column(name = "transmission")
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Basic
    @Column(name = "user_rating")
    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    @Basic
    @Column(name = "archived")
    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                numberOfSeats == car.numberOfSeats &&
                numberOfDoors == car.numberOfDoors &&
                Double.compare(car.dailyRentalCost, dailyRentalCost) == 0 &&
                Double.compare(car.trunkCapacity, trunkCapacity) == 0 &&
                productionYear == car.productionYear &&
                Double.compare(car.power, power) == 0 &&
                archived == car.archived &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(model, car.model) &&
                Objects.equals(clazz, car.clazz) &&
                Objects.equals(color, car.color) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(userRating, car.userRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, manufacturer, model, clazz, numberOfSeats, numberOfDoors, dailyRentalCost, trunkCapacity, productionYear, color, power, transmission, userRating, archived);
    }
}
