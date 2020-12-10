package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

public class NavigationPath {

	public NavigationPath(Waypoint start, Waypoint end, double cost) {
		this.end = end;
		this.cost = cost;
		this.start = start;
	}
	
	public NavigationPath(Waypoint end, double cost) {
		this.end = end;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Waypoint start;
	public final Waypoint end;
	public double cost;

}
