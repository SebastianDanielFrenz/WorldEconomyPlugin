package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.LimitedQueue;

public class Waypoint {

	public List<NavigationPath> paths = new ArrayList<NavigationPath>();
	private LimitedQueue<NavigationRoute> cache = new LimitedQueue<NavigationRoute>(10);
	private String name;

	private Location location;

	public Waypoint(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	public Waypoint(String name, double x, double y, double z) {
		this.name = name;
		location = new Location(null, x, y, z);
	}

	@Deprecated
	public Waypoint(String name, int dst) {
		this.name = name;
		Random random = new Random();
		location = new Location(null, random.nextDouble() * dst, random.nextDouble() * dst, random.nextDouble() * dst);
	}

	public NavigationRoute getRoute(PathNetwork network, Waypoint waypoint) {
		for (NavigationRoute route : cache) {
			if (route.end == waypoint) {
				return route;
			}
		}

		NavigationRoute route = calculateRoute(network, waypoint);
		cache.add(route);
		return route;
	}

	public NavigationRoute calculateRoute(PathNetwork network, Waypoint waypoint) {
		return AstarSearchAlgo2.AStarSearch(network, this, waypoint);
	}

	public double getDirectDistance(Waypoint waypoint) {
		return Math.sqrt(Math.pow(location.getX() - waypoint.location.getX(), 2) + Math.pow(location.getY() - waypoint.location.getY(), 2)
				+ Math.pow(location.getZ() - waypoint.location.getZ(), 2));
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public void setPaths(NavigationPath[] paths) {
		this.paths.clear();
		for (NavigationPath path : paths) {
			this.paths.add(path);
			path.start = this;
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
