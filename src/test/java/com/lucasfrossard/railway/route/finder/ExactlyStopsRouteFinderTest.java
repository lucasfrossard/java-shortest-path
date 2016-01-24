package com.lucasfrossard.railway.route.finder;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.entities.Trip;
import com.lucasfrossard.railway.util.InputProcessor;

import junit.framework.Assert;

public class ExactlyStopsRouteFinderTest {

	@Test
	public void testFindRoutes() {
		InputProcessor processor = new InputProcessor();
		StationsNetwork stationsNetwork = processor.initialize("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Node stationA = stationsNetwork.getExistingInstance("A");
		Node stationC = stationsNetwork.getExistingInstance("C");
		Trip aToC = new Trip(stationA, stationC);
		ExactlyStopsRouteFinder max4StopsRouteFinder = new ExactlyStopsRouteFinder(aToC, 4);
		List<Route> routesFound = max4StopsRouteFinder.findRoutes();
		Set<Route> deduplicatedMax4Stops = max4StopsRouteFinder.deduplicate(routesFound);
		Assert.assertEquals(deduplicatedMax4Stops.size(), 3);
	}

}
