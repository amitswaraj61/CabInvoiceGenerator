package cabInvoice;

import java.util.ArrayList;

public class Ride extends ArrayList<Ride> {
    public double distance;
    public double time;
    public CabRide cabRide;

    public Ride(double distance, double time, CabRide cabRide) {
        this.distance = distance;
        this.time = time;
        this.cabRide = cabRide;
    }
}
