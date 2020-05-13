package cabInvoice;

public enum CabRide {
    NORMAL(10.0, 1.0, 5.0), PREMIUM(15.0, 2.0, 20.0);

    private final double costPerKm;
    private final double costPerMin;
    private final double minFarePerRide;

    CabRide(double costPerKm, double costPerMin, double minFare) {
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
        this.minFarePerRide = minFare;
    }

    public double calcCostOfCabRide(Ride ride) {
        double rideCost = ride.distance * costPerKm + ride.time * costPerMin;
        return Math.max(rideCost, minFarePerRide);
    }
}
