package com.lucasfrossard.railway.route.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.ShortestRoutes;
import com.lucasfrossard.railway.entities.Trip;

/**
 * Calculate the shortest route so far
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class ShortestRouteFinder extends AbstractRouteFinder {

	/**
	 * Constructor
	 * 
	 * @param trip
	 *            the origin and the destiny
	 */
	public ShortestRouteFinder(Trip trip) {
		super(trip);
	}

	@Override
	public List<Route> findRoutes() {
		Route calculateShortestPath = this.calculateShortestPath();
		List<Route> routes = new ArrayList<Route>();
		routes.add(calculateShortestPath);
		return routes;
	}

	private Route calculateShortestPath() {
		ShortestRoutes shortestRoute = new ShortestRoutes();

		Map<Node, Connection> incommingConnections = this.getDestiny().getIncommingConnections();
		Connection connection = incommingConnections.get(this.getOrigin());
		Trip trip = new Trip(this.getOrigin(), this.getDestiny());
		if (connection != null) {
			// found the destiny, this is the sortest path then
			Route route = new Route();
			route.addFront(connection);
			shortestRoute.storeShortestRoute(trip, route);
		} else {
			for (Node adjacent : incommingConnections.keySet()) {
				Trip adjacentTripToDestination = new Trip(adjacent, this.getDestiny());
				Route route = new Route();
				Connection incommingConnection = incommingConnections.get(adjacent);
				route.addFront(incommingConnection);
				shortestRoute.storeShortestRoute(adjacentTripToDestination, route);
				this.calculateShortesPath(adjacent, this.getDestiny(), this.getOrigin(), shortestRoute);
			}
		}
		return shortestRoute.getShortestRoute(trip);

	}

	/**
	 * Calculate the shortest path
	 * 
	 * @param adjacentStation
	 *            previous station
	 * @param finalDestiny
	 *            final destiny
	 * @param sourceOrigin
	 *            origin of the train
	 * @param shortestRoutes
	 *            calculate shortest routes so far
	 */
	private void calculateShortesPath(final Node adjacentStation, final Node finalDestiny, final Node sourceOrigin,
			ShortestRoutes shortestRoutes) {
		Set<Node> possiblePreviousStations = adjacentStation.getIncommingConnections().keySet();
		// create object trip from source origin to here
		Trip tripFromhere = new Trip(adjacentStation, finalDestiny);
		// Get the shortest route from source origin to here
		Route shortestRouteFromHere = shortestRoutes.getShortestRoute(tripFromhere);

		// CHECK IF IT'S THE ORIGIN, IF IT IS, I DON'T NEED TO CHECK OTHERS
		// REACHABLE STATIONS, CAUSE WE FOUND IT!
		if (!adjacentStation.equals(sourceOrigin)) {
			// Calculate routes from previous steps

			for (Node previousStation : possiblePreviousStations) {
				// Create a new route, it is a route from the source passing
				// here
				Route routeFromThePreviousStation = new Route();
				routeFromThePreviousStation.add(shortestRouteFromHere);
				// ... getting to the next station
				Connection connection = adjacentStation.getIncommingConnections().get(previousStation);
				routeFromThePreviousStation.addFront(connection);
				// add this route to the shortest routes
				Trip tripFromThePreviousStation = new Trip(previousStation, finalDestiny);
				Route shortestRegisteredRouteFromPreviousStation = shortestRoutes
						.getShortestRoute(tripFromThePreviousStation);
				/**
				 * if there is a shorter path already registered that get to
				 * here, we don't need check the incoming stations
				 */
				if (shortestRegisteredRouteFromPreviousStation != null && shortestRegisteredRouteFromPreviousStation
						.getTotalDistance() < routeFromThePreviousStation.getTotalDistance()) {

				} else {

					shortestRoutes.storeShortestRoute(tripFromThePreviousStation, routeFromThePreviousStation);
					this.calculateShortesPath(previousStation, finalDestiny, sourceOrigin, shortestRoutes);
				}
			}
		}
	}

}
