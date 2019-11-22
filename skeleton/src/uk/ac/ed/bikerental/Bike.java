package uk.ac.ed.bikerental;
import java.time.LocalDate;

public class Bike {
    private LocalDate purchaseDate;
    // constructor for bike...add more to it
    public Bike(LocalDate PurchaseDate){
       this.purchaseDate = PurchaseDate;
    }
    public LocalDate getPurchaseDate(){
        return this.purchaseDate;
    }
    
    public BikeType getType() {
        // TODO: Implement Bike.getType
        assert false;
        return null;
    }
}