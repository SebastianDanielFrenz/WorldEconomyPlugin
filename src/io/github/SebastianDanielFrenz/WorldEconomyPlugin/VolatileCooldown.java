package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class VolatileCooldown {

	public VolatileCooldown(long duration) {
		this.duration = duration;
	}

	public VolatileCooldown(long duration, boolean used) {
		if (used) {
			this.used = System.currentTimeMillis();
		}
		this.duration = duration;
	}

	private long used;
	private long duration;

	public boolean isReady() {
		return used + duration <= System.currentTimeMillis();
	}

	public boolean use() {
		if (isReady()) {
			used = System.currentTimeMillis();
			return true;
		} else {
			return false;
		}
	}

}
