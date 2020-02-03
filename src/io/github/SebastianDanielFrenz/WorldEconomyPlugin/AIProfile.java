package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class AIProfile {

	public String username;
	public long bankingID;
	public long aiAsEmployerID;
	public long employeeID;
	public long aiID;

	public AIProfile(long aiID, String username, long bankingID, long employeeID, long aiAsEmployerID) {
		this.aiID = aiID;
		this.username = username;
		this.bankingID = bankingID;
		this.employeeID = employeeID;
		this.aiAsEmployerID = aiAsEmployerID;
	}
}
