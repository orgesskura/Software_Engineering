package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DoubleDecliningPolicy implements ValuationPolicy {
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
	assert bike.purchaseDate.isBefore(date);

	int age = (int) DateRange(bike.getPurchaseDate(), date).toYears();
	BikeType type = bike.getType();

	BigDecimal deprAmount = BigDecimal(2).multiply(type.getDepreciationRate());
	deprAmount = BigDecimal.ONE.minus(deprAmount).pow(age);

	assert deprAmount.compareTo(BigDecimal.ONE) < 0; // if deposit amount would be <= 0

	return deprAmount.multiply(type.getValue());
    }
}
