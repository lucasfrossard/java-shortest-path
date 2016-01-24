package com.lucasfrossard.railway.entities;

import java.util.LinkedList;
import java.util.List;

public class Route {

	private int totalDistance;

	List<Connection> connections = new LinkedList<Connection>();

	public void addFront(Connection connection) {
		this.totalDistance += connection.getDistance();
		this.connections.add(0, connection);
	}

	public void add(Route route) {
		this.totalDistance = route.getTotalDistance();
		this.connections.addAll(route.getConnections());
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public Node getOrigin() {
		return this.connections.get(0).getOrigin();
	}

	public Node getDestiny() {
		int size = this.connections.size() - 1;
		return this.connections.get(size).getDestiny();
	}

	public List<Connection> getConnections() {
		return this.connections;
	}

	public void add(Connection connection) {
		this.totalDistance += connection.getDistance();
		this.connections.add(connection);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Route) {
			Route other = (Route) o;
			List<Connection> otherConnections = other.getConnections();
			if (other.getTotalDistance() == this.getTotalDistance()
					&& otherConnections.size() == this.connections.size()
					&& other.getDestiny().equals(this.getDestiny()) && other.getOrigin().equals(this.getOrigin())) {
				for (int i = 0; i < this.connections.size(); i++) {
					if (!this.connections.get(i).equals(otherConnections.get(i))) {
						return false;
					}

				}
				return true;

			}
		}
		return false;
	}

	@Override
	public int hashCode (){
		return this.getOrigin().hashCode()+this.getDestiny().hashCode()+this.getTotalDistance();
	}

}
