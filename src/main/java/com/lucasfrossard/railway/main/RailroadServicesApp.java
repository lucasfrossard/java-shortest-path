package com.lucasfrossard.railway.main;

import java.util.List;
import java.util.Set;

import com.lucasfrossard.railway.entities.Route;
import com.lucasfrossard.railway.entities.Node;
import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.entities.Trip;
import com.lucasfrossard.railway.route.finder.ExactlyStopsRouteFinder;
import com.lucasfrossard.railway.route.finder.MaxDistanceRouteFinder;
import com.lucasfrossard.railway.route.finder.MaxStopsRouteFinder;
import com.lucasfrossard.railway.route.finder.NoSuchRouteException;
import com.lucasfrossard.railway.route.finder.ShortestRouteFinder;
import com.lucasfrossard.railway.trip.DistanceCalculator;
import com.lucasfrossard.railway.util.InputProcessor;

/**
 * RailroadServiceApp
 *
 */
public class RailroadServicesApp {

	private InputProcessor inputProcessor = new InputProcessor();

	private StationsNetwork stationsNetwork;

	public RailroadServicesApp(String entry) {
		this.stationsNetwork = inputProcessor.initialize(entry);
	}

	private void run() {
		DistanceCalculator distanceCalculator = new DistanceCalculator(stationsNetwork);

		this.printTravel(distanceCalculator, 1, "A", "B", "C");
		this.printTravel(distanceCalculator, 2, "A", "D");
		this.printTravel(distanceCalculator, 3, "A", "D", "C");
		this.printTravel(distanceCalculator, 4, "A", "E", "B", "C", "D");
		this.printTravel(distanceCalculator, 5, "A", "E", "D");

		Node stationC = stationsNetwork.getExistingInstance("C");
		Trip cToC = new Trip(stationC, stationC);
		MaxStopsRouteFinder maxStopRouteFinder = new MaxStopsRouteFinder(cToC, 3);
		List<Route> routesFound = maxStopRouteFinder.findRoutes();
		Set<Route> deduplicatedMax3Stops = maxStopRouteFinder.deduplicate(routesFound);
		System.out.println("#6 " + deduplicatedMax3Stops.size());

		Node stationA = stationsNetwork.getExistingInstance("A");
		Trip aToC = new Trip(stationA, stationC);
		ExactlyStopsRouteFinder max4StopsRouteFinder = new ExactlyStopsRouteFinder(aToC, 4);
		routesFound = max4StopsRouteFinder.findRoutes();
		Set<Route> deduplicatedMax4Stops = max4StopsRouteFinder.deduplicate(routesFound);
		System.out.println("#7 " + deduplicatedMax4Stops.size());

		ShortestRouteFinder shortestRouteFinder = new ShortestRouteFinder(aToC);
		List<Route> findRoutes = shortestRouteFinder.findRoutes();
		Route route = findRoutes.get(0);
		System.out.println("#8 " + route.getTotalDistance());

		Node stationB = stationsNetwork.getExistingInstance("B");
		Trip bToB = new Trip(stationB, stationB);
		shortestRouteFinder = new ShortestRouteFinder(bToB);
		List<Route> routesB2B = shortestRouteFinder.findRoutes();
		Route routeB2B = routesB2B.get(0);
		System.out.println("#9 " + routeB2B.getTotalDistance());

		MaxDistanceRouteFinder maxDistanceRouteFinder = new MaxDistanceRouteFinder(cToC, 30);
		List<Route> maxDistanceRoutes = maxDistanceRouteFinder.findRoutes();
		Set<Route> deduplicated = maxDistanceRouteFinder.deduplicate(maxDistanceRoutes);
		System.out.println("#10 " + deduplicated.size());

	}

	public static void main(String[] args) {
		String entry = null;
		if (args != null && args.length > 0) {
			entry = args[0];
		} else {
			entry = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		}
		RailroadServicesApp app = new RailroadServicesApp(entry);
		app.run();
	}

	private void printTravel(DistanceCalculator distanceCalculator, int i, String... destinations) {
		try {
			System.out.println("#" + i + " " + distanceCalculator.calculateDistance(destinations));
		} catch (NoSuchRouteException e) {
			System.out.println("#" + i + " NO SUCH ROUTE");
		}
	}

}
