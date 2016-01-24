package com.lucasfrossard.railway.route.finder;

import java.util.ArrayList;
import java.util.List;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Trip;

/**
 * Find routes with the exactly number of stops given on the constructor
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class ExactlyStopsRouteFinder extends MaxStopsRouteFinder {

	/**
	 * Constructor
	 * 
	 * @param origin
	 *            origin of the travel
	 * @param destiny
	 *            destiny of the travel
	 * @param exactlyNumberOfStop
	 *            desired number of stops
	 */
	public ExactlyStopsRouteFinder(Trip trip, int exactlyNumberOfStop) {
		super(trip, exactlyNumberOfStop);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Route> findRoutes() {
		List<Route> exactlyNumberOfStops = new ArrayList<Route>();
		List<Route> foundRoutes = super.findRoutes();
		for (Route route : foundRoutes) {
			List<Connection> connections = route.getConnections();
			if (connections.size() == this.getMaxStops()) {
				exactlyNumberOfStops.add(route);
			}
		}
		return exactlyNumberOfStops;

	}

}
