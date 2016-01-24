package com.lucasfrossard.railway.route.finder;

import java.util.List;

import org.junit.Test;

import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.entities.Trip;
import com.lucasfrossard.railway.util.InputProcessor;

import junit.framework.Assert;

public class MaxStopsRouteFinderTest {

	@Test
	public void testFindRoutes() {
		InputProcessor processor = new InputProcessor();
		StationsNetwork stationsNetwork = processor.initialize("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Node stationC = stationsNetwork.getExistingInstance("C");
		Trip cToC = new Trip(stationC, stationC);
		MaxStopsRouteFinder maxStopsRouteFinder = new MaxStopsRouteFinder(cToC, 3);
		List<Route> findRoutes = maxStopsRouteFinder.findRoutes();
		Assert.assertNotNull(findRoutes);
		Assert.assertEquals(findRoutes.size(), 2);
	}

}
