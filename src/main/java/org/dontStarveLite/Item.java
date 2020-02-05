package org.dontStarveLite;

public abstract class Item {
	public static final int PAD = 30;
	private static long allIds = 0;

	private long id;

	Item() {
		this.id = getAndIncrementAllIds();
	}

	public abstract String toGuiString();
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public static long getAllIds() {
		return Item.allIds;
	}

	public static long getAndIncrementAllIds() {
		long currentId = Item.allIds;
		Item.allIds += 1;
		return currentId;
	}
}
