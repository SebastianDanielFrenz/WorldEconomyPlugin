package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class Version {

	public Version(VersionType type, VersionStability stability, int[] numeric, char patch_level) {
		this.type = type;
		this.stability = stability;
		this.numeric = numeric;
		this.patch_level = patch_level;
	}

	private char patch_level;
	private int[] numeric;
	private VersionType type;
	private VersionStability stability;

	public static Version parseVersion(String raw) {
		// example: alpha 1.0.0c
		String[] split = raw.split(" ");
		VersionType type;
		if (split[0].equalsIgnoreCase("alpha")) {
			type = VersionType.ALPHA;
		} else if (split[0].equalsIgnoreCase("beta")) {
			type = VersionType.BETA;
		} else if (split[0].equalsIgnoreCase("version")) {
			type = VersionType.RELEASE;
		} else {
			throw new RuntimeException(raw + " is not properly formatted!");
		}

		String[] split2 = split[1].split(".");

		int[] nums = new int[split2.length];
		for (int i = 0; i < split2.length - 1; i++) {
			nums[i] = Integer.parseInt(split2[i]);
		}

		String patch_level_or_version_number = String.valueOf(split2[split2.length - 2].charAt(split2[split2.length - 2].length() - 1));
		char patch_level;
		if ("1234567890".contains(patch_level_or_version_number)) {
			nums[split2.length - 1] = Integer.parseInt(split2[split2.length - 1]);
			patch_level = 0;
		} else if ("abcdefghijklmnopqrstuvwxyz".contains(patch_level_or_version_number)) {
			// a --> 1 (none --> 0)
			nums[split2.length] = "abcdefghijklmnopqrstuvwxyz".indexOf(patch_level_or_version_number) + 1;
			patch_level = (char) Integer.parseInt(split2[split2.length - 1].substring(0, split2[split2.length - 1].length() - 1));
		} else {
			throw new RuntimeException(raw + " is not properly formatted!");
		}

		VersionStability stability = VersionStability.STABLE;
		if (split.length == 3) {
			if (split[2].replace("(", "").replace(")", "").equalsIgnoreCase("dev")) {
				stability = VersionStability.DEV;
			}
		}

		return new Version(type, stability, nums, patch_level);

	}

	@Override
	public String toString() {
		String out = type.toString();
		out += " ";
		for (int i = 0; i < numeric.length - 1; i++) {
			out += String.valueOf(numeric[i]) + ".";
		}
		out += String.valueOf(numeric[numeric.length - 1]);
		if (patch_level != 0) {
			out += patch_level + 'a';
		}
		out += " ";
		out += "(" + stability.toString() + ")";
		return out;
	}

}
