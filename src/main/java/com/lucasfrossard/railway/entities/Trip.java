package com.lucasfrossard.railway.entities;

/**
 * This is just a depositary class that represents a trip. A trip is nothing but
 * an origin and a destiny
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class Trip {

	/**
	 * The origin
	 */
	private Node origin;

	/**
	 * The destiny
	 */
	private Node destiny;

	public Trip(Node origin, Node destiny) {
		if (origin == null && destiny == null) {
			throw new RuntimeException("Origin and destiny cannot be null!");
		}
		this.origin = origin;
		this.destiny = destiny;
	}

	/**
	 * Gets the origin
	 * 
	 * @return the origin
	 */
	public Node getOrigin() {
		return origin;
	}

	/**
	 * Gets the destiny
	 * 
	 * @return the destiny
	 */
	public Node getDestiny() {
		return destiny;

	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Trip) {
			Trip so = (Trip) object;
			return this.getOrigin().equals(so.getOrigin()) && this.getDestiny().equals(this.getDestiny());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.origin.hashCode() + this.destiny.hashCode();
	}

}
