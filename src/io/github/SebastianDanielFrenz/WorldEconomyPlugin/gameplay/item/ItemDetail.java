package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

public class ItemDetail {

	public ItemDetail(ItemDetailType type, Object data) {
		this.type = type;
		this.data = data;
	}

	public final ItemDetailType type;
	public final Object data;

	public boolean equals(Object obj) {
		if (obj instanceof ItemDetail) {
			return equals((ItemDetail) obj);
		}
		return false;
	}

	public boolean equals(ItemDetail detail) {
		return type == detail.type && data.equals(detail.data);
	}

}
