package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.pathfinding;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AstarSearchAlgo2 {

	// h scores is the stright-line distance from the current city to Bucharest
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// initialize the graph base on the Romania map
		Waypoint n1 = new Waypoint("Arad", 366);
		Waypoint n2 = new Waypoint("Zerind", 374);
		Waypoint n3 = new Waypoint("Oradea", 380);
		Waypoint n4 = new Waypoint("Sibiu", 253);
		Waypoint n5 = new Waypoint("Fagaras", 178);
		Waypoint n6 = new Waypoint("Rimnicu Vilcea", 193);
		Waypoint pitesti = new Waypoint("Pitesti", 98);
		Waypoint n8 = new Waypoint("Timisoara", 329);
		Waypoint n9 = new Waypoint("Lugoj", 244);
		Waypoint n10 = new Waypoint("Mehadia", 241);
		Waypoint n11 = new Waypoint("Drobeta", 242);
		Waypoint n12 = new Waypoint("Craiova", 160);
		Waypoint buacharest = new Waypoint("Bucharest", 0);
		Waypoint n14 = new Waypoint("Giurgiu", 77);

		PathNetwork network = new PathNetwork();

		// initialize the NavigationPaths

		// Arad
		n1.setPaths(new NavigationPath[] { new NavigationPath(n2, 75), new NavigationPath(n4, 140), new NavigationPath(n8, 118) });

		// Zerind
		n2.setPaths(new NavigationPath[] { new NavigationPath(n1, 75), new NavigationPath(n3, 71) });

		// Oradea
		n3.setPaths(new NavigationPath[] { new NavigationPath(n2, 71), new NavigationPath(n4, 151) });

		// Sibiu
		n4.setPaths(new NavigationPath[] { new NavigationPath(n1, 140), new NavigationPath(n5, 99), new NavigationPath(n3, 151),
				new NavigationPath(n6, 80), });

		// Fagaras
		n5.setPaths(new NavigationPath[] { new NavigationPath(n4, 99),

				// 178
				new NavigationPath(buacharest, 211) });

		// Rimnicu Vilcea
		n6.setPaths(new NavigationPath[] { new NavigationPath(n4, 80), new NavigationPath(pitesti, 97), new NavigationPath(n12, 146) });

		// Pitesti
		pitesti.setPaths(new NavigationPath[] { new NavigationPath(n6, 97), new NavigationPath(buacharest, 101), new NavigationPath(n12, 138) });

		// Timisoara
		n8.setPaths(new NavigationPath[] { new NavigationPath(n1, 118), new NavigationPath(n9, 111) });

		// Lugoj
		n9.setPaths(new NavigationPath[] { new NavigationPath(n8, 111), new NavigationPath(n10, 70) });

		// Mehadia
		n10.setPaths(new NavigationPath[] { new NavigationPath(n9, 70), new NavigationPath(n11, 75) });

		// Drobeta
		n11.setPaths(new NavigationPath[] { new NavigationPath(n10, 75), new NavigationPath(n12, 120) });

		// Craiova
		n12.setPaths(new NavigationPath[] { new NavigationPath(n11, 120), new NavigationPath(n6, 146), new NavigationPath(pitesti, 138) });

		// Bucharest
		buacharest.setPaths(new NavigationPath[] { new NavigationPath(pitesti, 101), new NavigationPath(n14, 90), new NavigationPath(n5, 211) });

		// Giurgiu
		n14.setPaths(new NavigationPath[] { new NavigationPath(buacharest, 90) });

		network.addAll(new Waypoint[] { n1, n2, n3, n4, n5, n6, pitesti, n8, n9, n10, n11, n12, buacharest, n14 });

		NavigationRoute route = AStarSearch(network, n1, buacharest);

		System.out.println("Path: " + route.toString());

	}

	public static List<Node> nodifyWaypoints(PathNetwork network, Waypoint origin) {
		List<Node> out = new ArrayList<Node>();
		for (Waypoint point : network.waypoints) {
			out.add(new Node(point, origin));
		}
		return out;
	}

	public static void nodifyPaths(PathNetwork network, List<Node> nodes) {
		Node node;
		Waypoint waypoint;
		NavigationPath path;
		Node node2 = null;

		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			waypoint = network.waypoints.get(i);
			node.adjacencies = new NodeNavigationPath[waypoint.paths.size()];
			for (int j = 0; j < node.adjacencies.length; j++) {
				path = waypoint.paths.get(j);
				// find node corresponding to waypoint

				for (Node _node2 : nodes) {
					if (_node2.waypoint == path.end) {
						node2 = _node2;
						break;
					}
				}
				node.adjacencies[j] = new NodeNavigationPath(path, node2, path.getCost());
			}

		}
	}

	public static List<Node> nodifyNetwork(PathNetwork network, Waypoint origin) {
		List<Node> out = nodifyWaypoints(network, origin);
		nodifyPaths(network, out);
		return out;
	}

	public static List<NodeNavigationPath> RawAstarSearch(PathNetwork network, Waypoint _source, Waypoint _goal) {

		List<Node> nodified_network = nodifyNetwork(network, _goal);

		Node source = null;
		for (Node node : nodified_network) {
			if (node.waypoint == _source) {
				source = node;
				break;
			}
		}
		Node goal = null;
		for (Node node : nodified_network) {
			if (node.waypoint == _goal) {
				goal = node;
				break;
			}
		}

		Set<Node> explored = new HashSet<Node>();

		PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
			// override compare method
			public int compare(Node i, Node j) {
				if (i.f_scores > j.f_scores) {
					return 1;
				}

				else if (i.f_scores < j.f_scores) {
					return -1;
				}

				else {
					return 0;
				}
			}

		});

		// cost from start
		source.g_scores = 0;

		queue.add(source);

		boolean found = false;

		while ((!queue.isEmpty()) && (!found)) {

			// the node in having the lowest f_score value
			Node current = queue.poll();

			explored.add(current);

			// goal found
			if (current.value.equals(goal.value)) {
				found = true;
			}

			// check every child of current node
			for (NodeNavigationPath e : current.adjacencies) {
				Node child = e.target;
				double cost = e.cost;
				double temp_g_scores = current.g_scores + cost;
				double temp_f_scores = temp_g_scores + child.distance;

				/*
				 * if child node has been evaluated and the newer f_score is
				 * higher, skip
				 */

				if ((explored.contains(child)) && (temp_f_scores >= child.f_scores)) {
					continue;
				}

				/*
				 * else if child node is not in queue or newer f_score is lower
				 */

				else if ((!queue.contains(child)) || (temp_f_scores < child.f_scores)) {

					child.parent = current;
					child.g_scores = temp_g_scores;
					child.f_scores = temp_f_scores;

					if (queue.contains(child)) {
						queue.remove(child);
					}

					queue.add(child);

				}

			}

		}

		List<NodeNavigationPath> out = new ArrayList<NodeNavigationPath>();

		Node parent = goal;
		NodeNavigationPath nodeNav = null;
		//System.out.println("Reverse: "+goal);
		for (Node node = goal.parent; node != null; node = node.parent) {
			//System.out.println(node);
			for (NodeNavigationPath path : node.adjacencies) {
				if (path.target == parent) {
					nodeNav = path;
					//System.out.println("match: "+path.navigationPath.start+" - "+path.navigationPath.end);
					break;
				}
			}

			out.add(nodeNav);
			
			parent = node;
		}

		Collections.reverse(out);
		return out;

	}

	public static NavigationRoute convertToNavigationRoute(Waypoint start, List<NodeNavigationPath> node_paths) {
		NavigationPath[] paths = new NavigationPath[node_paths.size()];
		for (int i = 0; i < paths.length; i++) {
			paths[i] = node_paths.get(i).navigationPath;
		}
		return new NavigationRoute(start, paths);
	}

	public static NavigationRoute AStarSearch(PathNetwork network, Waypoint _source, Waypoint _goal) {
		return convertToNavigationRoute(_source, RawAstarSearch(network, _source, _goal));
	}

}

class Node {

	public final String value;
	public double g_scores;
	// public final double h_scores;
	// replaced by:
	public final double distance;
	public double f_scores = 0;
	public NodeNavigationPath[] adjacencies;
	public Node parent;
	public final Waypoint waypoint;

	public Node(String val, double hVal, Waypoint waypoint) {
		value = val;
		// h_scores = hVal;
		distance = hVal;
		this.waypoint = waypoint;
	}

	public Node(Waypoint waypoint, Waypoint origin) {
		distance = waypoint.getDirectDistance(origin);
		value = waypoint.getName();
		this.waypoint = waypoint;
	}

	public String toString() {
		return value;
	}

}