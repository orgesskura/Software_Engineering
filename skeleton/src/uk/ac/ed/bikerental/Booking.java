package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class Booking {
    private Quote quote;
    private int orderNumber;
    
    public Booking(Quote quote, int orderNumber) {
	this.quote = quote;
	this.orderNumber = orderNumber;
    }

    public List<Bike> getBikes() {
	return this.quote.getBikes();
    }

    public int getOrderNumber() {
	return this.orderNumber;
    }
}

enum BookingStatus {
    PENDING,
    BOOKED,
    RETURNED
}
