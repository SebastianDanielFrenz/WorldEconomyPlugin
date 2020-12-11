package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

public class Navigator {

	private NavigationRoute route;
	private int index;

	public Navigator(NavigationRoute route) {
		this.route = route;
	}

	public Navigator(PathNetwork network, Waypoint start, Waypoint end) {
		route = AstarSearchAlgo2.AStarSearch(network, start, end);
	}

	public NavigationPath getNextPath() {
		index++;
		return route.paths[index - 1];
	}

	public boolean hasNextPath() {
		return route.paths.length > index;
	}

}
