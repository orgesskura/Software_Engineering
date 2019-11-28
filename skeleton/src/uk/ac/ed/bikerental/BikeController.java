package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.*;

public class BikeController {
	private Map<Bike, BikeProvider> bikes;

	public BikeController(HashMap<Bike, BikeProvider> bikes, HashMap<Bike, DateRange> unavailable) {
		this.bikes = bikes;
	}

	public void addBike(Bike bike, BikeProvider provider) {
		assert bike != null;
		assert provider != null;

		this.bikes.put(bike, provider);
	}

	public void makeAvailable(Bike bike) {
		assert bike != null;

		bike.setStatus(BikeStatus.AVAILABLE);
	}

	public void makeUnavailable(Bike bike, DateRange during) {
		assert bike != null;
		assert during != null;

		bike.setStatus(BikeStatus.UNAVAILABLE);
	}

	public List<Bike> getAvailableBikes() {
		ArrayList<Bike> available = new ArrayList<Bike>();

		for (Bike bike : bikes.keySet()) {
			if (bike.getStatus() == BikeStatus.AVAILABLE) {
				available.add(bike);
			}
		}

		return available;
	}

	public List<Bike> getMatchingAvailableBikes(BikeProvider provider, HashMap<BikeType, Integer> required) {
		ArrayList<Bike> matches = new ArrayList<Bike>();
		Integer needed;

		for (Bike bike : this.bikes.keySet()) {
			if (providerFor(bike) == provider) {
				needed = required.get(bike.getType());

				if (needed != null && needed > 0) {
					matches.add(bike);
					required.put(bike.getType(), needed-1);
				}
			}
		}

		for (BikeType type : required.keySet()) {
			if (required.get(type) > 0) return null;
		}

		return matches;
	}


	public BikeProvider providerFor(Bike bike) {
		assert bike != null;

		return this.bikes.get(bike);
	}
}
