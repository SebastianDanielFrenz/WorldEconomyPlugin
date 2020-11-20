package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public enum VersionType {

	ALPHA("alpha"),
	BETA("beta"),
	RELEASE("version");

	public String str;

	VersionType(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}

}
