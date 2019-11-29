package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;
import java.time.LocalTime;

public class BikeProvider extends User {
	private String name;
	private Location location;
	private Set<BikeProvider> partners;
	private Map<DaysOfWeek,ArrayList<LocalTime>> openingHours;
	private HashMap<BikeType,Integer> bikesNr;//current available nr of bikeTypes
	private PricingPolicy pricingPolicy;
	private ValuationPolicy valuationPolicy;
	private BigDecimal depositRate;

	public BikeProvider(String name, Location location, Map<DaysOfWeek,ArrayList<LocalTime>> openingHours, Set<BikeProvider> partners, PricingPolicy pricingPolicy, ValuationPolicy valuationPolicy, BigDecimal depositRate) {
		super(name, location);
		this.openingHours = openingHours;
		this.partners = partners;
		this.pricingPolicy = pricingPolicy;
		this.valuationPolicy = valuationPolicy;
		this.depositRate = depositRate;
	}

	public Map<DaysOfWeek,ArrayList<LocalTime>> getOpeningHours() {
		return this.openingHours;
	}

	public Set<BikeProvider> getPartners() {
		return this.partners;
	}

	public boolean hasPartner(BikeProvider partner) {
		return this.partners.contains(partner);
	}

	public void addPartner(BikeProvider partner) {
		assert partner != null;

		if (!this.partners.contains(partner)) {
			this.partners.add(partner);
			partner.addPartner(this);
		}
	}

	public void setPricingPolicy(PricingPolicy policy) {
		this.pricingPolicy = policy;
	}

	public PricingPolicy getPricingPolicy() {
		return this.pricingPolicy;
	}

	public void setValuationPolicy(ValuationPolicy policy) {
		this.valuationPolicy = policy;
	}

	public ValuationPolicy getValuationPolicy() {
		return this.valuationPolicy;
	}

	public void setDepositRate(BigDecimal newRate) {
		this.depositRate = newRate;
	}

	public BigDecimal getDepositRate() {
		return this.depositRate;
	}
}
