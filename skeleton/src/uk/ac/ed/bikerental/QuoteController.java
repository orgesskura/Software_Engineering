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
    public QuoteController(ArrayList<BikeProvider> provider,ArrayList<Bike> bike,BookingController controller,BikeController controller1 ){
        this.providers = provider;
        this.bikes = bike;
        this.orderNr = 0;
        this.biController = controller1;
        this.boController = controller;
    }
    private void getInfo(Location locations, LocalDate start,LocalDate end){
        this.location = location;
        this.rangeDates = new DateRange(start,end);
    }
    private ArrayList<Quote> listQuotes(DateRange a,Location location,HashMap<BikeType,Integer> map,int nr_bikes){
        DateRange dates = this.rangeDates;
        ArrayList<Quote> quotes = new ArrayList<>();
        for(){
            DateRange dates1 = q.getDates();
            // if it is free in the desired dates i added quotes to the arraylist
            if(dates.overlapsCompletely(dates1) && q.getBikes()) {
                quotes.add(q);
            }
        }
        return quotes;

    }
    private ArrayList<Bike> getAvailableBikes(){
         
    }



}