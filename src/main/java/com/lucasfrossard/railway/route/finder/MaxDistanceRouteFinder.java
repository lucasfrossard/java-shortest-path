package com.lucasfrossard.railway.route.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Trip;

/**
 * Find routes with the max distance given
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class MaxDistanceRouteFinder extends AbstractRouteFinder {

	/**
	 * Max distance allowed in the route
	 */
	private int maxAllowedDistance;

	/**
	 * Constructor
	 * 
	 * @param trip
	 *            the origin of the route and the destiny of the route
	 * @param maxAllowedDistance
	 *            max distance allowed
	 */
	public MaxDistanceRouteFinder(Trip trip, int maxAllowedDistance) {
		super(trip);
		if (maxAllowedDistance <= 0) {
			throw new RuntimeException("Max allowed distance should be a non-zero positive number!");
		}
		this.maxAllowedDistance = maxAllowedDistance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Route> findRoutes() {
		Set<Node> keySet = this.getOrigin().getOutgoingConnections().keySet();
		List<Route> routes = new ArrayList<Route>();

		for (Node station : keySet) {
			if (keySet.contains(this.getDestiny())) {
				Route route = new Route();
				Connection connection = this.getOrigin().getOutgoingConnections().get(this.getDestiny());
				route.addFront(connection);
				if (route.getTotalDistance() < maxAllowedDistance) {
					routes.add(route);
				}
				// Cria lista e retorna
			}
			Connection connection = this.getOrigin().getOutgoingConnections().get(station);

			int currentMaxAllowed = this.maxAllowedDistance - connection.getDistance();
			if (currentMaxAllowed > 0) {
				Trip tripFromTheNextStationToTheDestiny = new Trip(station, getDestiny());
				MaxDistanceRouteFinder routeFinder = new MaxDistanceRouteFinder(tripFromTheNextStationToTheDestiny,
						currentMaxAllowed);
				List<Route> intermediateRoutes = routeFinder.findRoutes();
				if (intermediateRoutes != null) {
					this.addStationToAvailableRoutes(connection, intermediateRoutes);
					routes.addAll(intermediateRoutes);
				}
			}

		}

		return routes;
	}
}
