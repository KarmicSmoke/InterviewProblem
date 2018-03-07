package flipkart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Driver {

    private String name;
    private int totalCustomersRatedBy;
    private Double averageRatingReceived;
    private List<Customer> bannedCustomers = new ArrayList<>();

    public Double getAverageRatingReceived() {
        return averageRatingReceived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExistBannedCustomer(Customer customer) {
        return bannedCustomers.contains(customer);
    }

    public void setBannedCustomer(Customer customer) {
        bannedCustomers.add(customer);
    }

    public boolean canDriveWith(Customer customer) {
        return !bannedCustomers.contains(customer);
    }

    public void updateAverageRating(int rating) {
        averageRatingReceived = (averageRatingReceived * totalCustomersRatedBy + rating) / (++totalCustomersRatedBy);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", averageRatingReceived=" + averageRatingReceived +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
