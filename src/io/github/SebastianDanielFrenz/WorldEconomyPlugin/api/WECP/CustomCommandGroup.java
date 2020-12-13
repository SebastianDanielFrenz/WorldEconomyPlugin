package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WECP;

import java.util.ArrayList;
import java.util.List;

public class CustomCommandGroup extends CustomCommandGroupContent {

	public CustomCommandGroup(CustomCommandGroup parent, String command) {
		super(parent, command);
	}

	public List<CustomCommandGroupContent> children = new ArrayList<CustomCommandGroupContent>();
	
	public CustomCommandGroupContent getChild(String[] args, int index) {
		CustomCommand cmd;
		CustomCommandGroup group;
		for (CustomCommandGroupContent child:children) {
			if (child.command.equalsIgnoreCase(args[index])) {
				
			}
		}
	}

}
