package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    private String type;
    private BigDecimal originalValue;
    private BigDecimal depreciationRate;
    private BigDecimal daily_price;
    private BigDecimal deposit_amount; // we implement this with the default deposit rate calculator interaface
    public BikeType(String types,BigDecimal value,BigDecimal dValue,BigDecimal DailyPrice){
         this.originalValue = value;
         this.type = types;
         this.depreciationRate = dValue;
         this.daily_price = DailyPrice;//implement the daily price interface in the biketype
    }
    public BigDecimal getValue(){
        return this.originalValue;
    }
    public BigDecimal getDepreciationRate(){
        return this.depreciationRate;
    }
    public String getType(){
        return this.type;
    }
    
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        assert false;
        return null;
    }
}