package cabInvoice;



public class InvoiceService {

    private final RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }
    public double calculateFare(double distance, double time, CabRide cabRideType) {
        return cabRideType.calcCostOfCabRide(distance, time);
    }


    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.calcCostOfCabRide();
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

    public InvoiceSummary getInvoiceSummary(String userId) throws CabInvoiceException {
        if (userId == null) {
            throw new CabInvoiceException("userId cant be null", CabInvoiceException.ExceptionType.USER_CANT_BE_NULL);
        } else {
            return this.calculateFare(rideRepository.getRides(userId));
        }
    }
}
