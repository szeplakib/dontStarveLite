package org.dontStarveLite;

public class Structure {
	private StructureType type;
	private char currentType;
	private boolean isUseAble;

	Structure() {
		this(StructureType.NOSTRUCTURE);
		this.isUseAble = false;
	}

	Structure(StructureType type) {
		this.type = type;
		this.currentType = type.getType();
		this.isUseAble = true;
	}

	public char getCurrentType() {
		return this.currentType;
	}

	public boolean isUseAble() {
		return this.isUseAble;
	}

	public StructureType getType() {
		return this.type;
	}

	public void setIsUsable(boolean newUsability) {
		this.isUseAble = newUsability;
	}

	public void setCurrentType(char type) {
		this.currentType = type;
	}
}
