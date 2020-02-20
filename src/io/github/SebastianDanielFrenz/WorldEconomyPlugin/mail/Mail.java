package io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class Mail {

	public long ID;
	public String message;
	public long senderMailboxID;
	public long mailboxID;

	public Mail(long ID, String message, long senderMailboxID, long mailboxID) {
		this.ID = ID;
		this.message = message;
		this.senderMailboxID = senderMailboxID;
		this.mailboxID = mailboxID;
	}

}
