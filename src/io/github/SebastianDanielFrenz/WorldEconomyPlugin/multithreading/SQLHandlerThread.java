package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Config;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class SQLHandlerThread implements Runnable {

	private static List<String> queries = new ArrayList<String>();

	private Queue<long[]> idles = new LinkedList<long[]>();
	private Queue<long[]> working = new LinkedList<long[]>();

	private int period = Config.getPerformanceMonitoringPeriod() * 1000;

	private Thread requesting_thread;

	@Override
	public void run() {
		long loop_start;
		int runs;

		while (WorldEconomyPlugin.status != WorldEconomyPlugin.STATUS_STOPPING) {
			if (queries.size() == 0) {
				try {
					Thread.sleep(10);
					idles.add(new long[] { 10, System.currentTimeMillis() + period });
				} catch (InterruptedException e) {
					if (WorldEconomyPlugin.status == WorldEconomyPlugin.STATUS_STOPPING) {
						break;
					}
					// prepare performance report

					purgeOldTrackingData();
					requesting_thread.interrupt();
				}
			} else {
				runs = 0;

				for (String query : queries) {
					loop_start = System.currentTimeMillis();

					try {
						WorldEconomyPlugin.runSQLasync(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					working.add(new long[] { System.currentTimeMillis() - loop_start, System.currentTimeMillis() + period });
					runs++;
					if (runs == 10) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							if (WorldEconomyPlugin.status == WorldEconomyPlugin.STATUS_STOPPING) {
								break;
							} else {
								purgeOldTrackingData();
								requesting_thread.interrupt();
							}
						}
					}
				}
			}
		}
	}

	public void purgeOldTrackingData() {
		try {
			while (true) {
				if (idles.peek()[1] <= System.currentTimeMillis()) {
					idles.poll();
				} else {
					break;
				}
			}
		} catch (NullPointerException e) {
		}
		try {
			while (true) {
				if (working.peek()[1] <= System.currentTimeMillis()) {
					working.poll();
				} else {
					break;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	public float getIdleTime() {
		long total = 0;
		for (long[] idle : idles) {
			total += idle[0];
		}
		return total / 1000f;
	}

	public float getWorkingTime() {
		long total = 0;
		for (long[] work : working) {
			total += work[0];
		}
		return total / 1000000000f;
	}

}
