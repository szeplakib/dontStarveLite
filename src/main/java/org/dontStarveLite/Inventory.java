package org.dontStarveLite;

import java.util.Arrays;

public class Inventory {
	private final byte ROW = 5;
	private final byte COLUMN = 2;
	private final byte[] fullInventory = { -1, -1 };
	private Item[][] bag;

	public Inventory() {
		this.bag = new Item[ROW][COLUMN];
	}

	public static byte getStackQuantity(Item item) {
		return ((StackItem) item).getQuantity();
	}

	public boolean isStackItem(Item bagItem) {
		return bagItem instanceof StackItem;
	}

	public boolean isStackable(StackItem newItem, StackItem bagItem) {
		return isSameType(newItem, bagItem) && getStackQuantity(bagItem) < StackItem.MAX_STACK_SIZE;
	}

	public boolean canDeplete(StackType type, byte quantity, Item bagItem) {
		if (isStackItem(bagItem)) {
			return isSameType(type, (StackItem) bagItem) && getStackQuantity(bagItem) <= quantity;
		}
		return false;
	}

	public boolean isSameType(StackType type, StackItem stackItem) {
		return stackItem.getType() == type;
	}

	public boolean isSameType(StackItem stackItem, StackItem otherItem) {
		return stackItem.getType() == otherItem.getType();
	}

	public boolean isEnough(StackType type, int quantity) {
		return this.getAllQuantity(type) >= quantity;
	}

	public boolean hasEnough(StackItem stackItem) {
		byte count = 0;
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (isStackItem(item) && ((StackItem) item).getType() == stackItem.getType()) {
					count += ((StackItem) item).getQuantity();
					if (stackItem.getQuantity() <= count)
						return true;
				}
			}
		}
		return false;
	}

	public boolean hasCraftItem(CraftType craftType) {
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (item != null && !isStackItem(item)
						&& ((CraftItem) item).getType() == craftType) {
					return true;
				}
			}
		}
		return false;
	}

	public CraftItem getCraftItem(CraftType craftType) {
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (item != null && !isStackItem(item)
						&& ((CraftItem) item).getType() == craftType) {
					return (CraftItem) item;
				}
			}
		}
		return null;
	}

	public void breakCraftItem(CraftItem craftItem) {
		for (byte row = 0; row < this.bag.length; row++) {
			for (byte col = 0; col < this.bag[row].length; col++) {
				if (bag[row][col] != null && !isStackItem(bag[row][col])
						&& ((CraftItem) bag[row][col]).getType() == craftItem.getType()
						&& ((CraftItem) bag[row][col]).getDurability() <= 0) {
					bag[row][col] = null;
				}
			}
		}
	}

	public int getAllQuantity(StackType type) {
		int allQuantity = 0;
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (isStackItem(item) && (((StackItem) item).getType() == type)) {
					allQuantity += getStackQuantity(item);
				}
			}
		}
		return allQuantity;
	}

	public Item[][] getBag() {
		return this.bag;
	}

	public void useStackItem(StackType type, byte quantity) {
		if (isEnough(type, quantity)) {
			for (byte row = 0; row < this.bag.length; row++) {
				for (byte col = 0; col < this.bag[row].length; col++) {
					if (isStackItem(this.bag[row][col]) && isSameType(type, (StackItem) this.bag[row][col])) {
						if (canDeplete(type, quantity, this.bag[row][col])) {
							quantity -= getStackQuantity(this.bag[row][col]);
							this.bag[row][col] = null;
						} else if (quantity != (byte) 0) {
							((StackItem) this.bag[row][col])
									.setQuantity((byte) (getStackQuantity(this.bag[row][col]) - quantity));
							return;
						}
					}
				}
			}
		}
	}

	public void destroyCraftItem(CraftItem craftItem) {
		for (byte row = 0; row < this.bag.length; row++) {
			for (byte col = 0; col < this.bag[row].length; col++) {
				if (!isStackItem(bag[row][col]) && ((CraftItem) bag[row][col]).getType() == craftItem.getType()) {
					this.bag[row][col] = null;
					return;
				}
			}
		}
	}
	
	public void destroyItem(byte row, byte col) {
		this.bag[row][col] = null;
	}

	public void stackNewStackItem(StackItem stackItem) {
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (isStackItem(item) && this.isStackable(stackItem, (StackItem) item)) {
					((StackItem) item).addToStack(stackItem);
				}
				if (stackItem.getQuantity() == 0) {
					break;
				}

			}
		}
	}

	public byte[] searchEmptySlot() {
		for (byte row = 0; row < this.bag.length; row++) {
			for (byte column = 0; column < this.bag[row].length; column++) {
				if (this.bag[row][column] == null) {
					return new byte[]{ row, column };
				}
			}
		}
		return new byte[]{ -1, -1 };
	}

	public void printInventory() {
		for (Item[] items : this.bag) {
			for (Item item : items) {
				if (item == null)
					System.out.print(String.format("%1$-" + Item.PAD + "s", "NULL") + "\t");
				else if (item instanceof CraftItem)
					System.out.print(item + "\t");
				else
					System.out.print(item + "\t");
			}
			System.out.println();
		}
	}

	public void pickUpItem(Item item) {
		if (item instanceof StackItem) {
			StackItem stackItem = (StackItem) item;
			if (stackItem.getType().getType() == 'N')
				return;
			this.stackNewStackItem(stackItem);
			if (stackItem.getQuantity() != 0) {
				byte[] coordinates = this.searchEmptySlot();
				if (!Arrays.equals(coordinates, this.fullInventory)) {
					this.bag[coordinates[0]][coordinates[1]] = stackItem.getClass().cast(stackItem.clone());
					stackItem.setQuantity((byte) 0);
				} else {
					System.out.println("Inventory is full!!!");
				}
			}
		} else {
			byte[] coordinates = this.searchEmptySlot();
			if (!Arrays.equals(coordinates, this.fullInventory)) {
				bag[coordinates[0]][coordinates[1]] = item;
			} else {
				System.out.println("Inventory is full!!!");
			}
		}
	}

}
