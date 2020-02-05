package org.dontStarveLite;

public class Tile {
	private static final int MAX_DROPPED_ITEMS = 4;
	private TileType type;
	private Structure structure;
	private boolean isDark;
	private boolean isLit;

	Tile(TileType type, Structure structure, Item[] droppedItems, boolean isOnFire) {
		this.type = type;
		this.structure = structure;
		this.isDark = false;
		this.isLit = false;
	}

	Tile(TileType type) {
		this(type, new Structure(), new Item[MAX_DROPPED_ITEMS], false);
	}

	Tile() {
		this(TileType.GRASS, new Structure(), new Item[MAX_DROPPED_ITEMS], false);
	}

	public TileType getType() {
		return this.type;
	}

	public Structure getStructure() {
		return this.structure;
	}

	public boolean isLit() {
		return this.isLit;
	}

	public boolean isDark() {
		return this.isDark;
	}

	public void setLit(boolean isLit) {
		this.isLit = isLit;
	}

	public void setDark(boolean isDark) {
		this.isDark = isDark;
	}

	public void placeStructure(Structure structure) {
		this.structure = structure;
	}
}
