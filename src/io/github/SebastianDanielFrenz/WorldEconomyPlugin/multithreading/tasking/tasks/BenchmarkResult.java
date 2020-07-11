package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class BenchmarkResult {

	public BenchmarkResult(CommandSender commandSender, int thread_count) {
		start = System.currentTimeMillis();
		ends = new ArrayList<Long>();
		FLOs = new ArrayList<Long>();

		this.thread_count = thread_count;
		sender = commandSender;
	}

	private long start;
	private List<Long> ends;
	private List<Long> FLOs;

	private int thread_count;
	private CommandSender sender;

	public synchronized void push(long FLOs) {
		this.FLOs.add(FLOs);
		ends.add(System.currentTimeMillis());
		if (ends.size() == thread_count) {
			// last task has finished; outputting to screen
			sender.sendMessage(WorldEconomyPlugin.PREFIX + getFLOs() / 1000000000.0 + " GFLOs in " + getDuration()
					+ "s (" + Math.round((getFLOs() / 1000000000.0) / getDuration() * 1000) / 1000.0 + " GFLOPS) using "
					+ thread_count + " threads on " + Runtime.getRuntime().availableProcessors() + " logical cores");
		}
	}

	public long getDurationMillis() {
		return ends.get(ends.size() - 1) - start;
	}

	public double getDuration() {
		return getDurationMillis() / 1000.0;
	}

	public long getFLOs() {
		long out = 0;
		for (long l : FLOs) {
			out += l;
		}
		return out;
	}

}
