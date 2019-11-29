package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
import java.util.*;
public class QuoteController{
    //  private ArrayList<Quote> quotes;
    public  int orderNr;
    private ArrayList<BikeProvider> providers;
    private ArrayList<Bike> bikes; // variable storing the current number of available bikes
    private Location location; //private variable that stores the current location of the customer
    private DateRange rangeDates; // private variable that stores the current specified rangedates of the system
    private BookingController boController;
    private BikeController  biController;
    private HashMap<BikeType,Integer> typeNr; // current required nr of bike types
    private ArrayList<Quote> quote;
    private ArrayList<Booking> bookings;
    public QuoteController(ArrayList<BikeProvider> provider,ArrayList<Bike> bike,BookingController controller,BikeController controller1 ,ArrayList<Quote> sets){
        this.providers = provider;
        this.bikes = bike;
        this.orderNr = 0;
        this.biController = controller1;
        this.boController = controller;
        this.typeNr = new HashMap<BikeType,Integer>();
        this.quote = sets;
        this.bookings = new ArrayList<Booking>();
    }
    private void getInfo(Location locations, LocalDate start,LocalDate end){
        this.location = location;
        this.rangeDates = new DateRange(start,end);
    }
    public ArrayList<Quote> listQuotes(DateRange a,Location location,HashMap<BikeType,Integer> map){
        DateRange dates = this.rangeDates;
        this.typeNr = map;
        for(BikeProvider b : this.providers){
            boolean c = true;
            for(BikeType type : map.keySet()){
                if(this.biController.getMatchingAvailableBikes(b,map,this.boController,a).equals(null)) c = false;

            }
            if(c == true && location.isNearTo(b.getLocation())) {
                BigDecimal total = new BigDecimal(0.0);
                BigDecimal deposit = new BigDecimal(0.0);

                PricingPolicy pricing = b.getPricingPolicy();
                ValuationPolicy valuation = b.getValuationPolicy();

                List<Bike> bikes = this.biController.getMatchingAvailableBikes(b,map,this.boController,a);
                if (pricing == null)
                    pricing = new DefaultPricingPolicy();

                total = pricing.calculatePrice(bikes, a);

                for(Bike bike : bikes) {
                    if (valuation != null) {
                        deposit = deposit.add(valuation.calculateValue(bike, a.getStart()));
                    } else {
                        deposit = deposit.add(bike.getType().getValue().multiply(b.getDepositRate())); // calculate deposit amount via interface
                    }
                }

                Quote quote = new Quote(b, map.keySet(), total, deposit, a,this.biController.getMatchingAvailableBikes(b,map,this.boController,a));
                this.quote.add(quote);
            }
        }
        return quote;
    }



    private ArrayList<Bike> getAvailableBikes(DateRange date){
        ArrayList<Bike> list = new ArrayList<>();
        for(BikeProvider b : this.providers){
            list.addAll(this.biController.getMatchingAvailableBikes(b,this.typeNr,this.boController,date));
        }
        return list;
    }

    private int nrQuotes(){
        return this.quote.size();
    }
    public void bookQuote(Quote quotes,String name , boolean delivery_required, BankDetails details, Customer customer){
        this.orderNr++;
        Booking booking = new Booking(quotes,this.orderNr,BookingStatus.AwaitingPayment);
        boController.addInvoice(booking);
        this.bookings.add(booking);
        Payment.doPayment(booking,details);

        booking.setStatus(BookingStatus.PAYMENT_DONE);
        // delivery service should check opening hours to decide when to pickup
        if (delivery_required) {
            DeliveryService delivery_service  = DeliveryServiceFactory.getDeliveryService();
            for (Bike bike : quotes.getBikes()) {
                delivery_service.scheduleDelivery(bike, quotes.getBikeProvider().getLocation(), customer.getLocation(), quotes.getDates().getStart());
            }
        }
    }
}
