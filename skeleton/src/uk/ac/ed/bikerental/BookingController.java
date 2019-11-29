package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
import java.util.*;

class BookingNotFoundException extends Exception {
    BookingNotFoundException() { }
}

/**
 * A manager class to collect all bookings made within the system. Provides functionality for
 * interacting with bookings.
 */
public class BookingController {
    /**
     * The bookings the controller manages
     */
    private ArrayList<Booking> bookings;

    /**
     * Returns a new BookingController object, initialised with a list of bookings, or an
     * empty list if given a null reference
     *
     * @param booking an ArrayList of previous bookings
     */
    public BookingController(ArrayList<Booking> bookings) {
        if (bookings != null) {
            this.bookings = bookings;
        } else {
            this.bookings = new ArrayList<Booking>();
        }
    }
    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }

    /**
     * Called in the 'Return Bikes' use case. Returns the bikes to the providers available
     * stock, and returns the deposit to the customer
     *
     * @param bikes    a list of the bikes being returned
     * @param provider the provider that the customer is returning the bikes to
     */
    public void returnBikes(ArrayList<Bike> bikes, BikeProvider provider,BankDetails customer_deposit) {
        Booking booking;
        try {
            booking = findBooking(bikes);
        } catch(BookingNotFoundException ex) {
            // TODO: add some error handling here
            System.out.println("collection of bikes is not being rented");
            return;
        }

        if (booking.getStatus() != BookingStatus.PAYMENT_DONE) {
            System.out.println("bikes are not currently being booked");
            return;
        }

        returnDeposit(booking,customer_deposit);

        Quote q = booking.getQuote();

        if (q.getBikeProvider() == provider) {
            for (Bike bike : q.getBikes()) {
                bike.setStatus(BikeStatus.AVAILABLE);
            }

            booking.setStatus(BookingStatus.RETURNED);
        } else {
            assert q.getBikeProvider().hasPartner(provider);

            for (Bike bike : q.getBikes()) {
                DeliveryServiceFactory.setupMockDeliveryService();
                DeliveryService deliveryService = DeliveryServiceFactory.getDeliveryService();

                bike.setStatus(BikeStatus.FOR_RETURN);

                deliveryService.scheduleDelivery(bike, provider.getLocation(), q.getBikeProvider().getLocation(), LocalDate.now().plusDays(1));
            }
        }
    }

    /**
     * Finds the booking containing a given list of bikes
     *
     * @param  bikes the bikes the desired booking will be about
     *
     * @return       the booking containing the bikes
     * @throws BookingNotFoundException If the given <code>bikes</code> do not belong to any
     * <code>Booking</code> managed by the controller
     * @throws NullPointerException if the given <code>bikes</code> or <code>dates</code> are null
     */
    private Booking findBooking(ArrayList<Bike> bikes) throws BookingNotFoundException {
        Objects.requireNonNull(bikes);
        

        for (Booking booking : bookings) {
            if (booking.getQuote().getBikes().equals(bikes) && booking.getStatus() == BookingStatus.PAYMENT_DONE ) {
                return booking;
            }
        }

        throw new BookingNotFoundException();
    }

    /**
     * Determines is a given list of bikes are available for booking during a given range
     *
     * @param bike the bike to be checked
     * @param range the dates to check for the availability of the bike
     *
     * @return <code>true</code> if the bike has not been booked during the entire range
     */
    public boolean bikeAvailableDuringRange(Bike bike, DateRange range) {
        Quote quote;

        for (Booking booking : this.bookings) {
            quote = booking.getQuote();
            if (quote.getBikes().contains(bike) && range.overlaps(quote.getDates()) && booking.getStatus() == BookingStatus.PAYMENT_DONE)
                return false;
        }

        return true;
    }

    /**
     * Adds a booking to the controller
     *
     * @param invoice the booking to add
     *
     * @throws NullPointerException if given a <code>null</code> reference to add
     */
    public void addInvoice(Booking invoice) {
        Objects.requireNonNull(invoice);
        this.bookings.add(invoice);
    }

    /**
     * Returns the deposit amount specified to the correct customer
     *
     * @param booking the booking to return the deposit
     */
    public void returnDeposit(Booking booking,BankDetails details) {
        Quote q = booking.getQuote();

        Payment.doPayment(booking,details);
    }
}
