package com.lucasfrossard.railway.trip;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.route.finder.NoSuchRouteException;

/**
 * Calculate the distance covered when some destinations are reached
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class DistanceCalculator {

	StationsNetwork stationsNetwork;

	/**
	 * Constructor
	 * 
	 * @param stationsNetwork
	 *            contains the station network
	 */
	public DistanceCalculator(StationsNetwork stationsNetwork) {
		this.stationsNetwork = stationsNetwork;
	}

	/**
	 * Get the total covered distance
	 * 
	 * @param destinations
	 *            name of the destinations
	 * @return the total distance
	 * @throws NoSuchRouteException
	 *             if a route to be covered is not found
	 */
	public int calculateDistance(String... destinations) throws NoSuchRouteException {
		int distance = 0;
		Node currentStation = null;
		Node nextStation = null;
		for (String destinationName : destinations) {
			if (currentStation == null) {
				currentStation = stationsNetwork.getExistingInstance(destinationName);
			} else {
				nextStation = stationsNetwork.getExistingInstance(destinationName);
				Connection Connection = currentStation.findDirectOutgoingConnection(nextStation);
				if (Connection == null) {
					throw new NoSuchRouteException(
							"Cannot find a route to " + destinationName + " from " + currentStation.getIdentifier());
				}
				distance += Connection.getDistance();
				currentStation = nextStation;
			}
		}
		return distance;
	}
}
