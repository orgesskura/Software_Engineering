package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
public class Payment {
    //assuming credit card number and  security code are valid
    private Quote quote;
    private Booking booking;
    public Payment(Quote q){
        this.quote = q ;  // create booking out of that...associate each chosen  quote with the payment system
        BookingStatus stat = BookingStatus.AwaitingPayment;
        this.booking = new Booking (this.quote,new BigDecimal(0),stat);//do the order_nr 0 as it is not booked  yet..later use a hashmap for storing everything
    }
    private boolean isValid(BankDetails details){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = details.getExpiryDate();
        return date2.compareTo(date1)>0;
        
    }
    public void doPayment(BankDetails details){
         if(isValid(details)){
             this.booking.status = BookingStatus.PAYMENT_DONE;
             
         }
    }
    
}