package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

public class ValuationPolicyTests {
    // You can add attributes here

    // You can add attributes here
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
    private ArrayList<BikeProvider> providers;

    private HashMap<Bike, BikeProvider> bikes;


    private BikeController bikeController;

    private HashMap<BikeType, Integer> requested;
    private ArrayList<Bike> bookedBikes;
    private QuoteController qController;

    private Quote q1;
    private Booking bo1;
    private ArrayList<Booking> bs;
    private BankDetails details1;
    private BankDetails details2;
    private BookingController bookingController;
    private Map<DaysOfWeek,ArrayList<LocalTime>> openingHours;

    private DateRange dates;

    @BeforeEach
    void setUp() throws Exception {
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        road = new BikeType("road", new BigDecimal(250), new BigDecimal(0.2), new BigDecimal(0.2));

        Set<BikeType> types = new HashSet<>();
        types.add(road);

        older = LocalDate.of(2018, 10, 10);
        newer = LocalDate.of(2019, 9, 19);
        newest = LocalDate.of(2019, 9, 21);
        dates = new DateRange(newer,newest);
        
        b1 = new Bike(older, road, 2);
        b2 = new Bike(older, road, 2);

        l1 = new Location("AA1 B23", "12 road street");
        l2 = new Location("AA2 B23", "21 street road");

       this.openingHours= new HashMap<>();
       ArrayList<LocalTime> list = new ArrayList<>();
       list.add(LocalTime.of(10,0,0));
       list.add(LocalTime.of(16,0,0));
       
        openingHours.put(
            DaysOfWeek.MONDAY, 
            list);
        openingHours.put(
            DaysOfWeek.TUESDAY,
            list);
        openingHours.put(
            DaysOfWeek.WEDNESDAY,
            list);
        openingHours.put(
            DaysOfWeek.THURSDAY,
            list);
        openingHours.put(
            DaysOfWeek.FRIDAY,
            list);
        MockPricingPolicy mock = new MockPricingPolicy();    
        
        p1 = new BikeProvider("shop1", l1, openingHours, new HashSet<BikeProvider>(), null, new LinearDepreciationPolicy(), new BigDecimal(1));
        p2 = new BikeProvider("shop2", l2, openingHours, new HashSet<BikeProvider>(), mock, new DoubleDecliningPolicy(), new BigDecimal(1));
        providers = new ArrayList<BikeProvider>();
        providers.add(p1);
        providers.add(p2);
        bikes = new HashMap<Bike, BikeProvider>();
        bikes.put(b1, p1);
        bikes.put(b2, p1);
        bikes.put(b3, p2);
        
        bikeController = new BikeController(bikes, new HashMap<Bike,DateRange>());

        requested = new HashMap<BikeType, Integer>();
        requested.put(road, 1);

        bookedBikes = new ArrayList<Bike>();
        
        bs = new ArrayList<Booking>();
        
        bookingController = new BookingController(bs);
        qController = new QuoteController(providers, bookedBikes, bookingController, bikeController, new ArrayList<Quote>());

    }
    
    /**
     * testing this should give the deposit value
     * x = depositrate * bike value
     *   = 1.0 * 250
     *   = 250
     */
    @Test
    void testDefaultValuationPolicy() {
        ArrayList<Bike> found = new ArrayList<Bike>();
        found.add(b1);

        assertEquals(qController.calculateDeposit(found, p1, dates).stripTrailingZeros(), new BigDecimal(250).stripTrailingZeros());
    }
}
