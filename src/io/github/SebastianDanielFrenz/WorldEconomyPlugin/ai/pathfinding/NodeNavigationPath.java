package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

public class NodeNavigationPath {

	public NodeNavigationPath(NavigationPath navigationPath, Node target, double cost) {
		this.target = target;
		this.cost = cost;
		this.navigationPath = navigationPath;
	}

	public final Node target;
	public double cost;
	public final NavigationPath navigationPath;

}
