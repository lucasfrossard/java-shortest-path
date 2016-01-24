package com.lucasfrossard.railway.route.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Trip;

/**
 * This class is reponsible for dealing with the problem of find a route with
 * the maximum numbers of stops defined in the constructor
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class MaxStopsRouteFinder extends AbstractRouteFinder {

	/**
	 * Max stops
	 */
	private int maxStops;

	/**
	 * Constructor
	 * 
	 * @param trip
	 *            the origin and the destiny
	 * @param maxStops
	 *            max number of stops allowed
	 */
	public MaxStopsRouteFinder(Trip trip, int maxStops) {
		super(trip);
		if (maxStops <= 0) {
			throw new RuntimeException("Max allowed distance should be a non-zero positive number!");
		}
		this.maxStops = maxStops;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Route> findRoutes() {
		Set<Node> keySet = this.getOrigin().getOutgoingConnections().keySet();
		List<Route> routes = new ArrayList<Route>();

		for (Node station : keySet) {
			if (station.equals(this.getDestiny())) {
				Route route = new Route();
				Connection connection = this.getOrigin().getOutgoingConnections().get(this.getDestiny());
				route.addFront(connection);
				routes.add(route);
			}
			if (this.maxStops > 1) {
				Trip tripFromNextStationToTheDestiny = new Trip(station, getDestiny());
				MaxStopsRouteFinder routeFinder = new MaxStopsRouteFinder(tripFromNextStationToTheDestiny, this.maxStops - 1);
				List<Route> intermediateRoutes = routeFinder.findRoutes();
				Connection connection = this.getOrigin().getOutgoingConnections().get(station);
				if (intermediateRoutes != null && !intermediateRoutes.isEmpty()) {
					this.addStationToAvailableRoutes(connection, intermediateRoutes);
					routes.addAll(intermediateRoutes);
				}
			}
		}
		return routes;

	}

	/**
	 * Maximum number of stops
	 * 
	 * @return maximum number of stops
	 */
	public int getMaxStops() {
		return maxStops;
	}

}
