package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private String name;
    private Location location;

    public User(String name, Location location){
        this.name = name;
        this.location = location;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Location getLocation(){
        return this.location;
    }
}
