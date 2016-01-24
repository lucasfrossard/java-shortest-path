package com.lucasfrossard.railway.route.finder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Trip;

/**
 * This defines the
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public abstract class AbstractRouteFinder {

	// TODO in the future, chnage to use the Trip object here, replacing those
	// two nodes
	/***
	 * The origin and destiny of the route
	 */
	private Trip trip;

	/**
	 * Constructor
	 * 
	 * @param origin
	 *            the origin
	 * @param destiny
	 *            the destiny
	 */
	public AbstractRouteFinder(Trip trip) {
		this.trip = trip;
	}

	/**
	 * Gets the origin
	 */
	public Node getOrigin() {
		return this.trip.getOrigin();
	}

	/**
	 * Gets the destiny
	 * 
	 * @return the destiny node
	 */
	public Node getDestiny() {
		return this.trip.getDestiny();
	}

	protected void addStationToAvailableRoutes(Connection connection, List<Route> intermediateRoutes) {
		for (Route route : intermediateRoutes) {
			route.addFront(connection);
		}
	}

	/**
	 * Deduplicate itens in the list
	 * 
	 * @param routesList
	 *            list of routes
	 * @return a set of routes
	 */
	public Set<Route> deduplicate(List<Route> routesList) {
		Set<Route> routes = new HashSet<Route>();
		for (Route route : routesList) {
			routes.add(route);
		}
		return routes;

	}

	/*
	 * ABSTRACT METHODS
	 */

	/**
	 * Find routes
	 * 
	 * @return routes found
	 */
	public abstract List<Route> findRoutes();
}
