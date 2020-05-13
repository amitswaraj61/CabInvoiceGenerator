package cabInvoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides = null;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        boolean checkRide = userRides.containsKey(userId);
        ArrayList<Ride> list= new ArrayList<Ride>();
        if(checkRide == false){
            list.addAll(Arrays.asList(rides));
            this.userRides.put(userId,new ArrayList<>(Arrays.asList(rides)));
        }
        else for(Ride ride : rides) userRides.get(userId).add(ride);
    }
    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
