package cabInvoice;

public class Ride {
    private double distance;
    private double time;
    private CabRide cabRide;

    public Ride(double distance, double time, CabRide cabRide) {
        this.distance = distance;
        this.time = time;
        this.cabRide = cabRide;
    }
    public double calcCostOfCabRide() {
        return cabRide.calcCostOfCabRide(distance, time);
    }
}
