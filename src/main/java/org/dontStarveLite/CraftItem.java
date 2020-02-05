package org.dontStarveLite;

public abstract class CraftItem extends Item {
	private int durability;
	private CraftType type;

	public void deteriorate() {
		this.setDurability((byte) (this.getDurability() - this.type.getDeteriorRate()));
	}

	CraftItem(CraftType type) {
		this.type = type;
		this.durability = 100;
	}

	public int getDurability() {
		return this.durability;
	}

	public CraftType getType() {
		return this.type;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	@Override
	public String toString() {
		String text = this.getType().getName() + ":" + this.getDurability();
		return String.format("%1$-" + StackItem.PAD + "s", text);
	}
	
	@Override
	public String toGuiString() {
		return this.getType().getName() + ":" + this.getDurability();
	}
}
