package org.dontStarveLite;

public class StackItem extends Item implements Cloneable {

	public static final byte MAX_STACK_SIZE = 5;

	private final boolean isStackable = true;
	private byte quantity;
	private StackType type;

	StackItem() {
		this(StackType.NULL);
	}

	StackItem(StackType type) {
		this(type, (byte) 1);
	}

	StackItem(StackType type, byte quantity) {
		this.type = type;
		this.quantity = quantity;
	}

	public StackType getType() {
		return this.type;
	}

	@Override
	public Object clone(){
		return new StackItem(this.type, this.quantity);
	}

	public boolean isStackable() {
		return this.isStackable;
	}

	public byte getQuantity() {
		return this.quantity;
	}

	public void setQuantity(byte quantity) {
		this.quantity = quantity;
	}

	boolean doesItFit(StackItem otherItem) {
		return 100 > this.getQuantity() + otherItem.getQuantity();
	}

	boolean isSameType(StackItem otherItem) {
		return this.getType() == otherItem.getType();
	}

	public void addToStack(StackItem stackitem) {
		if (this.doesItFit(stackitem) && this.isSameType(stackitem)) {
			this.quantity += stackitem.quantity;
			stackitem.setQuantity((byte) 0);
		} else if (this.isSameType(stackitem)) {
			stackitem.quantity -= (MAX_STACK_SIZE - this.quantity);
			this.quantity = MAX_STACK_SIZE;
		}
	}

	@Override
	public String toString() {
		String text = this.getType().getName() + ":" + this.getQuantity();
		return String.format("%1$-" + StackItem.PAD + "s", text);
	}
	
	@Override
	public String toGuiString() {
		return this.getType().getName() + ":" + this.getQuantity();
	}

}
