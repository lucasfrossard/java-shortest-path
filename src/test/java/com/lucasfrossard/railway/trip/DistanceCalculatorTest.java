package com.lucasfrossard.railway.trip;

import org.junit.Test;

import com.lucasfrossard.railway.entities.StationsNetwork;
import com.lucasfrossard.railway.route.finder.NoSuchRouteException;
import com.lucasfrossard.railway.util.InputProcessor;

import junit.framework.Assert;

public class DistanceCalculatorTest {

	@Test
	public void testCalculateDistance() throws NoSuchRouteException {
		InputProcessor processor = new InputProcessor();
		StationsNetwork stationsNetwork = processor.initialize("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		DistanceCalculator distanceCalculator = new DistanceCalculator(stationsNetwork);
		int distanceABC = distanceCalculator.calculateDistance("A", "B", "C");
		Assert.assertEquals(distanceABC, 9);
		int distanceAD = distanceCalculator.calculateDistance("A", "D");
		Assert.assertEquals(distanceAD, 5);
		int distanceADC = distanceCalculator.calculateDistance("A", "D", "C");
		Assert.assertEquals(distanceADC, 13);
		int distanceAEBCD = distanceCalculator.calculateDistance("A", "E", "B", "C", "D");
		Assert.assertEquals(distanceAEBCD, 22);
	}
	
	@Test
	public void testCalculateDistance1()  {
		InputProcessor processor = new InputProcessor();
		StationsNetwork stationsNetwork = processor.initialize("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		DistanceCalculator distanceCalculator = new DistanceCalculator(stationsNetwork);
		try {
			distanceCalculator.calculateDistance("A", "E", "D");
			Assert.fail("Should reach here!");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof NoSuchRouteException);
		}
	}
}
