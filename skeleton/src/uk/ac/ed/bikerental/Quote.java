package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;
// 
public class Quote {
	private BikeProvider provider;
	private Set<BikeType> bikeTypes;
	private BigDecimal price;
	private BigDecimal deposit;
	private DateRange dates;
    private List<Bike> bikes;
	public Quote(BikeProvider prov, Set<BikeType> bikes, BigDecimal prices, BigDecimal deposits, DateRange date,List<Bike> bike){
		assert (prov != null && bikes != null && prices != null && deposits != null);

		this.provider = prov;
		this.bikeTypes = bikes;
		this.price = prices;
		this.deposit = deposits;
		this.dates = date;
		this.bikes = bike;
	}
	public DateRange getDates(){
		return this.dates;
	}
	public BikeProvider getBikeProvider(){
		return this.provider;
	}

	public Set<BikeType> getBikeTypes() {
		return this.bikeTypes;
	}
	public List<Bike> getBikes(){
		return this.bikes;
	}

	public BigDecimal getPrice(){
		return this.price;
	}
	public BigDecimal getDeposit(){
		return this.deposit;
	}


}
