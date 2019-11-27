package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class BikeProvider extends User{
  private String name;
  private Location location;
  private Set<BikeProvider> partnersWith;
  private List<String> openingHours;

  public BikeProvider(String name, Location location, List<String> openingHours, Set<BikeProvider> partners) {
       super(name, location);
       this.openingHours = openingHours;
       this.partnersWith = partners;
  }

    public List<String> getOpeningHours() {
	return this.openingHours;
    }

    public List<BikeProvider> getPartners() {
	return this.partnersWith;
    }

    public void addPartner(BikeProvider partner) {
	assert partner != null;

	if (!this.contains(partner)) {
	    this.partnersWith.add(partner);
	    partner.addPartner(this);
	}
    }
}
