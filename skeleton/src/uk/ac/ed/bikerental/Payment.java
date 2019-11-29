package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;

public class Payment {
    private static boolean isValid(BankDetails details){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = details.getExpiryDate();
        return date2.compareTo(date1)>0;

    }
    public static void doPayment(Booking booking, BankDetails details){
        if(isValid(details)){
            booking.setStatus(BookingStatus.PAYMENT_DONE);
        }

        //would interact with external system here using 'booking'
    }
}
