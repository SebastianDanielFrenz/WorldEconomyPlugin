package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

public class NavigationRoute {

	public final NavigationPath[] paths;
	public final Waypoint start;
	public final Waypoint end;

	public NavigationRoute(Waypoint start, NavigationPath[] paths) {
		this.paths = paths;
		this.start = start;
		end = paths[paths.length - 1].end;
	}

	public String toString() {
		String out = start.getName() + "\n";
		for (NavigationPath path : paths) {
			out += path.start.getName() + " --> " + path.end.getName() + " (" + path.getCost() + ")\n";
		}
		return out;
	}

}
