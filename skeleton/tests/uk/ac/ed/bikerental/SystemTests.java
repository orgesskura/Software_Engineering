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
import java.util.Set;
import java.util.HashSet;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
public class SystemTests {
    // You can add attributes here

    private BikeType mountain;
    private BikeType road;
    private BikeType bmx;

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

    private ArrayList<Bike> bookedBikes;
    private QuoteController qController;

    private Quote q1;
    private Booking bo1;
    private ArrayList<Booking> bs;
    private BankDetails details1;
    private BankDetails details2;
    private BookingController bookingController;
    private Map<DaysOfWeek,ArrayList<LocalTime>> openingHours;
    
    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        mountain = new BikeType("mountain", new BigDecimal(100), new BigDecimal(0.1), new BigDecimal(0.3));
        road = new BikeType("road", new BigDecimal(250), new BigDecimal(0.2), new BigDecimal(0.2));
        bmx = new BikeType("bmx", new BigDecimal(400), new BigDecimal(0.05), new BigDecimal(0.4));

        Set<BikeType> types = new HashSet<>();
        types.add(mountain);
        types.add(road);
        types.add(bmx);
        older = LocalDate.of(2018, 10, 10);
        newer = LocalDate.of(2019, 8, 19);
        newest = LocalDate.of(2019, 9, 21);
        DateRange dates = new DateRange(older,newest);
        
        b1 = new Bike(older, mountain, 1);
        b2 = new Bike(older, road, 2);
        b3 = new Bike(newer, road, 3);

        l1 = new Location("AA1 B23", "12 road street");
        l2 = new Location("AA1 B23", "21 street road");

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
        
        p1 = new BikeProvider("shop1", l1, openingHours, new HashSet<BikeProvider>(),new DefaultPricingPolicy(), new LinearDepreciationPolicy(), new BigDecimal(1.2));
        p2 = new BikeProvider("shop2", l2, openingHours, new HashSet<BikeProvider>(), mock, new DoubleDecliningPolicy(), new BigDecimal(1.1));
        providers = new ArrayList<BikeProvider>();
        providers.add(p1);
        providers.add(p2);
        bikes = new HashMap<Bike, BikeProvider>();
        bikes.put(b1, p1);
        bikes.put(b2, p1);
        bikes.put(b3, p2);
        
        bikeController = new BikeController(bikes, new HashMap<Bike,DateRange>());


        bookedBikes = new ArrayList<Bike>();
        bookedBikes.add(b1);
        bookedBikes.add(b2);
        bookedBikes.add(b3);
        b1.setStatus(BikeStatus.UNAVAILABLE);
        
        q1 = new Quote(p1,types, new BigDecimal(10), new BigDecimal(10), dates, bookedBikes);
        bo1 = new Booking(q1, 1, BookingStatus.PAYMENT_DONE);
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(bo1);

        this.bs = new ArrayList<Booking>();
        this.bs.add(bo1);
        
        bookingController = new BookingController(bs);
        String nr1  = "4312-1234-9876-0779";
        String nr2  = "5347-9762-6969-0900";
        String name1 = "Sami El-Daher";
        String name2 = "Orgasm Skura";
        LocalDate date1 = LocalDate.of(2021,Month.MARCH,25);
        LocalDate date2  = LocalDate.of(2022,Month.SEPTEMBER,10);
        int code1 = 969;
        int code2 = 177;
        this.details1 = new BankDetails(nr1,name1,date1,code1);
        this.details2 = new BankDetails(nr2, name2, date2, code2);

        qController = new QuoteController(providers, bookedBikes, bookingController, bikeController, new ArrayList<Quote>());

    }
    
    // TODO: Write system tests covering the three main use cases

    // @Test
    // void myFirstTest() {
    //     // JUnit tests look like this
    //     assertEquals("The moon", "cheese"); // Should fail
    // }

    // black box test for bike return to original provider
    @Test
    void findQuoteTest(){
        DateRange dates = new DateRange(LocalDate.of(2019,Month.MARCH,21),LocalDate.of(2019,Month.NOVEMBER,30));
        Customer customer1 = new Customer("Sami",l1,7978988,new ArrayList<Booking>());
        HashMap<BikeType,Integer> bikesPerType = new HashMap<>();
        bikesPerType.put(road,1);
        Location location = new Location("AA12 7AP","13 Traquair Park East");
        ArrayList<Quote> quote = qController.listQuotes(dates,location,bikesPerType);
        assertEquals(quote.size(),1);
    }

    @Test
    void findQuoteTestDifferentCity(){
        DateRange dates = new DateRange(LocalDate.of(2019,Month.MARCH,21),LocalDate.of(2019,Month.NOVEMBER,30));
        Customer customer1 = new Customer("Sami",l1,7978988,new ArrayList<Booking>());
        HashMap<BikeType,Integer> bikesPerType = new HashMap<>();
        bikesPerType.put(bmx,3);
        Location location = new Location("EH12 7AP","13 Traquair Park East");
        ArrayList<Quote> quote = qController.listQuotes(dates,location,bikesPerType);
        assertEquals(quote.size(),0);
        
    }

    @Test
    void returnBikesToOriginalProviderPass() {
        bookingController.returnBikes(bookedBikes, p1,details1);

        assertEquals(b1.getStatus(), BikeStatus.AVAILABLE);
    }

    // black box test fpr return to partner provider
    @Test
    void returnBikesToPartnerProviderPass() {
        p1.addPartner(p2);

        bookingController.returnBikes(bookedBikes, p2,details2);
        assertEquals(b1.getStatus(), BikeStatus.FOR_RETURN);
    }

    @Test
    void findQuoteAndBookIt(){
        DateRange dates = new DateRange(LocalDate.of(2019,Month.MARCH,21),LocalDate.of(2019,Month.NOVEMBER,30));
        Customer customer1 = new Customer("Sami",l1,7978988,new ArrayList<Booking>());
        HashMap<BikeType,Integer> bikesPerType = new HashMap<>();
        bikesPerType.put(bmx,3);
        Location location = new Location("EH12 7AP","13 Traquair Park East");
        ArrayList<Quote> quote = qController.listQuotes(dates,location,bikesPerType);
        assertEquals(quote.size(),1);
        qController.bookQuote(quote.get(0),"Sami",true,this.details1,customer1);
        assertEquals(bookingController.getBookings().get(0).getStatus(),BookingStatus.PAYMENT_DONE);
    }


    

   
}
