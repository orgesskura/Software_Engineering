package uk.ac.ed.bikerental;
import java.time.LocalDate;

public class Bike {
    private LocalDate manufactureDate;
    // constructor for bike...add more to it
    public Bike(LocalDate mDate){
       this.manfacture = mDate;
    }
    public LocalDate getManufactureDate(){
        return this.manufactureDate;
    }
    
    public BikeType getType() {
        // TODO: Implement Bike.getType
        assert false;
        return null;
    }
}