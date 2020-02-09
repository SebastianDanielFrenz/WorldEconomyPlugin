package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

public class ChooserGUIResponse {

	public ChooserGUIResponseType type;
	public Object value;

	public ChooserGUIResponse(ChooserGUIResponseType type, Object value) {
		this.type = type;
		this.value = value;
	}

}
