package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public class ContractCancellationDetails {

	private final ContractCancellationType cancellationType;
	private final long multiplier;
	private final TimeInterval interval;
	private final long start;

	public ContractCancellationDetails(ContractCancellationType type, TimeInterval interval, long multiplier,
			long currentTickCount) {
		this.cancellationType = type;
		this.interval = interval;
		this.multiplier = multiplier;

		start = currentTickCount;
	}

	public TimeInterval getInterval() {
		return interval;
	}

	public ContractCancellationType getCancellationType() {
		return cancellationType;
	}

	public long getMultiplier() {
		return multiplier;
	}

	public long getStart() {
		return start;
	}

	/**
	 * -1 means that you are not able to terminate the contract.
	 * 
	 * @param currentTicks
	 * @return
	 */
	public long getDueTime(long currentTicks) {
		if (cancellationType == ContractCancellationType.PERIODS) {
			long passed = currentTicks - start;

			return ((passed - 1) / IntervalTranslator.translateInGame(interval, multiplier) + 1)
					* IntervalTranslator.translateInGame(interval, multiplier) + start;
		} else if (cancellationType == ContractCancellationType.DURATION) {
			return IntervalTranslator.translateInGame(interval, multiplier) + currentTicks;
		} else if (cancellationType == ContractCancellationType.INSTANT) {
			return currentTicks;
		} else {
			return -1;
		}
	}

}
