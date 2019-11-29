package uk.ac.ed.bikerental;

enum  BookingStatus{
    AwaitingPayment,
    PAY_DEPOSIT, // paid deposit
    PAYMENT_DONE, // paid rental price
    DEADLINE_REACHED, // should return the bike
    RETURNED // bike has been returned and booking is resolved
}
