package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bike {
    private LocalDate manufactureDate;
    private BikeType type;
    private BikeStatus status;
    
    // constructor for bike...add more to it
    public Bike(LocalDate mDate, BikeType type) {
       this.manufactureDate = mDate;
       this.type = type;
       this.status = BikeStatus.AVAILABLE;
    }
    
    public LocalDate getManufactureDate(){
        return this.manufactureDate;
    }
    
    public BikeType getType() {
        return this.type;
    }

    public void setStatus(BikeStatus status) {
	this.status = status;
    }

    public BikeStatus getStatus() {
	return this.status;
    }
}

enum BikeStatus {
    AVAILABLE,
    DELIVERING,
    UNAVAILABLE,
    RETURNING
}
