package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bike implements Deliverable {
    private LocalDate manufactureDate;
    private BikeType type;
    private int id;
    private BikeStatus status;
    
    
    // constructor for bike...add more to it
    public Bike(LocalDate mDate, BikeType type, int id) {
       this.manufactureDate = mDate;
       this.type = type;
       this.status = BikeStatus.AVAILABLE;
        this.id = id;
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

    public void onPickup() {
	assert this.status == BikeStatus.FOR_DELIVERY || this.status == BikeStatus.FOR_RETURN;
	
	if (this.status == BikeStatus.FOR_DELIVERY) {
	    this.status = BikeStatus.DELIVERING;
	} else if (this.status == BikeStatus.FOR_RETURN) {
	    this.status = BikeStatus.RETURNING;
	}
    }

    public void onDropoff() {
	assert this.status == BikeStatus.DELIVERING || this.status == BikeStatus.RETURNING;
	if (this.status == BikeStatus.DELIVERING) {
	    this.status = BikeStatus.UNAVAILABLE;
	} else if (this.status == BikeStatus.RETURNING) {
	    this.status = BikeStatus.AVAILABLE;
	}
    }
}

enum BikeStatus {
    AVAILABLE,
    FOR_DELIVERY,
    DELIVERING,
    UNAVAILABLE,
    FOR_RETURN,
    RETURNING
}
