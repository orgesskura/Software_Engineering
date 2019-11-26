package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
public class Quote {
    private BikeProvider provider;
    private BikeType type;
    private BigDecimal price;
    private BigDecimal deposit;
    private LocalDate date;
    public Quote(BikeProvider prov, BikeType types, BigDecimal prices, BigDecimal deposits,LocalDate Date ){
        this.provider = prov;
        this.type = types;
        this.price = prices;
        this.deposit = deposits;
        this.date = Date;
    }
    public LocalDate getDate(){
        return this.date;
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