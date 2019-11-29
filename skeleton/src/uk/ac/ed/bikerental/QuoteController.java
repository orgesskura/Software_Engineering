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
    private ArrayList<Quote> listQuotes(DateRange a,Location location,HashMap<BikeType,Integer> map,int nr_bikes){
        DateRange dates = this.rangeDates;
        this.typeNr = map;
        for(BikeProvider b : this.providers){
            boolean c = true;
            for(BikeType type : map.keySet()){
                if(this.biController.getMatchingAvailableBikes(b,map,this.boController,a).equals(null)) c = false;
                
            }
            if(c == true) {
                BigDecimal total = new BigDecimal(0.0);
                BigDecimal deposit = new BigDecimal(0.0);
                for(Bike bike :this.biController.getMatchingAvailableBikes(b,map,this.boController,a) ){
                    total = total.add(bike.getType().getValue()); // calculate total to pay and also include interface later
                    deposit = deposit.add(bike.getType().getValue()); // calculate deposit amount via interface
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
    private void bookQuote(Quote quotes,String name , String mode_collection,BankDetails details){
        this.orderNr++;
        Booking booking = new Booking(quotes,this.orderNr,BookingStatus.AwaitingPayment);
        this.bookings.add(booking);
        Payment.doPayment(booking,details);
        
    }



}