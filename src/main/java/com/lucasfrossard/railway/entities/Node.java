package com.lucasfrossard.railway.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node an its connection
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class Node {

	/**
	 * Identifier of the node
	 */
	private String identifier;

	/**
	 * nodes that can be reached from this node
	 */
	private Map<Node, Connection> outgoingConnections = new HashMap<Node, Connection>();

	/**
	 * nodes that can be reache this node
	 */
	private Map<Node, Connection> incommingConnections = new HashMap<Node, Connection>();

	/**
	 * Constructor
	 * 
	 * @param identifier
	 *            name of the node (cannot be null)
	 */
	Node(String identifier) {
		if (identifier == null) {
			throw new RuntimeException("Name cannot be null");
		}
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Get the distance from the current node, to the specified node if
	 * they are directly connected. If they are not, returns null.
	 * 
	 * @param destiny
	 *            the destiny
	 * @return the weight of the that node
	 */
	public Connection findDirectOutgoingConnection(Node destiny) {
		return this.outgoingConnections.get(destiny);
	}

	/**
	 * Get the distance from the current node, to the specified node if
	 * they are directly connected. If they are not, returns null.
	 * 
	 * @param destiny
	 *            the destiny
	 * @return the weight of the that node
	 */
	public Connection findDirectIncommingConnection(Node destiny) {
		return this.incommingConnections.get(destiny);
	}

	/**
	 * Get the distance from the current node, to the specified node if
	 * they are directly or indirectly connected. If they are not reachable,
	 * returns null.
	 * 
	 * @param destiny
	 *            the destiny
	 * @return the weight of the that node
	 */
	public Connection reach(Node destiny) {
		return this.outgoingConnections.get(destiny);
	}

	public Map<Node, Connection> getOutgoingConnections() {
		return outgoingConnections;
	}

	/**
	 * Add a connection to this node
	 * 
	 * @param destiny
	 *            a node that can be reached from this node
	 * @param weight
	 *            the distance between them
	 */
	public void addOutgoingConnection(Node destiny, int distance) {
		this.outgoingConnections.put(destiny, new Connection(this, distance, destiny));
	}

	/**
	 * Add a connection to this node
	 * 
	 * @param source
	 *            a node that can reach this
	 * @param weight
	 *            the distance between them
	 */
	public void addIncommingConnection(Node source, int distance) {
		this.incommingConnections.put(source, new Connection(source, distance, this));
	}

	public Map<Node, Connection> getIncommingConnections() {
		return incommingConnections;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Node) {
			Node aNode = (Node) object;
			return this.identifier.equals(aNode.getIdentifier());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}

}
