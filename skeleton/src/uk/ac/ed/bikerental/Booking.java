package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class Booking {
    private Quote quote;
    private int orderNumber;
    private List<Bike> bikes;
    
    public Booking(Quote quote, int orderNumber, ArrayList<Bike> bikes) {
	this.quote = quote;
	this.orderNumber = orderNumber;
	this.bikes = bikes;
    }

    public List<Bike> getBikes() {
	return this.bikes;
    }
}

enum BookingStatus {
    PENDING,
    BOOKED,
    RETURNED
}
