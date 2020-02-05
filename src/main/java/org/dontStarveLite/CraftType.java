package org.dontStarveLite;

public enum CraftType {
	AXE(
		'a',
		"Axe",
		(byte)5,
		(byte)20,
		new StackItem[] {new StackItem(StackType.GRASS, (byte)1), new StackItem(StackType.TWIGS, (byte)3), new StackItem(StackType.FLINT, (byte)1)}
		),
	PICKAXE(
		'p',
		"Pickaxe",
		(byte)5,
		(byte)20, 
		new StackItem[] {new StackItem(StackType.GRASS, (byte)1), new StackItem(StackType.WOOD, (byte)1), new StackItem(StackType.FLINT, (byte)1)}
		),
	REPAIR_HAMMER(
			'r',
			"Repair hammer",
			(byte)5,
			(byte)1, 
			new StackItem[] {new StackItem(StackType.STONE, (byte)2), new StackItem(StackType.WOOD, (byte)2)}
			),
	GARLAND(
			'g',
			"Garland",
			(byte)1,
			(byte)0,
			new StackItem[] {new StackItem(StackType.FLOWER, (byte)10)}
			),
	CAMPFIRE(
			'c',
			"Campfire",
			(byte)10,
			(byte)0,
			new StackItem[] {new StackItem(StackType.WOOD, (byte)2), new StackItem(StackType.STONE, (byte)4), new StackItem(StackType.GRASS, (byte)2)}
			)
	;
	
	private char type;
	private String name;
	private byte deteriorRate;
	private byte repairRate;
	private StackItem[] ingredients;

	CraftType(char type, String name, byte deteriorRate, byte repairRate, StackItem[] ingredients) {
		this.type = type;
		this.name = name;
		this.deteriorRate = deteriorRate;
		this.repairRate = repairRate;
		this.ingredients = ingredients;
	}

	public char getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public byte getDeteriorRate() {
		return deteriorRate;
	}

	public byte getRepairRate() {
		return repairRate;
	}

	public StackItem[] getIngredients() {
		return this.ingredients;
	}
	
	public String getRecipe() {
		StringBuilder recipe = new StringBuilder();
		for (StackItem i : ingredients) {
			recipe.append(i.getType().getName() + ": " + i.getQuantity() + ", ");
		}
		recipe.delete(recipe.length()-2, recipe.length()-1);
		return recipe.toString();
	}
}
