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
        double fare = invoiceService.calculateFare(distance, time);
        assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance, time);
        assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "a@b.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenInputWithoutUserId_WhenCalculated_ShouldReturnCustomException() throws CabInvoiceException {
        String userId = null;
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        try {
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        } catch (CabInvoiceException exception) {
            assertEquals("userId cant be null", exception.getMessage());
        }
    }
}






