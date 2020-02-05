package org.dontStarveLite;

public enum TileType {
	STARTERGRASS(
			'S',
			true
			),
	GRASS(
			'g',
			true
			),
	MARSHLAND(
			'm',
			true
			),
	DRYGRASS(
			'D',
			true
			),
	ROCKY_PLAINS(
			'r',
			true
			),
	MOUNTAIN(
			'M',
			false
			),
	COAST(
			'c',
			true
			),
	WATER(
			'w',
			false
			)
	;
	private char type;
	private boolean isPassable;

	TileType(char type, boolean isPassable) {
		this.type = type;
		this.isPassable = isPassable;
	}

	public char getType() {
		return this.type;
	}

	public boolean isPassable() {
		return this.isPassable;
	}
}
