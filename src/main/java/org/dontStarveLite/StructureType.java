package org.dontStarveLite;

public enum StructureType {
	NOSTRUCTURE(
		),
	TREE(	
		'T',
		't',
		new StackItem(StackType.WOOD, (byte) 1)
		),
	PINE(
		'P',
		'p',
		new StackItem(StackType.WOOD, (byte) 1)
		),
	ROCK(
		'R',
		'r',
		new StackItem(StackType.STONE, (byte) 1)
		),
	BUSH(
		'B',
		'b',
		new StackItem(StackType.BERRY, (byte) 1)
		),
	FLOWER_SPOT(
		'F',
		'f',
		new StackItem(StackType.FLOWER, (byte) 1)
			),
	GRASS_SPOT(
		'G',
		'g',
		new StackItem(StackType.GRASS, (byte) 1)
			),
	CARROT_SPOT(
		'C',
		'c',
		new StackItem(StackType.CARROT, (byte) 1)
			),
	TWIGS_SPOT(
		'W',
		'w',
		new StackItem(StackType.TWIGS, (byte) 1)),
	CAMPFIRE_SPOT(
		'L',
		'l',
		new StackItem(StackType.WOOD, (byte) 1)),
	FLINT_SPOT(
		'I',
		'i',
		new StackItem(StackType.FLINT, (byte) 1))
		;
	private char type;
	private char afterUseageType;
	private StackItem loot;

	StructureType(char type, char afterUsageType, StackItem loot) {
		this.type = type;
		this.loot = loot;
		this.afterUseageType = afterUsageType;
	}

	StructureType() {
		this('n', 'n', new StackItem());
	}

	public char getAfterUsageType() {
		return this.afterUseageType;
	}

	public StackItem getLoot() {
		return this.loot;
	}

	public char getType() {
		return this.type;
	}

}
