package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public class TimeIntervalNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2003294583569874645L;

	public TimeIntervalNotFoundException(TimeInterval interval) {
		super(interval.toString());
	}

}
