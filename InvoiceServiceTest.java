package cabInvoice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;

    @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance,time, Ride.RideType.Normal);
        assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance,time, Ride.RideType.Normal);
        assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "a@b.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenInputWithoutUserId_WhenCalculated_ShouldReturnCustomException()  {
        String userId = null;
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        try {
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        } catch (CabInvoiceException exception) {
            assertEquals("userId cant be null", exception.getMessage());
        }
    }
    @Test
    public void givenMultipleRidesArrays_ShouldReturnInvoiceSummary() throws CabInvoiceException{
        String userId = "amit.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId, rides);
        Ride[] rides1 = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 60);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenMultipleUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "amit.com";
        String userId1="amy.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId, rides);
        Ride[] rides1 = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId, rides1);
        Ride[] rides2 = {
                new Ride(3.0, 5, Ride.RideType.Normal),
                new Ride(0.2, 1, Ride.RideType.Normal)
        };
        invoiceService.addRides(userId1,rides2);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 60);
        assertEquals(expectedInvoiceSummary, summary);
    }
    @Test
    public void givenPremiumAndNormalRides_ShouldReturnInvoiceSummary() throws CabInvoiceException{
        String userId = "amit.com";
        String userId1="amy.com";
        Ride[] rides = {
                new Ride(2.0,5.0, Ride.RideType.Normal),
                new Ride(0.1,1.0, Ride.RideType.Premium)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45);
        assertEquals(expectedInvoiceSummary, summary);
    }

}


