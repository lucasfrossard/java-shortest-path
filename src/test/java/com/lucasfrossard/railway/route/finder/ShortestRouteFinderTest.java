package com.lucasfrossard.railway.route.finder;

import java.util.List;

import org.junit.Test;

import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.entities.Trip;
import com.lucasfrossard.railway.util.InputProcessor;

import junit.framework.Assert;

/**
 * Test for the shortest route
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</a>
 *
 */
public class ShortestRouteFinderTest {

	@Test
	public void testFindRoutes() {
		InputProcessor processor = new InputProcessor();
		StationsNetwork stationsNetwork = processor.initialize("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Node stationB = stationsNetwork.getExistingInstance("B");
		Trip btoB = new Trip(stationB, stationB);
		ShortestRouteFinder shortestRouteFinder = new ShortestRouteFinder(btoB);
		List<Route> shortestRoute = shortestRouteFinder.findRoutes();
		Route route = shortestRoute.get(0);
		Assert.assertEquals(route.getTotalDistance(), 9);
		Assert.assertEquals(route.getConnections().size(), 3);
		Assert.assertEquals(route.getConnections().get(0).getDistance(), 4);
		Assert.assertEquals(route.getConnections().get(1).getDistance(), 2);
		Assert.assertEquals(route.getConnections().get(2).getDistance(), 3);
	}

}
