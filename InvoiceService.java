package cabInvoice;

import java.util.ArrayList;

public class InvoiceService {
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;
    private final RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) throws CabInvoiceException {
        if (userId == null) {
            throw new CabInvoiceException("userId cant be null", CabInvoiceException.ExceptionType.USER_CANT_BE_NULL);
        } else {
            rideRepository.addRides(userId, rides);
        }
    }

    public InvoiceSummary getInvoiceSummary(String userId) {

        return this.calculateFare(rideRepository.getRides(userId));
    }
}
