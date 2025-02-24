package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DoubleDecliningPolicy implements ValuationPolicy {
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        DateRange dates = new DateRange(bike.getManufactureDate(), date);

        int age = (int) dates.toYears();
        BikeType type = bike.getType();

        BigDecimal deprAmount = new BigDecimal(2).multiply(type.getDepreciationRate());
        deprAmount = (BigDecimal.ONE.subtract(deprAmount)).pow(age);

        return deprAmount.multiply(type.getValue());
    }
}
