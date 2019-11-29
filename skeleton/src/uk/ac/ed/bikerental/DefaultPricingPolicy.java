package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;
public class DefaultPricingPolicy implements PricingPolicy{
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice){
        bikeType.setValue(dailyPrice);
    }
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        BigDecimal price = new BigDecimal(0.0);
        for(Bike bike : bikes){
              BikeType type = bike.getType();
              price = (price.add(type.getValue()).multiply(new BigDecimal(duration.toDays())));
        }
        return price;
    }


}