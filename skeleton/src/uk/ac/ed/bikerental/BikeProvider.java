package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;
public class BikeProvider extends User{
  private String name;
  private Location location;
  private ArrayList<BikeProvider> partnersWith;
  private ArrayList<String> openingHours;
  public BikeProvider(String nam,Location locations,ArrayList<String> openingHour,ArrayList<BikeProvider> partners){
       super(nam,locations);
       this.openingHours = openingHour;
       this.partnersWith = partners;
  }

  //Sami...Implement the getter and setters or any other additional stuff
 

}