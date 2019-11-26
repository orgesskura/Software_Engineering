package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class Booking {
   private Quote quoteDetails;
   private BigDecimal orderNr;
   public BookingStatus status;
   public Booking(Quote quote,BigDecimal order_nr,BookingStatus statuss){
       this.quoteDetails = quote;
       this.orderNr = order_nr;
       this.status = statuss;
   }
   public Quote getQuote(){
       return this.quoteDetails;
   }
   public BigDecimal getOrderNr(){
       return this.orderNr;
   }

}