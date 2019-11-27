package uk.ac.ed.bikerental;

enum  BookingStatus{
	AwaitingPayment,
	PAY_DEPOSIT, // paid deposit
	PAYMENT_DONE, // paid rental price
	DEADLINE_REACHED, // should return the bike
	// add more types in here

	BOOKED,
	RETURNED
}
