package com.lucasfrossard.railway.util;

import java.util.Map;

import org.junit.Test;

import com.lucasfrossard.railway.entities.Connection;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test the input processo
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class InputProcessorTest extends TestCase {

	@Test
	public void testInitialize() {
		InputProcessor input = new InputProcessor();
		StationsNetwork stationsNetwork = input.initialize("AB1, BC2");
		Node stationA = stationsNetwork.getExistingInstance("A");
		Node stationB = stationsNetwork.getExistingInstance("B");
		Node stationC = stationsNetwork.getExistingInstance("C");

		// General assertions
		Assert.assertEquals(stationA.getIdentifier(), "A");
		Assert.assertEquals(stationB.getIdentifier(), "B");
		Assert.assertEquals(stationC.getIdentifier(), "C");

		// Assertions related to the connections from station A
		Map<Node, Connection> stAOutgoingConnections = stationA.getOutgoingConnections();
		Assert.assertNotNull(stAOutgoingConnections);
		Connection connectionA2B = stAOutgoingConnections.get(stationB);
		Assert.assertNotNull(connectionA2B);
		Assert.assertEquals(stationA, connectionA2B.getOrigin());
		Assert.assertEquals(stationB, connectionA2B.getDestiny());
		Assert.assertEquals(1, connectionA2B.getDistance());

		// Assertions incoming connections from station B
		Map<Node, Connection> stBIncommingConnections = stationB.getIncommingConnections();
		Assert.assertNotNull(stBIncommingConnections);
		Connection connectionA2BAgain = stBIncommingConnections.get(stationA);
		Assert.assertNotNull(connectionA2BAgain);
		Assert.assertEquals(stationA, connectionA2B.getOrigin());
		Assert.assertEquals(stationB, connectionA2B.getDestiny());
	}

}
