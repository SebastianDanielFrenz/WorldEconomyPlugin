package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public class IntervalTranslator {

	public static long translateInGame(TimeInterval interval, long multiplier) {
		if (interval == TimeInterval.MINUTE_INTERVAL) {
			return ((long) ((double) 1000 / 60) * multiplier);
		} else if (interval == TimeInterval.HOURLY) {
			return 1000 * multiplier;
		} else if (interval == TimeInterval.DAILY) {
			return 24000 * multiplier;
		} else if (interval == TimeInterval.MONTHLY) {
			return 20 * 60 * 60 * 24 * 30 * multiplier;
		} else if (interval == TimeInterval.YEARLY) {
			return 20 * 60 * 60 * 24 * 365 * multiplier;
		} else {
			throw new TimeIntervalNotFoundException(interval);
		}
	}

}
