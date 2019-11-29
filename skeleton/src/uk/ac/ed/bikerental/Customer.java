package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class Customer extends User {
    private int phone_nr;
    private ArrayList<Booking> bookings;
    private String name;
    private Location location;
    public Customer(String Name, Location loc , int phone , ArrayList<Booking> booking){
        super(Name,loc);
        this.phone_nr = phone;
        this.bookings = booking;

    }
    public String getName(){
        return this.name;
    }
    public int getPhoneNr(){
        return this.phone_nr;
    }
    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }
    public Location getLocation(){
        return this.location;
    }





}
