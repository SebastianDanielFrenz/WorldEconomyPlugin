package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WECP;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomCommandGroupContent {

	public CustomCommandGroupContent(CustomCommandGroup parent, String command) {
		this.parent = parent;
		this.command = command;
	}

	public final CustomCommandGroup parent;
	public final String command;

	protected void addCommandToList(List<String> list) {
		if (parent != null) {
			parent.addCommandToList(list);
		}
		list.add(command);
	}

	public List<String> getCompleteCommand() {
		List<String> out = new ArrayList<String>(5);
		addCommandToList(out);
		return out;
	}

	public boolean matches(String[] cmd_args) {
		List<String> cmd = getCompleteCommand();
		if (cmd_args.length != cmd.size()) {
			return false;
		}
		for (int i = 0; i < cmd_args.length; i++) {
			if (!cmd_args[i].equalsIgnoreCase(cmd.get(i))) {
				return false;
			}
		}
		return true;
	}

}
