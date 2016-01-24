package com.lucasfrossard.railway.entities;

import java.util.HashMap;
import java.util.Map;

public class StationsNetwork {
	/**
	 * Keeps a single instance of each station
	 */
	private Map<String, Node> instances = new HashMap<String, Node>();

	/**
	 * Get the instance of the station specified by the name. If does not exist
	 * yet, it is created.
	 * 
	 * @param identifier
	 *            identifier of the destiny node
	 * @return
	 */
	public Node instantiate(String identifier) {
		Node node = this.instances.get(identifier);
		if (node == null) {
			node = new Node(identifier);
			instances.put(identifier, node);
		}
		return node;
	}
	
	/**
	 * Get an existing instance of a station, or return null if it does not
	 * exists
	 * 
	 * @param identifier
	 *            identifier of the station
	 * @return an station
	 */
	public Node getExistingInstance(String identifier) {
		Node node = this.instances.get(identifier);
		return node;
	}
}
