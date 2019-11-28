package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here

    private BikeType mountain;
    private BikeType road;

    private LocalDate older;
    private LocalDate newer;
    private LocalDate newest;

    private Bike b1;
    private Bike b2;
    private Bike b3;

    private Location l1;
    private Location l2;

    private ArrayList<String> open1;

    private BikeProvider p1;
    private BikeProvider p2;

    private HashMap<Bike, BikeProvider> bikes;

    private BikeController bikeController;

    private ArrayList<Bike> bookedBikes;

    private Quote q1;
    private Booking b1;
    private ArrayList<Booking> bs;

    private BookingController bookingController;
    
    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        mountain = new BikeType("mountain", new BigDecimal(100), new BigDecimal(0.1), new BigDecimal(0.3));
        road = new BikeType("road", new BigDecimal(250), new BigDecimal(0.2), new BigDecimal(0.2));

        older = LocalDate.of(2018, 10, 10);
        newer = LocalDate.of(2019, 8, 19);
        newest = LocalDate.of(2019, 9, 21);
        
        b1 = new Bike(older, mountain, 1);
        b2 = new Bike(older, road, 2);
        b3 = new Bike(newer, road, 3);

        l1 = new Location("AA1 B23", "12 road street");
        l2 = new Location("AA1 B23", "21 street road");

        open1 = new ArrayList<String>();
        Open1.add("10 - 12");
        open1.add("10 - 18");
        open1.add("10 - 12");
        open1.add("10 - 12");
        open1.add("");
        open1.add("");
        open1.add("10 - 12");
        
        p1 = new BikeProvider("shop1", l1, open, null, null, new LinearDepreciationPolicy(), new BigDecimal(1.2));
        p2 = new BikeProvider("shop2", l2, open, null, null, new DoubleDecliningPolicy(), new BigDecimal(1.1));

        bikes = new HashMap<Bike, BikeProvider>();
        bikes.put(b1, p1);
        bikes.put(b2, p1);
        bikes.put(b3, p2);
        
        bikeController = new BikeController(bikes, null);


        bookedBikes = new ArrayList<Bike>();
        bookedBikes.add(b1);
        b1.setStatus(BikeStatus.UNAVAILABLE);
        
        q1 = new Quote(p1, bookedBikes, new BigDecimal(10), new BigDecimal(10), newest);
        b1 = new Booking(q1, 1, BookingStatus.PAYMENT_DONE);

        bs = new ArrayList<Booking>();
        bs.add(b1);
        
        bookingController = new BookingController(b1);
    }
    
    // TODO: Write system tests covering the three main use cases

    // @Test
    // void myFirstTest() {
    //     // JUnit tests look like this
    //     assertEquals("The moon", "cheese"); // Should fail
    // }

    @Test
    void returnBikesToOriginalProviderPass() {
        bookingController.returnBikes(bookedBikes, p1);

        assertEquals(b1.getStatus(), BikeStatus.available);
    }

    @Test
    void returnBikesToPartnerProviderPass() {
        p1.addPartner(p2);

        bookingController.returnBikes(bookedBikes, p2);
        assertEquals(b1.getStatus(), BikeStatus.available);
    }
}
