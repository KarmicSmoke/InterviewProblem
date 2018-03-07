package flipkart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseCab {

    private static List<Driver> driverList = new ArrayList<>();
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Cab> cabsAvailable = new ArrayList<>();
    private static Customer cabNeededForThisCustomer;
    private static double threshold = 50;

    public static void main(String[] args) {

        fillTheHistoricData();
        fillTheCabData();
        getTheRequestingCustomerData();
        solve();

    }

    private static void solve() {

        //Step 0 : Valid Seats Chosen
        checkIfCustomerBooksValidSeats();

        //Step 1 : Filter Cabs based on 1 rating given
        filterByRatings();

        //Step 2 : Filter Based on available Seats
        filterByAvailableSeats();

        //Step 3 : Filter based on Distance Threshold
        filterByThreshold();

        //Step 4 : Driver Average Rating should be Greater Than Average Rating Of Customer
        filterByDriverAverageRatingGreaterThanAverageRatingOfCustomer();
    }

    private static void filterByDriverAverageRatingGreaterThanAverageRatingOfCustomer() {
        List<Cab> tempCabsAvailable = new ArrayList<>(cabsAvailable);

        for (Cab cab : tempCabsAvailable) {
            if (cab.getCabDriver().getAverageRatingReceived() < cabNeededForThisCustomer.getAverageRatingReceived())
                tempCabsAvailable.remove(cab);
        }

        System.out.println("Customer for whom Cab is needed : " + cabNeededForThisCustomer);
        System.out.println("List of available cabs : " + System.lineSeparator());

        if (tempCabsAvailable.isEmpty()) {
            useTheAlternativeBasedOnDriverWithWhomCustomerRodeBefore();
            System.out.println(cabsAvailable);

        } else {
            System.out.println(tempCabsAvailable);
        }
    }

    private static void useTheAlternativeBasedOnDriverWithWhomCustomerRodeBefore() {

        for (Cab cab : cabsAvailable) {
            if (!cabNeededForThisCustomer.isDriverHistoryPresent(cab.getCabDriver())) {
                cabsAvailable.remove(cab);
            }
        }

    }

    private static void filterByThreshold() {

        for (Cab cab : cabsAvailable) {
            if (!cab.canAcceptCustomerByThreshold(cabNeededForThisCustomer, threshold))
                cabsAvailable.remove(cab);
        }
    }

    private static void checkIfCustomerBooksValidSeats() {

        if (cabNeededForThisCustomer.getSeatsRequired() != 1 || cabNeededForThisCustomer.getSeatsRequired() != 2)
            throw new InvalidSeatsSelectionException("Customer demanded either less than 1 or more than 2 seats which is invalid");
    }

    private static void filterByAvailableSeats() {

        for (Cab cab : cabsAvailable) {
            if (cab.getAvailableSeats() < cabNeededForThisCustomer.getSeatsRequired())
                cabsAvailable.remove(cab);
        }

    }

    private static void filterByRatings() {

        for (Cab cab : cabsAvailable) {
            if (cab.getCabDriver().isExistBannedCustomer(cabNeededForThisCustomer) || cabNeededForThisCustomer.isExistBannedDriver(cab.getCabDriver()))
                cabsAvailable.remove(cab);
        }
    }

    private static void getTheRequestingCustomerData() {

        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNext()) {
                // Taking input in format Ajeet , 1 , 21, 30
                String[] values = input.nextLine().split(",");

                String customerName = values[0];
                int seatsRequired = Integer.parseInt(values[1]);
                int x = Integer.parseInt(values[2]);
                int y = Integer.parseInt(values[3]);

                cabNeededForThisCustomer = new Customer();

                for (Customer customer : customerList) {
                    if (customer.getName().equals(customerName)) {
                        cabNeededForThisCustomer = customer;
                        break;
                    }

                }

                cabNeededForThisCustomer.setName(customerName);
                cabNeededForThisCustomer.setLocation(new Coordinate(x, y));
                cabNeededForThisCustomer.setSeatsRequired(seatsRequired);

            }
        }
    }

    private static void fillTheCabData() {

        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNext()) {
                String[] values = input.nextLine().split(",", 3);

                String driverName = values[0];
                Driver driver = new Driver();
                driver.setName(driverName);
                Cab cab = new Cab();
                cab.setCabDriver(driver);

                // Extracting the list of coordinates
                // And filling customers in the cab
                values[3] = values[3].substring(1, values[3].length() - 1);
                String[] parts = values[3].split("(?<=\\))(,\\s*)(?=\\()");
                for (String part : parts) {
                    part = part.substring(1, part.length() - 1); // Get rid of parentheses.
                    String[] coords = part.split(",\\s*");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    Customer customer = new Customer();
                    customer.setLocation(new Coordinate(x, y));
                    cab.addCustomer(customer);
                }

                cabsAvailable.add(cab);
            }

        }

    }

    private static void fillTheHistoricData() {

        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNext()) {
                String[] values = input.nextLine().split(",");

                String driverName = values[0];
                int driverRating = Integer.parseInt(values[1]);

                Driver driver = new Driver();
                driver.setName(driverName);
                driver.updateAverageRating(driverRating);

                String customerName = values[2];
                int customerRating = Integer.parseInt(values[3]);

                Customer customer = new Customer();
                customer.setName(customerName);
                customer.updateAverageRating(customerRating);
                customer.addDriverHistory(driver);


                if (customerRating == 1)
                    driver.setBannedCustomer(customer);

                if (driverRating == 1)
                    customer.setBannedDriver(driver);

                driverList.add(driver);
                customerList.add(customer);

            }
        }

    }


}
