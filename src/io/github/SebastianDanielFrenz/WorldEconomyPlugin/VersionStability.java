package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public enum VersionStability {

	STABLE("stable"),
	DEV("dev");

	public String repr;

	private VersionStability(String repr) {
		this.repr = repr;
	}

	@Override
	public String toString() {
		return repr;
	}

}
