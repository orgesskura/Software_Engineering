package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class BikeProvider extends User {
  private String name;
  private Location location;
  private Set<BikeProvider> partners;
  private List<String> openingHours;

  public BikeProvider(String name, Location location, List<String> openingHours, Set<BikeProvider> partners) {
       super(name, location);
       this.openingHours = openingHours;
       this.partners = partners;
  }

    public List<String> getOpeningHours() {
	return this.openingHours;
    }

    public Set<BikeProvider> getPartners() {
	return this.partners;
    }

    public boolean hasPartner(BikeProvider partner) {
	return this.partners.contains(partner);
    }

    public void addPartner(BikeProvider partner) {
	assert partner != null;

	if (!this.partners.contains(partner)) {
	    this.partners.add(partner);
	    partner.addPartner(this);
	}
    }
}
