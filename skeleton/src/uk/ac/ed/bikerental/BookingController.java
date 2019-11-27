package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
import java.util.*;

class BookingNotFoundException extends Exception {
	BookingNotFoundException() { }
}

public class BookingController {
	private List<Booking> bookings;

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

	/**
	 * Called in the 'Return Bikes' use case. Returns the bikes to the providers available
	 * stock, and returns the deposit to the customer
	 *
	 * @param bikes    a list of the bikes being returned
	 * @param provider the provider that the customer is returning the bikes to
	 */
	public void returnBikes(ArrayList<Bike> bikes, BikeProvider provider) {
		Booking booking;
		try {
			booking = findBooking(bikes);
		} catch(BookingNotFoundException ex) {
			// TODO: add some error handling here
			System.out.println("collection of bikes is not being rented");
			return;
		}

		if (booking.getStatus() != BookingStatus.BOOKED) {
			System.out.println("bikes are not currently being booked");
			return;
		}

		returnDeposit(booking);

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
	 * @return       the booking containing the bikes
	 */
	private Booking findBooking(ArrayList<Bike> bikes) throws BookingNotFoundException {
		Objects.requireNonNull(bikes);

		for (Booking booking : bookings) {
			if (booking.getQuote().getBikes().equals(bikes)) {
				return booking;
			}
		}

		throw new BookingNotFoundException();
	}

	/**
	 * Adds a booking to the controller
	 *
	 * @param invoice the booking to add
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
	public void returnDeposit(Booking booking) {
		Quote q = booking.getQuote();

		PaymentService.pay();
	}
}
