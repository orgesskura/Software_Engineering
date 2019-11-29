package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;
public class MockPricingPolicy implements PricingPolicy{
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice){
        bikeType.setValue(dailyPrice);
    }
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        BigDecimal toto = new BigDecimal(0.0);
        for(Bike b  : bikes){
            BigDecimal kar = b.getType().getReplacementValue();
            BigDecimal range  = new BigDecimal ( duration.toDays());
            kar = kar.multiply(range);
            toto = toto.add(kar);
        }
        BigDecimal discount = discount(duration);
        BigDecimal one = new BigDecimal(1);
        BigDecimal totdiscount = one.subtract(discount);
        toto= toto.multiply(totdiscount);
        return toto;
    }

    public BigDecimal discount(DateRange duration){
        long days = duration.toDays();
        if(days<3) return new BigDecimal(0.0);
        else if(days>2 && days < 7) return new BigDecimal((double)5/100);
        else if (days>6 && days < 14) return new BigDecimal(0.01);
        else return new BigDecimal(0.15);
    }



}
