package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
	private String type;
	private BigDecimal originalValue;
	private BigDecimal depreciationRate;
	private BigDecimal rentalRate;

	public BikeType(String types,BigDecimal value,BigDecimal dValue,BigDecimal rentalRate){
		this.originalValue = value;
		this.type = types;
		this.depreciationRate = dValue;
		this.rentalRate = rentalRate;
	}

	//this is redundant
	public BigDecimal getValue() {
		return this.originalValue;
	}

	public BigDecimal getDepreciationRate() {
		return this.depreciationRate;
	}

	public String getType() {
		return this.type;
	}

	public BigDecimal getReplacementValue() {
		return this.originalValue;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}
}
