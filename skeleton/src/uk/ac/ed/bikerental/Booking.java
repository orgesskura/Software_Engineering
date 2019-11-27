package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class Booking {
    private Quote quote;
    private int orderNumber;
    private BookingStatus status;
    
    public Booking(Quote quote, int orderNumber) {
	this.quote = quote;
	this.orderNumber = orderNumber;
	this.status = BookingStatus.PENDING;
    }

    public int getOrderNumber() {
	return this.orderNumber;
    }

    public Quote getQuote() {
	return this.quote;
    }
    
    public BookingStatus getStatus() {
	return this.status;
    }
	 
    public void setStatus(BookingStatus status) {
	this.status = status;
    }
}

enum BookingStatus {
    PENDING,
    BOOKED,
    RETURNED
}
