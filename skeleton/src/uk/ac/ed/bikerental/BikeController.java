package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class BikeController {
    private Map<Bike, BikeProvider> bikes;
    private Map<Bike, DateRange> unavailableBikes;

    public BikeController(HashMap<Bike, BikeProvider> bikes, HashMap<Bike, DateRange> unavailable) {
	this.bikes = bikes;
	this.unavailableBikes = unavailable;
    }

    public void addBike(Bike bike, BikeProvider provider) {
	this.bikes.put(bike, provider);
    }

    public void makeAvailable(Bike bike) {
	bike.setStatus(BikeStatus.AVAILABLE);
    }

    public void makeUnavailable(Bike bike, DateRange during) {
	bike.setStatus(BikeStatus.UNAVAILABLE);

	this.unavailableBikes.put(bike, during);
    }

    public List<Bike> getAvailableBikes() {
	ArrayList<Bike> available = new ArrayList<Bike>();
	
	for (Bike bike : bikes.keySet()) {
	    if (!this.unavailableBikes.containsKey(bike)) {
		available.add(bike);
	    }
	}

	return available;
    }
}
