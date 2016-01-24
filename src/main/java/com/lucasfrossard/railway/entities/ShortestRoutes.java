package com.lucasfrossard.railway.entities;

import java.util.HashMap;
import java.util.Map;

public class ShortestRoutes {

	private Map<Trip, Route> shortestRoutes = new HashMap<Trip, Route>();

	/**
	 * Get shortest route for a given trip
	 * 
	 * @param shortest
	 *            route
	 * @return
	 */
	public Route getShortestRoute(Trip shortest) {
		return this.shortestRoutes.get(shortest);
	}

	/**
	 * This will register a route for this trip only if no route exist yet or if
	 * the given route is shorter.
	 * 
	 * @param shortest
	 *            route
	 * @return
	 */
	public void storeShortestRoute(Trip shortest, Route route) {
		Route existingRoute = shortestRoutes.get(shortest);
		if (existingRoute == null) {
			this.shortestRoutes.put(shortest, route);
		} else {
			if (existingRoute.getTotalDistance() > route.getTotalDistance()) {
				this.shortestRoutes.put(shortest, route);
			}
		}
	}
}
