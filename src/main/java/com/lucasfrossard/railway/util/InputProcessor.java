package com.lucasfrossard.railway.util;

import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;

/**
 * Process a given input to initialize the necessary data
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class InputProcessor {
	/**
	 * Initialize the input
	 * 
	 * @param input
	 *            the input string should be something like AB5, BC4, CD8, DC8,
	 *            DE6, AD5, CE2, EB3, AE7 where foreach item on the list, the
	 *            first letter is an origin station, the second one is a
	 *            destination station and the third number is a distance
	 */
	public StationsNetwork initialize(String input) {
		StationsNetwork stationsNetwork = new StationsNetwork();
		String[] connections = input.split(",");
		for (String connection : connections) {
			connection = connection.trim();
			Node origin = stationsNetwork.instantiate(connection.substring(0, 1));
			Node destiny = stationsNetwork.instantiate(connection.substring(1, 2));
			String distance = connection.substring(2);
			Integer intDistance = Integer.valueOf(distance);
			origin.addOutgoingConnection(destiny, intDistance);
			destiny.addIncommingConnection(origin, intDistance);
		}
		return stationsNetwork;
	}

}
