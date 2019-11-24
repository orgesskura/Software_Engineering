package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class Quote {
    private BikeProvider provider;
    private BikeType type;
    private BigDecimal price;
    private BigDecimal deposit;

    // this should have a list of bikes instead of a biketype
    public Quote(BikeProvider prov, BikeType types, BigDecimal prices, BigDecimal deposits ){
        this.provider = prov;
        this.type = types;
        this.price = prices;
        this.deposit = deposits;
    }
    public BikeProvider getBikeProvider(){
        return this.provider;
    }
    public BikeType getBikeType(){
        return this.type;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public BigDecimal getDeposit(){
        return this.deposit;
    }


}
