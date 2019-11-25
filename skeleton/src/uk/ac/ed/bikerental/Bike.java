package uk.ac.ed.bikerental;
import java.time.LocalDate;

public class Bike {
    private LocalDate manufactureDate;
    private BikeType type;
    private int id;
    private BikeStatus status;
    
    
    // constructor for bike...add more to it
    public Bike(LocalDate mDate,BikeType  types){
       this.manufactureDate = mDate;
       this.type = types;
    }
    public LocalDate getManufactureDate(){
        return this.manufactureDate;
    }
    
    public BikeType getType() {
        // TODO: Implement Bike.getType
        return this.type;
    }
}