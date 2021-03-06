package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class BenchmarkTask extends Task {

	private int runs;
	private int current_runs = 0;
	private long slice = 10000;
	private long flos;
	private long start;

	private boolean done = false;

	private CommandSender sender;
	private BenchmarkResult result;

	public BenchmarkTask(int runs, CommandSender sender, BenchmarkResult result) {
		this.runs = runs;
		flos = slice * runs;
		this.sender = sender;
		this.result = result;
	}

	@Override
	public int getPriority() {
		return -10000;
	}

	@Override
	public void init() {
		start = System.currentTimeMillis();
	}

	@Override
	public void work() {
		@SuppressWarnings("unused")
		float f = 1.1f;
		for (int i = 0; i < slice; i++) {
			f *= 1.1f;
		}
		current_runs++;

		if (runs == current_runs) {
			done = true;
			double time = (System.currentTimeMillis() - start) / 1000f;
			if (!(sender instanceof CommandBlock)) {
				result.push(flos);
			}
		}
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		return false;
	}

	@Override
	public boolean hasFinished() {
		return done;
	}

	@Override
	public String getName() {
		return "Benchmarking Task";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
