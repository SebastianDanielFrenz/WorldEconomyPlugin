package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

import java.util.ArrayList;
import java.util.List;

public class PathNetwork {

	public List<Waypoint> waypoints = new ArrayList<Waypoint>();
	public void addAll(Waypoint[] waypoints) {
		for (Waypoint waypoint:waypoints) {
			this.waypoints.add(waypoint);
		}
	}

}