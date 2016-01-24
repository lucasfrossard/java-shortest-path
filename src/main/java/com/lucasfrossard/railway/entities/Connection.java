package com.lucasfrossard.railway.entities;

/**
 * Represents a connection between two consecutive stations
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class Connection {

	/**
	 * origin station
	 */
	private Node origin;

	/**
	 * Distance between the the origin and the destiny
	 */
	private int distance;

	/**
	 * Destiny station
	 */
	private Node destiny;

	public Node getOrigin() {
		return origin;
	}

	public Connection(Node origin, int distance, Node destiny) {
		this.origin = origin;
		this.distance = distance;
		this.destiny = destiny;
	}

	public void setOrigin(Node origin) {
		this.origin = origin;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Node getDestiny() {
		return destiny;
	}

	public void setDestiny(Node destiny) {
		this.destiny = destiny;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Connection) {
			Connection other = (Connection) o;
			return this.getOrigin().equals(other.getOrigin()) && this.getDestiny().equals(other.getDestiny())
					&& this.getDistance() == other.getDistance();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.origin.hashCode() + this.destiny.hashCode() + this.distance;
	}

}
