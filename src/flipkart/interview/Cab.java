package flipkart.interview;

import java.util.List;

public class Cab {

    private Driver cabDriver;
    private int availableSeats;
    private List<Customer> poolingCustomerList;

    public Driver getCabDriver() {
        return cabDriver;
    }

    public void setCabDriver(Driver cabDriver) {
        this.cabDriver = cabDriver;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<Customer> getPoolingCustomerList() {
        return poolingCustomerList;
    }

    public void addCustomer(Customer customer) {
        if (availableSeats != 0) {
            poolingCustomerList.add(customer);
            --availableSeats;
        }
    }

    public boolean canAcceptCustomerByThreshold(Customer cabNeededForThisCustomer, double threshold) {

        if (poolingCustomerList.isEmpty())
            return true;

        double minimumDistance = Double.MAX_VALUE;

        for (Customer customer : poolingCustomerList) {
            minimumDistance = Math.min(minimumDistance, cabNeededForThisCustomer.getRelativeDistance(customer));
        }

        if (minimumDistance <= threshold)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "cabDriver=" + cabDriver +
                '}';
    }
}
