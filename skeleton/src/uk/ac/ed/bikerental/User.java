package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private String name;
    private Location location;
    public User(String nam,Location locations){
        this.name = nam;
        this.location = locations;
    }
    public String getName(){
        return this.name;
    }
    public Location getLocation(){
        return this.location;
    }



}
