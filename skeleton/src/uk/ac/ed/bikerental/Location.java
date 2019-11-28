package uk.ac.ed.bikerental;

import java.util.Objects;

/**
 * <code>Location</code> class represents a real life location, and allows comparisons between
 * other <code>Location</code> classes
 */
public class Location {
	/**
	 * <code>postcode</code> is the postcode of the <code>Location</code>
	 */
	private String postcode;

	/**
	 * <code>address</code> is the address of the <code>Location</code> in real life
	 */
	private String address;

	/**
	 * Constructs the <code>Location</code> class with all attributes specified
	 *
	 * @param postcode a plaintext string of the location's postcode
	 * @param address a plaintext string of the location's address
	 */
	public Location(String postcode, String address) {
		assert postcode.length() >= 6;
		this.postcode = postcode;
		this.address = address;
	}

	/**
	 * Determines whether two locations are close together
	 *
	 * @param other the <code>Location</code> to check for closeness
	 *
	 * @return <code>true</code> if the <code>Location other</code> is close to <code>this</code>
	 * @throws NullPointerException if the other <code>Location</code> is <code>null</code>
	 */
	public boolean isNearTo(Location other) {
		Objects.requireNonNull(other);

		return this.postcode.equals(other.getPostcode());
	}

	/**
	 * Gets the postcode of this location
	 *
	 * @return the postcode stored in the <code>Location</code> object
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Gets the address of this location
	 *
	 * @return the address stored in the <code>Location</code> object
	 */
	public String getAddress() {
		return address;
	}

	// You can add your own methods here
}
