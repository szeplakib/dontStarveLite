package org.dontStarveLite;

public enum StackType {
	NULL(	'N', "Empty", false, (byte)0, (byte)0, (byte)0, false),
	WOOD(	'w', "Wood", false, (byte)0, (byte)0, (byte)0, false),
	IRON(	'i', "Iron", false, (byte)0, (byte)0, (byte)0, false),
	STONE(	'S', "Stone", false, (byte)0, (byte)0, (byte)0, false),
	BERRY(	'b', "Berry",true,	(byte)-5, (byte)1, (byte)10, true),
	COOKED_BERRY( 'B', "Cooked berry", true, (byte)0, (byte)2, (byte)8, false),
	FLOWER(	'f', "Flower", true, (byte)0, (byte)5, (byte)1, false),
	GRASS(	'g', "Grass", false, (byte)0, (byte)0, (byte)0, false),
	CARROT(	'c', "Carrot", true, (byte)0, (byte)1, (byte)10, true),
	COOKED_CARROT( 'C', "Cooked carrot", true, (byte)3, (byte)2, (byte)8, false),
	TWIGS(	'w', "Twigs", false, (byte)0, (byte)0, (byte)0, false),
	FLINT('i', "Flint", false, (byte)0, (byte)0, (byte)0, false),
	;

	private char type;
	private String name;
	private boolean isEdible;
	private boolean isCookable;
	private byte health;
	private byte sanity;
	private byte hunger;

	

	StackType(char type, String name, boolean isEdible, byte health, byte sanity, byte hunger, boolean isCookable) {
		this.type = type;
		this.name = name;
		this.isEdible = isEdible;
		this.health = health;
		this.sanity = sanity;
		this.hunger = hunger;
		this.isCookable = isCookable;
	}

	public char getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isEdible() {
		return this.isEdible;
	}
	
	public byte getHealth() {
		return this.health;
	}
	
	public byte getSanity() {
		return this.sanity;
	}
	
	public byte getHunger() {
		return this.hunger;
	}
	
	public boolean isCookable() {
		return this.isCookable;
	}

}
