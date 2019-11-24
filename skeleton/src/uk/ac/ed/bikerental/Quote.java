package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Quote {
    private BikeProvider provider;
    private List<Bike> bikes;
    private BigDecimal price;
    private BigDecimal deposit;

    public Quote(BikeProvider prov, ArrayList<Bike> bikes, BigDecimal prices, BigDecimal deposits ){
	assert (prov != null && bikes != null && prices != null && deposits != null);
	
        this.provider = prov;
	this.bikes = bikes;
        this.price = prices;
        this.deposit = deposits;
    }
    public BikeProvider getBikeProvider(){
        return this.provider;
    }

    public List<Bike> getBikes() {
	return this.bikes;
    }
    
    public BigDecimal getPrice(){
        return this.price;
    }
    public BigDecimal getDeposit(){
        return this.deposit;
    }


}
