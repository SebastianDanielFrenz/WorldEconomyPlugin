package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

@DataBaseRepresentation
public abstract class BankingCustomerEntity {

	public long bankingID;

	public abstract String getType();

}
