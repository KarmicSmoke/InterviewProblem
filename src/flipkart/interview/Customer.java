package flipkart.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Customer {

    private String name;
    private Double averageRatingReceived;
    private int totalDriversRatedBy;
    private Coordinate location;
    private int seatsRequired;
    private List<Driver> bannedDrivers = new ArrayList<>();
    private HashSet<Driver> listOfDriversWithWhomCustomerRodeBefore = new HashSet<>();

    public int getSeatsRequired() {
        return seatsRequired;
    }

    public void setSeatsRequired(int seatsRequired) {
        this.seatsRequired = seatsRequired;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageRatingReceived() {
        return averageRatingReceived;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public void setBannedDriver(Driver driver) {
        bannedDrivers.add(driver);
    }

    public boolean isExistBannedDriver(Driver driver) {
        return bannedDrivers.contains(driver);
    }

    public void updateAverageRating(int rating) {
        averageRatingReceived = (averageRatingReceived * totalDriversRatedBy + rating) / (++totalDriversRatedBy);
    }

    public double getRelativeDistance(Customer customer) {
        Coordinate location1 = customer.getLocation();
        Coordinate location2 = location;
        return location1.getDistance(location2);
    }

    public void addDriverHistory(Driver driver) {
        listOfDriversWithWhomCustomerRodeBefore.add(driver);
    }

    public boolean isDriverHistoryPresent(Driver driver) {
        return listOfDriversWithWhomCustomerRodeBefore.contains(driver);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", averageRatingReceived=" + averageRatingReceived +
                '}';
    }
}
