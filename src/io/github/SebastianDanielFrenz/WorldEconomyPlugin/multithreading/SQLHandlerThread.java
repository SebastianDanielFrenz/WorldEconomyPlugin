package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Config;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class SQLHandlerThread implements Runnable {

	private static Queue<String> queries = new LinkedList<String>();

	private static Queue<long[]> idles = new LinkedList<long[]>();
	private static Queue<long[]> working = new LinkedList<long[]>();

	private static int period = Config.getPerformanceMonitoringPeriod() * 1000;

	private static Thread requesting_thread;
	private static boolean finished;

	private static Thread this_thread;

	private static long last_idle_millis;

	@Override
	public void run() {
		last_idle_millis = System.currentTimeMillis();
		this_thread = Thread.currentThread();

		long loop_start;
		int runs;

		while (WorldEconomyPlugin.status != WorldEconomyPlugin.STATUS_STOPPING) {
			if (queries.size() == 0) {
				try {
					Thread.sleep(10);
					idles.add(new long[] { 10, System.currentTimeMillis() + period });
					last_idle_millis = System.currentTimeMillis();
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

				for (String query = queries.poll(); query != null; query = queries.poll()) {
					loop_start = System.currentTimeMillis();

					try {
						WorldEconomyPlugin.runSQLasync(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					working.add(new long[] { System.currentTimeMillis() - loop_start,
							System.currentTimeMillis() + period });
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
						runs = 0;
					}
				}
			}
		}

		finished = true;
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

	public static float getIdleTime() {
		long total = 0;
		for (long[] idle : idles) {
			total += idle[0];
		}
		return total / 1000f;
	}

	public static float getWorkingTime() {
		long total = 0;
		for (long[] work : working) {
			total += work[0];
		}
		return total / 1000000000f;
	}

	/**
	 * This function exists with a halted worker thread.
	 */
	public static void requestData(Thread thread) {
		requesting_thread = thread;
		while (true) {
			try {
				if (finished) {
					return;
				}
				this_thread.interrupt();

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	/**
	 * Returns the last point, at which the handler thread was idle, measured in
	 * milliseconds.
	 * 
	 * @return
	 */
	public static long lastIdle() {
		return last_idle_millis;
	}

	public static void queueSQL(String query) {
		queries.add(query);
	}

	public static int queueLength() {
		return queries.size();
	}

}
