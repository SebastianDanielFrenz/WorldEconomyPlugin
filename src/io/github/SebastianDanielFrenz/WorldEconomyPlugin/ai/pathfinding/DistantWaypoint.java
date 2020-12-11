package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

public class DistantWaypoint {

	public final Waypoint waypoint;
	public double stacked_path_length;

	public DistantWaypoint(Waypoint waypoint, double current_length) {
		this.waypoint = waypoint;
		stacked_path_length = current_length;
	}

}
