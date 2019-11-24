package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    private String type;
    private BigDecimal originalValue;
    private BigDecimal depreciationRate;
    
    public BikeType(String types,BigDecimal value,BigDecimal dValue){
         this.originalValue = value;
         this.type = types;
         this.depreciationRate = dValue;
    }
    
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
}
