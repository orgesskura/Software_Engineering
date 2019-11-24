package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

class BookingNotFoundException extends Exception {
    BookingNotFoundException() { }
}

public class BookingController {
    private List<Booking> bookings;

    public BookingController(ArrayList<Booking> bookings) {
	if (bookings != null) {
	    this.bookings = bookings;
	} else {
	    this.bookings = new ArrayList<Booking>();
	}
    }

    public void returnBikes(ArrayList<Bike> bikes, BikeController controller) {
	Booking booking;
	try {
	    booking = findBooking(bikes);
	} catch(BookingNotFoundException ex) {
	    // TODO: add some error handling here
	    System.out.println("collection of bikes is not being rented");
	    return;
	}

	returnDeposit(booking);

	for (Bike bike : booking.getBikes()) {
	    controller.makeAvailable(bike);
	}
    }

    private Booking findBooking(ArrayList<Bike> bikes) throws BookingNotFoundException {
	Objects.requireNonNull(bikes);
	
	for (Booking booking : bookings) {
	    if (booking.getBikes().equals(bikes)) {
		return booking;
	    }
	}

	throw new BookingNotFoundException();
    }

    public void addInvoice(Booking invoice) {
	Objects.requireNonNull(invoice);
	this.bookings.add(invoice);
    }

    public void returnDeposit(Booking booking) {
	PaymentService.pay();
    }
}
